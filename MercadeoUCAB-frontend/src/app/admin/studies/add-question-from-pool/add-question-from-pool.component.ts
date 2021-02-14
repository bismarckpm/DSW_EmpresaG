import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { MessageService } from 'primeng/api';
import { QUESTION_TYPES } from '../../../core/constants/question_types';
import { QuestionService } from '../../../core/services/admin/studies/question.service';
import { SubcategoryService } from '../../../core/services/admin/products/subcategory.service';
import { QuestionCategorySubcategory } from 'src/app/core/classes/study/question_category_subcategory';
import { CategorySubcategory } from 'src/app/core/classes/products/category_subcategory';
import { ProcessHttpMessageService } from 'src/app/core/services/process-http-message.service';

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
  subcategorias: any[];
  question_types = QUESTION_TYPES;

  /* Table */
  loading = false;
  @ViewChild('dt') table: Table;

  /* Category id */
  @Input() category_id: number;

  constructor(
    private messageService: MessageService,
    private questionService: QuestionService,
    private subcategoryService: SubcategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    this.questionService.getQuestionsByCategory(this.category_id).subscribe((questions) => {
      if (questions.codigo == 0){
        this.preguntas = questions.objeto as QuestionCategorySubcategory[];
        this.loading = false;
      }else{
        this.loading = false;
        this.questionsErrorMessage = questions.mensaje;
      }
    },
      errorMessage => {
        this.loading = false;
        this.questionsErrorMessage = errorMessage;
      });

    this.subcategoryService.getSubcategories(this.category_id).subscribe((respuesta) => {
      var subcategories = respuesta.objeto as CategorySubcategory[];
      console.log(subcategories);
      this.subcategorias = [];
      for (let i = 0; i < subcategories.length; i++) {
        this.subcategorias.push({
          value: subcategories[i].fkSubcategoria._id,
          label: subcategories[i].fkSubcategoria.nombre
        });
      }
    },
      errorMessage => {
        this.categoryErrorMessage = errorMessage;
      });
  }

  onSubcategoryChange(event) {
    this.table.filter(event.value, 'fkSubcategoria._id', 'in');
  }

  onQuestionTypeChange(event) {
    this.table.filter(event.value, 'fkPregunta.fkTipoPregunta._id', 'in');
  }

  closeView() {
    this.onViewClose.emit(1);
  }

  selectQuestion(question: QuestionCategorySubcategory) {
    /* STATUS = 2: Cloned question
    Clone question so the modification doesn't affect other studies */
    this.questionService.cloneQuestion(question).subscribe((q) => {
      if (q.codigo == 0){
        question._id = (q.objeto as QuestionCategorySubcategory)._id;
        question.fkPregunta._id = (q.objeto as QuestionCategorySubcategory).fkPregunta._id;
        this.onQuestionSelect.emit(question);
      }else{
        this.messageService.add({severity: 'error', summary: 'Error', detail: q.mensaje});
      }
    });
  }

}
