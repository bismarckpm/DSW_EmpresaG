import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { serverURL } from '../constants/serverURL';
import { Survey } from '../classes/survey';

@Injectable({
  providedIn: 'root'
})
export class UserSurveyService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  postAnswers(study_id, person_id, survey): Observable<Survey> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Survey>(serverURL + 'survey/take/' + study_id + '/' + person_id, survey, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
