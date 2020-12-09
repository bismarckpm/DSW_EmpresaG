import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { AnalystService } from '../../services/analyst.service';
import { Study } from '../../classes/study';
import { Question } from 'src/app/classes/question';
import { AnalyticData } from 'src/app/classes/analytic_data';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { StudiesService } from 'src/app/services/studies.service';

// TODO: Show only X kind of questions

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class StatisticsComponent implements OnInit {
  current_study: number;
  study: Study;
  open_text_questions: Question[];
  selection_questions: Question[];
  true_false_questions: Question[];
  range_questions: Question[];

  true_false_dataset: AnalyticData[] = [];
  selection_dataset: AnalyticData[] = [];
  range_dataset: any[] = [];
  background_colors: string[] = [];

  loading:boolean = true;
  estudioErrorMessage: string;
  toolbar: any;
  show_editor: boolean = false;
  sent_form: boolean = false;

  conclusionForm: FormGroup;
  @ViewChild('cform') conclusionFormDirective;

  formErrors = {
    'conclusion': ''
  };

  validationMessages = {
    'conclusion': {
      'required': "Conclusión es requerida",
      'minlength': "Conclusión no puede ser menor a 50 caracteres",
      'maxlength': "Conclusión no puede ser mayor a 3000 caracteres",
    }
  }

  constructor(private activatedRoute: ActivatedRoute,
    private router: Router,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private analystService: AnalystService,
    private studiesService: StudiesService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService) {
    this.background_colors = ['#42A5F5', '#439f78', '#FF6384', '#6a6085', '#FFCE56', '#4bc0c0', '#E7E9ED', '#a0b6fe', '#f87f38', '#d5ffd1']
    this.toolbar = {
      toolbar: [
        ['bold', 'italic', 'underline'],
        [{'list': 'ordered'}, {'list': 'bullet'}],
        [{'color': ['#000000', '#439f78', '#FF6384', '#003399']}, {'background': ['#ffff00', '#99ff99', '#ff99cc']} ],
        ['blockquote'],
        [{'size': ['small', false, 'large']}]
      ], 
    }
  }

  ngOnInit(): void {
    /* If query is empty return 404 */
    if ((this.activatedRoute.snapshot.queryParamMap.get('studyId') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current question */
    else {
      this.spinner.show();
      this.current_study = parseInt(this.activatedRoute.snapshot.queryParamMap.get('studyId'));

      this.analystService.getStats(this.current_study).subscribe((study) => {
        if (study){
          console.log(study)
          this.spinner.hide();
          this.study = study;
          this.open_text_questions = this.study.preguntas.filter(this.isOpenText)
  
          this.selection_questions = this.study.preguntas.filter(this.isSelection)
          this.selectionDataset();
  
          this.true_false_questions = this.study.preguntas.filter(this.isTrueFalse)
          this.trueFalseDataset();
  
          this.range_questions = this.study.preguntas.filter(this.isRange)
          this.rangeDataset();

          this.createForm();
  
          this.loading = false;
  
        }

        else {
          //this.router.navigate(['404']);
        }
        
        // TODO: Get open text responses in a table
      }, errorMessage => {
        this.loading = false;
        this.estudioErrorMessage = errorMessage;
      })
    }
  }

  createForm(){
    this.conclusionForm = this.fb.group({
      conclusion: [
        '',
        [
          Validators.required,
          Validators.minLength(50),
          Validators.maxLength(3000)
        ]
      ]
    })

    this.conclusionForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any) {
    /* If form hasn't been created */
    if (!this.conclusionForm) {
      return;
    }

    const form = this.conclusionForm;
    for (const field in this.formErrors) {
      if (this.formErrors.hasOwnProperty(field)) {
        // clear previous error message if any
        this.formErrors[field] = '';
        const control = form.get(field);

        // if field is modified by user
        if (control && control.dirty && !control.valid) {
          const messages = this.validationMessages[field];

          // check if i'm adding the error message to the field
          for (const key in control.errors) {
            if (control.errors.hasOwnProperty(key)) {
              this.formErrors[field] += messages[key] + ' ';
            }
          }
        }
      }
    }
  }

  putStudy(){
    this.studiesService.putStudy(this.study).subscribe((s) => {
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Estudio analizado con éxito'});
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  onSubmit(){
    this.sent_form = true;
    if (this.conclusionForm.valid){
      this.study.conclusion = this.conclusionForm.value.conclusion
      this.confirmationService.confirm({
        message: '¿Está seguro que desea concluir el estudio? No podrá modificar la conclusión después',
        header: 'Confirmación',
        icon: 'pi pi-info-circle',
        accept: () => {
            //this.study.id_estado = 3;
            this.putStudy();
        },
        reject: () => {
          this.sent_form = false;
          return;
        }
    });
    }
    else {
      this.messageService.add({severity:'error', summary: 'Error', detail: 'El campo conclusión debe ser válido'});
      this.sent_form = false;
    }
  }

  isOpenText(el): boolean {
    return el.id_tipo == 1;
  }

  isSelection(el): boolean {
    return el.id_tipo == 2 || el.id_tipo == 3;
  }

  isTrueFalse(el): boolean {
    return el.id_tipo == 4;
  }

  isRange(el): boolean {
    return el.id_tipo == 5;
  }

  trueFalseDataset() {
    for (var i = 0; i < this.true_false_questions.length; i++) {
      this.true_false_dataset.push({
        labels: ['Verdaero', 'Falso'],
        datasets: [
          {
            data: [this.true_false_questions[i].estadisticas.n_personas_verdadero,
                  this.true_false_questions[i].estadisticas.n_personas_falso],
            backgroundColor: [
              "#FF6384",
              "#36A2EB",
            ],
          }
        ]
      })
    }
  }

  selectionDataset() {
    for (var i = 0; i < this.selection_questions.length; i++) {
      let labels: string[] = [];
      let data: number[] = [];
      let colors: string[] = [];
      for (var j = 0; j < this.selection_questions[i].opciones.length; j++) {
        labels.push(this.selection_questions[i].opciones[j].valor)
        data.push(this.selection_questions[i].opciones[j].estadisticas.n_personas_respondieron)
        // Avoid index error if there are more than 10 options
        colors.push(this.background_colors[(this.background_colors.length+j) % this.background_colors.length])
      }
      this.selection_dataset.push({
        labels: labels,
        datasets: [
          {
            data: data,
            backgroundColor: colors
          }
        ]
      })
    }
  }

  rangeDataset(){
    for (var i = 0; i<this.range_questions.length; i++){
      this.range_dataset.push({
        min_average: this.range_questions[i].opciones[0].estadisticas.promedio_rango_inicial,
        max_average: this.range_questions[i].opciones[0].estadisticas.promedio_rango_final
      })
    }
  }
}
