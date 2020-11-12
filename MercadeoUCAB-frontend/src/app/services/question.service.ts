import { Injectable } from '@angular/core';
import { Question } from '../classes/question';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getQuestions(): Observable<Question[]>{
    return this.http.get<Question[]>(baseURL + 'questions')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deleteQuestion(question): Observable<Question>{
    return this.http.delete<Question>(baseURL + 'questions', {
      params: {
        id: question.id
      }
    })
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
