import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table'
import { MenuItem, MessageService } from 'primeng/api';
import { QuestionService } from '../services/question.service';
import { CategoryService } from '../services/category.service';
import { ConfirmationService } from 'primeng/api';
import { replaceKey } from '../functions/common_functions';
import { QuestionCategorySubcategory } from '../classes/question_category_subcategory';
import { QUESTION_TYPES } from '../constants/question_types';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class QuestionsComponent implements OnInit {
  preguntas: QuestionCategorySubcategory[];
  categorias: MenuItem[];
  question_types = QUESTION_TYPES;


  questionsErrorMessage: string;
  categoryErrorMessage: string;
  subcategoryErrorMessage: string;


  loading: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(
    private questionService: QuestionService,
    private categoryService: CategoryService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService) {
    }

  ngOnInit(): void {
    this.loading = true;
    this.questionService.getQuestions().subscribe((questions) => {
        this.preguntas = questions; 
        this.loading = false;
      },
      errorMessage => {
        this.loading = false;
        this.questionsErrorMessage = errorMessage;
      })

    this.categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKey(categories);
    },
    errorMessage => {
      this.categoryErrorMessage = errorMessage;
    })
  }

  onQuestionTypeChange(event){
    this.table.filter(event.value, 'fkPregunta.fkTipoPregunta._id', 'in')
  }

  onCategoryChange(event){
    this.table.filter(event.value, 'fkCategoria.nombre', 'in')
  }

  deleteQuestion(question){
    this.confirmationService.confirm({
      message: 'La siguiente pregunta: <code>' + question.fkPregunta.pregunta + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.questionService.deleteQuestion(question).subscribe((q) => {

            let index = this.preguntas.indexOf(question)
            if (index > -1)
              this.preguntas.splice(index, 1);

            this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Pregunta eliminada con éxito'});
            
          }, errorMessage => {
            this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
          })
      },
      reject: () => {
          //
      }
  });
}
}
