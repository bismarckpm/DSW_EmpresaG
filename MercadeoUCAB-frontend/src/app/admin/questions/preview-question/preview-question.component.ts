import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { QuestionService } from '../../../core/services/admin/studies/question.service';
import { Question } from '../../../core/classes/study/question';
import { NgxSpinnerService } from "ngx-spinner";
import { QuestionCategorySubcategory } from 'src/app/core/classes/study/question_category_subcategory';

@Component({
  selector: 'app-preview-question',
  templateUrl: './preview-question.component.html',
  styleUrls: ['./preview-question.component.scss']
})
export class PreviewQuestionComponent implements OnInit {
  pregunta: QuestionCategorySubcategory;
  loading: boolean = true;
  current_question: number;
  questionErrorMessage: string;

  constructor(private Activatedroute: ActivatedRoute,
    private questionService: QuestionService,
    private spinner: NgxSpinnerService,
    private router: Router) {
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('questionId') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current question */
    else {
      this.spinner.show();
      this.current_question = parseInt(this.Activatedroute.snapshot.queryParamMap.get('questionId'));
      this.questionService.getQuestion(this.current_question).subscribe((question) => {
        this.pregunta = question;
        if (this.pregunta) {
          this.loading = false;
          this.spinner.hide();
        }

        else {
          this.router.navigate(['404']);
        }
      });
    }
  }

  ngOnInit(): void {
  }
}
