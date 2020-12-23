import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { StudiesService } from 'src/app/core/services/admin/studies/studies.service';
import { UserSurveyService } from '../../core/services/surveys/user-survey.service';
import { MessageService } from 'primeng/api';
import { Person } from 'src/app/core/classes/profile/person';
import { StudyQuestion } from 'src/app/core/classes/study/study_question';
import { Survey } from 'src/app/core/classes/study/survey';
import { PossibleAnswer } from 'src/app/core/classes/study/possible_answers';
import { Option } from 'src/app/core/classes/study/options';
import { AnalystService } from 'src/app/core/services/analytics/analyst.service';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-make-interview',
  templateUrl: './make-interview.component.html',
  styleUrls: ['./make-interview.component.scss'],
  providers: [MessageService]
})
export class MakeInterviewComponent implements OnInit {
  persona: Person;
  preguntas: StudyQuestion[];
  respuestas: Survey[];
  current_study: number;
  current_user: number;
  sent_form = false;
  loading = true;

  estudioErrorMessage: string;
  questionsErrorMessage: string;

  /* Form */
  surveyForm: FormGroup;
  @ViewChild('sform') surveyFormDirective;

  validationMessages = {
    respuesta_texto: {
      required: 'Campo es requerido',
    },
    opcion_seleccionada: {
      required: 'Debe seleccionar una opción'
    },
    rango_inicial: {
      required: 'Rango inicial es requerido',
      pattern: 'Rango debe ser numérico',
      lessThan: 'Rango inicial debe ser menor que rango final',
      min: 'Rango inicial no puede ser menor que el valor permitido',
      max: 'Rango inicial no puede ser mayor que el valor permitido'
    },
    rango_final: {
      required: 'Rango final es requerido',
      pattern: 'Rango debe ser numérico',
      greaterThan: 'Rango final debe ser mayor que rango inicial',
      min: 'Rango final no puede ser menor que el valor permitido',
      max: 'Rango final no puede ser mayor que el valor permitido'
    }
  };

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private userSurveyService: UserSurveyService,
              private analystService: AnalystService,
              private studiesService: StudiesService,
              private messageService: MessageService,
              private fb: FormBuilder,
              private spinner: NgxSpinnerService) {
    /* If query is empty return 404 */
    if ((this.activatedRoute.snapshot.queryParamMap.get('surveyId') || 0) === 0
    || (this.activatedRoute.snapshot.queryParamMap.get('personId') || 0) === 0) {
      this.router.navigate(['404']);
    }

    else {
      this.spinner.show();
      this.current_study = parseInt(this.activatedRoute.snapshot.queryParamMap.get('surveyId'), 10);
      this.current_user = parseInt(this.activatedRoute.snapshot.queryParamMap.get('personId'), 10);

      this.studiesService.getStudy(this.current_study).subscribe((study) => {
        /* FINISHED STUDIES DO NOT ALLOW NEW ANSWERS */
        if (study.fkEstudio.estado === 2){
          this.router.navigate(['404']);
        }
        else {
          this.studiesService.getStudyQuestionsWithOptions(this.current_study).subscribe((questions) => {
            this.preguntas = questions;

            this.analystService.isPersonPartOfAvailablePopulationInterview(this.current_study, this.current_user).subscribe((res) => {
              this.loading = false;
              this.spinner.hide();
              this.createForm();
            }, errorMessage => {
              this.router.navigate(['404']);
            });

          }, errorMessage => {
            this.questionsErrorMessage = errorMessage;
            this.loading = false;
            this.spinner.hide();
          });
        }
      });
    }
  }

  ngOnInit(): void {
  }

  createForm() {
    this.surveyForm = this.fb.group({
      answers: new FormArray([])
    });

    this.getUserAnswers();

    this.surveyForm.valueChanges
      .subscribe(data => {
        this.surveyForm.setValue(data, { emitEvent: false });
      });
  }

  /* Create form array */

  get answers() {
    return this.surveyForm.get('answers') as FormArray;
  }

  getUserAnswers() {
    // Push according to the number of questions present
    for (let i = 0; i < this.preguntas.length; i++) {
      // OPEN TEXT
      if (this.preguntas[i].fkPregunta.fkTipoPregunta._id === 1) {
        this.answers.push(new FormGroup({
          respuesta_texto: new FormControl('', Validators.required)
        }));
      }

      // SIMPLE SELECTION, MULTIPLE SELECTION, TRUE/FALSE

      else if (this.preguntas[i].fkPregunta.fkTipoPregunta._id === 2 || this.preguntas[i].fkPregunta.fkTipoPregunta._id === 3 || this.preguntas[i].fkPregunta.fkTipoPregunta._id === 4) {
        this.answers.push(new FormGroup({
          opcion_seleccionada: new FormControl(null, Validators.required)
        }));
      }

      // RANGE

      else {
        this.answers.push(new FormGroup({
          rango_inicial: new FormControl(
            '',
            [
              Validators.required,
              Validators.pattern('^[0-9]*$'),
              RxwebValidators.lessThan({ fieldName: 'rango_final' }),
              Validators.min(this.preguntas[i].fkPregunta.listPosibleRespuestas[0].fkOpcion.rangoInicial),
              Validators.max(this.preguntas[i].fkPregunta.listPosibleRespuestas[0].fkOpcion.rangoFinal)
            ]
          ),

          rango_final: new FormControl(
            '',
            [
              Validators.required,
              Validators.pattern('^[0-9]*$'),
              Validators.min(this.preguntas[i].fkPregunta.listPosibleRespuestas[0].fkOpcion.rangoInicial),
              Validators.max(this.preguntas[i].fkPregunta.listPosibleRespuestas[0].fkOpcion.rangoFinal),
              RxwebValidators.greaterThan({ fieldName: 'rango_inicial' })
            ]
          )
        }));
      }

    }
  }

  postAnswers() {
    this.analystService.postAnswers(this.current_study, this.current_user, this.respuestas).subscribe((study) => {
      this.router.navigate(['/analytics/requests']);
    }, errorMessage => {
      this.sent_form = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    });
  }

  onSubmit() {
    this.sent_form = true;
    this.respuestas = [];

    for (let index = 0; index < this.answers.controls.length; index++) {
      let currentAnswer: Survey;
      if (this.answers.controls[index].value.respuesta_texto) {
        currentAnswer = new Survey();
        currentAnswer.respuestaTexto = this.answers.controls[index].value.respuesta_texto;
        currentAnswer.fkPregunta = this.preguntas[index].fkPregunta;
        currentAnswer.id_persona = this.current_user;
        currentAnswer.id_estudio = this.current_study;
        this.respuestas.push(currentAnswer);
      }
      // SIMPLE SELECTION
      else if (this.answers.controls[index].value.opcion_seleccionada != null && !Array.isArray(this.answers.controls[index].value.opcion_seleccionada)) {
        currentAnswer = new Survey();
        currentAnswer.fkPregunta = this.preguntas[index].fkPregunta;
        currentAnswer.fkPosibleRespuesta = new PossibleAnswer();
        currentAnswer.fkPosibleRespuesta.fkOpcion = new Option();
        currentAnswer.fkPosibleRespuesta.fkOpcion._id = this.answers.controls[index].value.opcion_seleccionada;
        currentAnswer.fkPosibleRespuesta._id = this.preguntas[index].fkPregunta.listPosibleRespuestas.find(val => val.fkOpcion._id === this.answers.controls[index].value.opcion_seleccionada)._id;
        currentAnswer.id_persona = this.current_user;
        currentAnswer.id_estudio = this.current_study;
        this.respuestas.push(currentAnswer);
      }
      // MULTIPLE SELECTION
      else if (this.answers.controls[index].value.opcion_seleccionada && Array.isArray(this.answers.controls[index].value.opcion_seleccionada)) {
        for (let j = 0; j < this.answers.controls[index].value.opcion_seleccionada.length; j++) {
          currentAnswer = new Survey();
          currentAnswer.fkPregunta = this.preguntas[index].fkPregunta;
          currentAnswer.fkPosibleRespuesta = new PossibleAnswer();
          currentAnswer.fkPosibleRespuesta.fkOpcion = new Option();
          currentAnswer.fkPosibleRespuesta.fkOpcion._id = this.answers.controls[index].value.opcion_seleccionada[j];
          currentAnswer.fkPosibleRespuesta._id = this.preguntas[index].fkPregunta.listPosibleRespuestas.find(val => val.fkOpcion._id === this.answers.controls[index].value.opcion_seleccionada[j])._id;
          currentAnswer.id_persona = this.current_user;
          this.respuestas.push(currentAnswer);
        }
      }
      // RANGE
      else if (this.answers.controls[index].value.rango_inicial && this.answers.controls[index].value.rango_final) {
        currentAnswer = new Survey();
        currentAnswer.fkPregunta = this.preguntas[index].fkPregunta;
        currentAnswer.fkPosibleRespuesta = new PossibleAnswer();
        currentAnswer.respuestaRangoInicial = parseInt(this.answers.controls[index].value.rango_inicial, 10);
        currentAnswer.respuestaRangoFinal = parseInt(this.answers.controls[index].value.rango_final, 10);
        currentAnswer.fkPosibleRespuesta._id = this.preguntas[index].fkPregunta.listPosibleRespuestas[0]._id;
        currentAnswer.id_persona = this.current_user;
        currentAnswer.id_estudio = this.current_study;
        this.respuestas.push(currentAnswer);
      }
    }

    if (this.surveyForm.valid) {
      this.postAnswers();
    }

    else {
      this.sent_form = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Hay campos vacíos o incorrectos en el formulario' });
    }
  }


}
