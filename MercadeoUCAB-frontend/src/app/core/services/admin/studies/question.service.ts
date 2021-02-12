import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../../constants/serverURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { Respuesta } from 'src/app/core/classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  getQuestions(): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'questions/all')
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getQuestionsByCategory(category_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'questions/all-by-category/' + category_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getQuestion(qid): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'questions/find/' + qid)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  postQuestion(question): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Respuesta>(serverURL + 'questions/add', question, httpOptions);
  }

  cloneQuestion(question): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Respuesta>(serverURL + 'questions/clone/' + question._id , question, httpOptions);
  }

  putQuestion(question): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Respuesta>(serverURL + 'questions/update/' + question._id, question, httpOptions);
  }

  deleteQuestion(question): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Respuesta>(serverURL + 'questions/delete/' + question._id, question, httpOptions);
  }
}
