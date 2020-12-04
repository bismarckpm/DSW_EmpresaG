import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Question } from '../../classes/question';
import { Table } from 'primeng/table';
import { MenuItem } from 'primeng/api';
import { QUESTION_TYPES } from '../../constants/question_types';
import { QuestionService } from '../../services/question.service';
import { SubcategoryService } from '../../services/subcategory.service';
import { replaceKey } from '../../functions/common_functions';
import { QuestionCategorySubcategory } from 'src/app/classes/question_category_subcategory';

@Component({
  selector: 'app-add-question-from-pool',
  templateUrl: './add-question-from-pool.component.html',
  styleUrls: ['./add-question-from-pool.component.scss']
})

export class AddQuestionFromPoolComponent implements OnInit {
  @Output() onQuestionSelect = new EventEmitter<any>();
  @Output() onViewClose = new EventEmitter<any>();

  preguntas: QuestionCategorySubcategory[];
  questionsErrorMessage: string;
  categoryErrorMessage: string;
  subcategorias: MenuItem[];
  question_types = QUESTION_TYPES;

  /* Table */
  loading: boolean = false;
  @ViewChild('dt') table: Table;

  /* Category id */
  @Input() category_id: number;

  constructor(
    private questionService: QuestionService,
    private subcategoryService: SubcategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    this.questionService.getQuestionsByCategory(this.category_id).subscribe((questions) => {
        this.preguntas = questions; 
        this.loading = false;
      },
      errorMessage => {
        this.loading = false;
        this.questionsErrorMessage = errorMessage;
      })

    this.subcategoryService.getSubcategories(this.category_id).subscribe((subcategories) => {
      this.subcategorias = replaceKey(subcategories);
    },
    errorMessage => {
      this.categoryErrorMessage = errorMessage;
    })
  }

  onSubcategoryChange(event){
    this.table.filter(event.value, 'categoria', 'in')
  }

  onQuestionTypeChange(event){
    this.table.filter(event.value, 'tipo', 'in')
  }

  closeView(){
    this.onViewClose.emit(1);
  }

  selectQuestion(question){
    delete question['id'];
    /* Clone question so the modification doesn't affect other studies */
    this.questionService.postQuestion(question).subscribe((q) => {
      this.onQuestionSelect.emit(q);
    })
  }

}
