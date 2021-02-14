import {Component, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { AnalystService } from '../../core/services/analytics/analyst.service';
import { AnalyticData } from 'src/app/core/classes/analytics/analytic_data';
import { StudiesService } from 'src/app/core/services/admin/studies/studies.service';
import { StudyWithFilter } from '../../core/classes/study/study_with_filter';
import { Analytics } from '../../core/classes/analytics/analytics';
import { QuestionWithStats } from '../../core/classes/analytics/question_with_stats';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-clients-study-stats',
  templateUrl: './clients-study-stats.component.html',
  styleUrls: ['./clients-study-stats.component.scss']
})
export class ClientsStudyStatsComponent implements OnInit {
  current_study: number;
  study: StudyWithFilter;
  conclusion: Analytics;
  open_text_questions: QuestionWithStats[];
  selection_questions: QuestionWithStats[];
  true_false_questions: QuestionWithStats[];
  range_questions: QuestionWithStats[];

  true_false_dataset: AnalyticData[] = [];
  selection_dataset: AnalyticData[] = [];
  range_dataset: any[] = [];
  background_colors: string[] = [];

  loading = true;
  estudioErrorMessage: string;
  analisisErrorMessage: string;
  toolbar: any;
  show_editor = false;
  sent_form = false;

  conclusionForm: FormGroup;
  @ViewChild('cform') conclusionFormDirective;

  formErrors = {
    conclusion: ''
  };

  validationMessages = {
    conclusion: {
      required: 'Conclusión es requerida',
      minlength: 'Conclusión no puede ser menor a 50 caracteres',
      maxlength: 'Conclusión no puede ser mayor a 3000 caracteres',
    }
  };

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private analystService: AnalystService,
              private studiesService: StudiesService,
              private fb: FormBuilder,
              private spinner: NgxSpinnerService) {
    this.background_colors = ['#42A5F5', '#439f78', '#FF6384', '#6a6085', '#FFCE56', '#4bc0c0', '#E7E9ED', '#a0b6fe', '#f87f38', '#d5ffd1'];
    this.toolbar = {
      toolbar: [
        ['bold', 'italic', 'underline'],
        [{ list: 'ordered' }, { list: 'bullet' }],
        [{ color: ['#000000', '#439f78', '#FF6384', '#003399'] }, { background: ['#ffff00', '#99ff99', '#ff99cc'] }],
        ['blockquote'],
        [{ size: ['small', false, 'large'] }]
      ],
    };
  }

  ngOnInit(): void {
    /* If query is empty return 404 */
    if ((this.activatedRoute.snapshot.queryParamMap.get('studyId') || 0) === 0) {
      this.router.navigate(['404']);
    }

    /* Get current question */
    else {
      this.spinner.show();
      this.current_study = parseInt(this.activatedRoute.snapshot.queryParamMap.get('studyId'));

      this.analystService.getOpenTextAnswers(this.current_study).subscribe((open_text_questions) => {
        if (open_text_questions.codigo == 0){
        this.open_text_questions = open_text_questions.objeto as QuestionWithStats[];

        this.analystService.getSelectionAnswers(this.current_study).subscribe((selection_questions) => {
            if (selection_questions.codigo == 0){
              this.selection_questions = selection_questions.objeto as QuestionWithStats[];
              this.selectionDataset();

              this.analystService.getTrueFalseAnswers(this.current_study).subscribe((true_false) => {
                if (true_false.codigo == 0){
                  this.true_false_questions = true_false.objeto as QuestionWithStats[];
                  this.trueFalseDataset();


                  this.analystService.getRangeAnswers(this.current_study).subscribe((range) => {
                    if (range.codigo == 0){
                      this.range_questions = range.objeto as QuestionWithStats[];
                      this.rangeDataset();

                      this.studiesService.getStudy(this.current_study).subscribe((study) => {
                        if(study.codigo == 0){
                          this.study = study.objeto as StudyWithFilter;

                          if (this.study.fkEstudio.estado === 1){
                            this.createForm();
                          }
                          else {
                            this.conclusion = new Analytics();
                            this.analystService.getAnalysis(this.current_study).subscribe((conclusion) => {
                              this.conclusion = conclusion.objeto as Analytics;
                            });
                          }

                          this.loading = false;
                          this.spinner.hide();
                      }else{
                        this.estudioErrorMessage = study.mensaje;
                      }
                      }, errorMessage => {
                        this.estudioErrorMessage = errorMessage;
                      });
                    }
                    else{
                      this.analisisErrorMessage = range.mensaje;
                    }
                  }, e => this.analisisErrorMessage = e);
                }
                else{
                  this.analisisErrorMessage = true_false.mensaje;
                }
              }, e => this.analisisErrorMessage = e);
            }else{
              this.analisisErrorMessage = selection_questions.mensaje;
            }
          }, e => this.analisisErrorMessage = e);
        }else{
          this.analisisErrorMessage = open_text_questions.mensaje;
        }
      }, e => this.analisisErrorMessage = e);
    }
  }

  createForm() {
    this.conclusionForm = this.fb.group({
      conclusion: [
        '',
        [
          Validators.required,
          Validators.minLength(50),
          Validators.maxLength(3000)
        ]
      ]
    });

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

  trueFalseDataset() {
    for (let i = 0; i < this.true_false_questions.length; i++) {
      this.true_false_dataset.push({
        labels: [this.true_false_questions[i].fkPregunta.listOpciones[0].valor, this.true_false_questions[i].fkPregunta.listOpciones[1].valor],
        datasets: [
          {
            data: [this.true_false_questions[i].fkPregunta.listOpciones[0].numeroDePersonas,
              this.true_false_questions[i].fkPregunta.listOpciones[1].numeroDePersonas],
            backgroundColor: [
              '#36A2EB',
              '#FF6384',
            ],
          }
        ]
      });
    }
  }

  selectionDataset() {
    for (let i = 0; i < this.selection_questions.length; i++) {
      const labels: string[] = [];
      const data: number[] = [];
      const colors: string[] = [];
      for (let j = 0; j < this.selection_questions[i].fkPregunta.listOpciones.length; j++) {
        labels.push(this.selection_questions[i].fkPregunta.listOpciones[j].valor);
        data.push(this.selection_questions[i].fkPregunta.listOpciones[j].numeroDePersonas);
        // Avoid index error if there are more than 10 options
        colors.push(this.background_colors[(this.background_colors.length + j) % this.background_colors.length]);
      }
      this.selection_dataset.push({
        labels,
        datasets: [
          {
            data,
            backgroundColor: colors
          }
        ]
      });
    }
  }

  rangeDataset() {
    for (let i = 0; i < this.range_questions.length; i++) {
      this.range_dataset.push({
        min_average: this.range_questions[i].promedioRangoInicial,
        max_average: this.range_questions[i].promedioRangoFinal
      });
    }
  }
}
