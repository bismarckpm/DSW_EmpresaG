import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { Table } from 'primeng/table'
import { MenuItem, PrimeNGConfig } from 'primeng/api';
import { Question } from '../classes/question';
import { QUESTION_TYPES_FOR_TABLE_FILTER } from '../constants/question_types';
import { QuestionService } from '../services/question.service';
import { CategoryService } from '../services/category.service';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss'],
  providers: [ConfirmationService]
})
export class QuestionsComponent implements OnInit {
  preguntas: Question[];
  categorias: MenuItem[];
  question_types = QUESTION_TYPES_FOR_TABLE_FILTER;
  questionsErrorMessage: string;
  categoryErrorMessage: string;
  subcategoryErrorMessage: string;
  loading: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(
    private questionService: QuestionService,
    private categoryService: CategoryService,
    private confirmationService: ConfirmationService,
    private primeNgConfig: PrimeNGConfig) {

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
      this.categorias = this.replaceKey(categories);
    },
    errorMessage => {
      this.categoryErrorMessage = errorMessage;
    })
  }

  replaceKey(obj){
    return obj.map(({ nombre: value, nombre: label, ...rest }) => ({ value, label, ...rest }));
  }

  onQuestionTypeChange(event){
    this.table.filter(event.value, 'tipo', 'in')
  }

  onCategoryChange(event){
    this.table.filter(event.value, 'categoria', 'in')
  }

  deleteQuestion(question){
    this.confirmationService.confirm({
      message: 'La siguiente pregunta: <code>' + question.pregunta + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          //
      },
      reject: () => {
          //
      }
  });
  }

}
