import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { Study } from '../classes/study';
import { map, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { baseURL } from '../constants/baseURL';

@Injectable({
  providedIn: 'root'
})
export class UserSurveyService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  postAnswers(study): Observable<Study> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Study>(baseURL + 'take-survey', study, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
