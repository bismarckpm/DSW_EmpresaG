import { Component, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators'
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { StudiesService } from 'src/app/services/studies.service';
import { UserSurveyService } from '../../services/user-survey.service';
import { Study } from '../../classes/study';
import { Answer } from 'src/app/classes/answer';
import { MessageService } from 'primeng/api';
import { Person } from 'src/app/classes/person';
import { QuestionCategorySubcategory } from 'src/app/classes/question_category_subcategory';
import { Question } from 'src/app/classes/question';
import { StudyQuestion } from 'src/app/classes/study_question';

@Component({
  selector: 'app-make-interview',
  templateUrl: './make-interview.component.html',
  styleUrls: ['./make-interview.component.scss'],
  providers: [MessageService]
})
export class MakeInterviewComponent implements OnInit {
  estudio: Study;
  persona: Person;
  preguntas: StudyQuestion[];
  current_study: number;
  current_user: number;
  sent_form: boolean = false;
  loading: boolean = true;

  estudioErrorMessage: string;
  questionsErrorMessage: string;

  /* Form */
  surveyForm: FormGroup;
  @ViewChild('sform') surveyFormDirective;

  validationMessages = {
    'respuesta_texto': {
      'required': 'Campo es requerido',
    },
    'opcion_seleccionada': {
      'required': 'Debe seleccionar una opción'
    },
    'rango_inicial': {
      'required': 'Rango inicial es requerido',
      'pattern': 'Rango debe ser numérico',
      'lessThan': 'Rango inicial debe ser menor que rango final',
      'min': 'Rango inicial no puede ser menor que el valor permitido',
      'max': 'Rango inicial no puede ser mayor que el valor permitido'
    },
    'rango_final': {
      'required': 'Rango final es requerido',
      'pattern': 'Rango debe ser numérico',
      'greaterThan': 'Rango final debe ser mayor que rango inicial',
      'min': 'Rango final no puede ser menor que el valor permitido',
      'max': 'Rango final no puede ser mayor que el valor permitido'
    }
  }

  // TODO: See take-survey-user todo

  constructor(private activatedRoute: ActivatedRoute,
    private router: Router,
    private userSurveyService: UserSurveyService,
    private studiesService: StudiesService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService) {
    /* If query is empty return 404 */
    if ((this.activatedRoute.snapshot.queryParamMap.get('surveyId') || 0) == 0) {
      //this.router.navigate(['404']);
    }

    else {
      this.spinner.show();
      this.current_study = parseInt(this.activatedRoute.snapshot.queryParamMap.get('surveyId'));

      this.studiesService.getStudyQuestionsWithOptions(this.current_study).subscribe((questions) => {
        this.preguntas = questions;
        console.log(questions)
        /* TODO: Is current user part of the available population? */
        if ((this.activatedRoute.snapshot.queryParamMap.get('personId') || 0) == 0) {
          //this.router.navigate(['404']);
        }
        else {
          this.current_user = parseInt(this.activatedRoute.snapshot.queryParamMap.get('personId'));
          this.loading = false;
          this.spinner.hide();
          this.createForm();
        }

      }, errorMessage => {
        this.questionsErrorMessage = errorMessage;
        this.loading = false;
        this.spinner.hide();
      })
    }
  }

  ngOnInit(): void {
  }

  createForm() {
    this.surveyForm = this.fb.group({
      answers: new FormArray([])
    })

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
    for (var i = 0; i < this.preguntas.length; i++) {
      // OPEN TEXT
      if (this.preguntas[i].fkPregunta.fkTipoPregunta._id == 1) {
        this.answers.push(new FormGroup({
          respuesta_texto: new FormControl('', Validators.required)
        }))
      }

      // SIMPLE SELECTION, MULTIPLE SELECTION, TRUE/FALSE

      else if (this.preguntas[i].fkPregunta.fkTipoPregunta._id == 2 || this.preguntas[i].fkPregunta.fkTipoPregunta._id == 3 || this.preguntas[i].fkPregunta.fkTipoPregunta._id == 4) {
        this.answers.push(new FormGroup({
          opcion_seleccionada: new FormControl(null, Validators.required)
        }))
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
        }))
      }

    }
  }

  postAnswers() {
    this.userSurveyService.postAnswers(this.estudio).subscribe((study) => {
      this.router.navigate(["analysis-requests"])
    }, errorMessage => {
      this.sent_form = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    })
  }

  onSubmit() {
    this.sent_form = true;
    for (var index = 0; index < this.answers.controls.length; index++) {
      this.estudio.preguntas[index].respuestas = new Answer();
      if (this.answers.controls[index].value.respuesta_texto) {
        this.estudio.preguntas[index].respuestas.respuesta_texto = this.answers.controls[index].value.respuesta_texto;
      }
      else if (this.answers.controls[index].value.opcion_seleccionada) {
        this.estudio.preguntas[index].respuestas.opcion_seleccionada = this.answers.controls[index].value.opcion_seleccionada;
      }
      else if (this.answers.controls[index].value.rango_inicial && this.answers.controls[index].value.rango_final) {
        this.estudio.preguntas[index].respuestas.rango_inicial = parseInt(this.answers.controls[index].value.rango_inicial);
        this.estudio.preguntas[index].respuestas.rango_final = parseInt(this.answers.controls[index].value.rango_final);
      }

      this.estudio.preguntas[index].respuestas.usuario_id = this.current_user;
    }

    if (this.surveyForm.valid) {
      this.postAnswers()
    }

    else {
      this.sent_form = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Hay campos vacíos o incorrectos en el formulario' });
    }
  }


}
