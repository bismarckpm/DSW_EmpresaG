import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";
import { StudiesService } from '../../core/services/admin/studies/studies.service';
import { Location } from '@angular/common';
import { StudyWithFilter } from 'src/app/core/classes/study/study_with_filter';
import { QuestionCategorySubcategory } from 'src/app/core/classes/study/question_category_subcategory';

@Component({
  selector: 'app-study-preview',
  templateUrl: './study-preview.component.html',
  styleUrls: ['./study-preview.component.scss']
})
export class StudyPreviewComponent implements OnInit {
  loading: boolean = true;
  current_study: number;
  estudio: StudyWithFilter;
  preguntas: QuestionCategorySubcategory[];
  studyErrorMessage: string;
  questionsErrorMessage: string;

  constructor(private Activatedroute: ActivatedRoute,
    private router: Router,
    private location: Location,
    private studiesService: StudiesService,
    private spinner: NgxSpinnerService) {
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('studyId') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current study */
    else {
      this.spinner.show();

      this.current_study = parseInt(this.Activatedroute.snapshot.queryParamMap.get('studyId'));
      this.studiesService.getStudy(this.current_study).subscribe((study) => {

        if (!study) {
          this.router.navigate(['404']);
        }

        this.estudio = study;

        this.spinner.hide();
        this.loading = false;

        this.studiesService.getStudyQuestions(this.current_study).subscribe((questions) => {
          this.preguntas = questions;
        }, errorMessage => {
          this.questionsErrorMessage = errorMessage;
        })

      }, errorMessage => {
        this.loading = false;
        this.spinner.hide();
        this.studyErrorMessage = errorMessage;
      });
    }
  }

  ngOnInit(): void {
  }

  backClicked() {
    this.location.back();
  }

}
