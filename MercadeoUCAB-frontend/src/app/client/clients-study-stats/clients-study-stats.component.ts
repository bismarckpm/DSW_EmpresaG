import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { AnalystService } from '../../services/analytics/analyst.service';
import { Study } from '../../classes/study';
import { Question } from 'src/app/classes/question';
import { AnalyticData } from 'src/app/classes/analytics/analytic_data';
import { StudiesService } from 'src/app/services/admin/studies/studies.service';

@Component({
  selector: 'app-clients-study-stats',
  templateUrl: './clients-study-stats.component.html',
  styleUrls: ['./clients-study-stats.component.scss']
})
export class ClientsStudyStatsComponent implements OnInit {
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


  constructor(private activatedRoute: ActivatedRoute,
    private router: Router,
    private analystService: AnalystService,
    private studiesService: StudiesService,
    private spinner: NgxSpinnerService) {
    this.background_colors = ['#42A5F5', '#439f78', '#FF6384', '#6a6085', '#FFCE56', '#4bc0c0', '#E7E9ED', '#a0b6fe', '#f87f38', '#d5ffd1']
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

      // TODO: In progress studies cannot be visualized
      // TODO: Studies that were not requested by this client cannot be visualized
      this.analystService.getStats(this.current_study).subscribe((study) => {
        if (study){
          //console.log(study)
          this.spinner.hide();
          this.study = study;
          this.open_text_questions = this.study.preguntas.filter(this.isOpenText)

          this.selection_questions = this.study.preguntas.filter(this.isSelection)
          this.selectionDataset();

          this.true_false_questions = this.study.preguntas.filter(this.isTrueFalse)
          this.trueFalseDataset();

          this.range_questions = this.study.preguntas.filter(this.isRange)
          this.rangeDataset();

          this.loading = false;

        }

        else {
          this.router.navigate(['404']);
        }

        // TODO: Get open text responses in a table
      }, errorMessage => {
        this.loading = false;
        this.estudioErrorMessage = errorMessage;
      })
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
