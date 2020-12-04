import { Injectable } from '@angular/core';
import { Question } from '../classes/question';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../constants/serverURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { QuestionCategorySubcategory } from '../classes/question_category_subcategory';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getQuestions(): Observable<QuestionCategorySubcategory[]>{
    return this.http.get<QuestionCategorySubcategory[]>(serverURL + 'questions/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getQuestionsByCategory(category_id): Observable<QuestionCategorySubcategory[]>{
    return this.http.get<QuestionCategorySubcategory[]>(serverURL + 'questions', {params: {
      id_categoria: category_id
    }})
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getQuestion(qid): Observable<QuestionCategorySubcategory>{
    return this.http.get<QuestionCategorySubcategory>(serverURL + 'questions/find/' + qid)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postQuestion(question): Observable<QuestionCategorySubcategory>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<QuestionCategorySubcategory>(serverURL + 'questions/add', question, httpOptions)
  }

  putQuestion(question): Observable<QuestionCategorySubcategory>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<QuestionCategorySubcategory>(serverURL + 'questions/update/' + question._id, question, httpOptions)
  }

  deleteQuestion(question): Observable<QuestionCategorySubcategory>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<QuestionCategorySubcategory>(serverURL + 'questions/delete/' + question._id, question, httpOptions)
  }
}
