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

@Component({
  selector: 'app-take-survey-user',
  templateUrl: './take-survey-user.component.html',
  styleUrls: ['./take-survey-user.component.scss'],
  providers: [MessageService]
})
export class TakeSurveyUserComponent implements OnInit {
  estudio: Study;
  current_study: number;
  sent_form: boolean = false;
  loading: boolean = true;

  estudioErrorMessage: string;

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

  // TODO: 
  // OPTION 1: Bring the whole available surveys list, verify if the ID of the survey in query param exists in the list, if not,
  // redirect to 404

  // OPTION 2: Verify from backend if survey wasn't already taken or it's not available and return a flag from the server

  // TODO:
  // In radio button you have to change [value] for the ID of the option when backend is integrated

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private userSurveyService: UserSurveyService,
    private studiesService: StudiesService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService) {

      /* If query is empty return 404 */
      if ((this.activatedRoute.snapshot.queryParamMap.get('surveyId')||0) == 0){
        this.router.navigate(['404']);
      }

      /* Get current question */
      else {
        this.spinner.show();
        this.current_study = parseInt(this.activatedRoute.snapshot.queryParamMap.get('surveyId'));

        this.studiesService.getStudy(this.current_study).subscribe((study) => {
          this.loading = false;
          //this.estudio = study;
          this.spinner.hide();
          this.createForm();
        }, errorMessage => {
          this.loading = false;
          this.spinner.hide();
          this.estudioErrorMessage = errorMessage;
        })
      }
    }

  ngOnInit(): void {
  }

  createForm(){
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

  get answers(){
    return this.surveyForm.get('answers') as FormArray;
  }

  getUserAnswers(){
    // Push according to the number of questions present
    for (var i = 0; i < this.estudio.preguntas.length; i++){
      // OPEN TEXT
      if (this.estudio.preguntas[i].id_tipo == 1){
        this.answers.push(new FormGroup ({
          respuesta_texto: new FormControl('', Validators.required)
        }))
      }

      // SIMPLE SELECTION, MULTIPLE SELECTION, TRUE/FALSE

      else if (this.estudio.preguntas[i].id_tipo == 2 || this.estudio.preguntas[i].id_tipo == 3 || this.estudio.preguntas[i].id_tipo == 4){
        this.answers.push(new FormGroup ({
          opcion_seleccionada: new FormControl(null, Validators.required)
        }))
      }

      // RANGE

      else{
        this.answers.push(new FormGroup({
          rango_inicial: new FormControl(
            '',
            [
              Validators.required,
              Validators.pattern('^[0-9]*$'),
              RxwebValidators.lessThan({fieldName: 'rango_final'}),
              Validators.min(this.estudio.preguntas[i].opciones[0].rangoInicial),
              Validators.max(this.estudio.preguntas[i].opciones[0].rangoFinal)
            ]
          ),

          rango_final: new FormControl(
            '',
            [
              Validators.required,
              Validators.pattern('^[0-9]*$'),
              RxwebValidators.greaterThan({fieldName: 'rango_inicial'}),
              Validators.min(this.estudio.preguntas[i].opciones[0].rangoInicial),
              Validators.max(this.estudio.preguntas[i].opciones[0].rangoFinal)
            ]
          )
        }))
      }

    }
  }

  postAnswers(){
    this.userSurveyService.postAnswers(this.estudio).subscribe((study)=> {
      this.router.navigate(["available-surveys"])
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
    })
  }

  onSubmit(){
    // TODO: Get current user
    // OPTION 1: Manage that part from the backend
    // OPTION 2: Send current user's ID to the backend
    this.sent_form = true;
    for (var index = 0; index < this.answers.controls.length ; index++){
      this.estudio.preguntas[index].respuestas = new Answer();
      if (this.answers.controls[index].value.respuesta_texto){
        this.estudio.preguntas[index].respuestas.respuesta_texto = this.answers.controls[index].value.respuesta_texto;
        
      }
      else if (this.answers.controls[index].value.opcion_seleccionada){
        this.estudio.preguntas[index].respuestas.opcion_seleccionada = this.answers.controls[index].value.opcion_seleccionada;
      }
      else if (this.answers.controls[index].value.rango_inicial && this.answers.controls[index].value.rango_final){
        this.estudio.preguntas[index].respuestas.rango_inicial = parseInt(this.answers.controls[index].value.rango_inicial);
        this.estudio.preguntas[index].respuestas.rango_final = parseInt(this.answers.controls[index].value.rango_final);
      }
    }
    
    if (this.surveyForm.valid){
      this.postAnswers()
    }

    else {
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Hay campos vacíos o incorrectos en el formulario'});
    }
  }

}
