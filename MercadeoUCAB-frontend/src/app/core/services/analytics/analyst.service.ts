import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { catchError } from 'rxjs/operators';
import { serverURL } from '../../constants/serverURL';
import { Observable } from 'rxjs';
import { persondata } from '../../classes/profile/persondata';
import { QuestionWithStats } from '../../classes/analytics/question_with_stats';
import { Analytics } from '../../classes/analytics/analytics';
import {Survey} from '../../classes/study/survey';

@Injectable({
  providedIn: 'root'
})
export class AnalystService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  getAvailablePopulation(study_id): Observable<persondata[]>{
    return this.http.get<persondata[]>(serverURL + 'survey/available-population/' + study_id)
    .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  isPersonPartOfAvailablePopulation(study_id, person_id): Observable<any>{
    return this.http.get<any>(serverURL + 'survey/available-population/' + study_id + '/' + person_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  isPersonPartOfAvailablePopulationInterview(study_id, person_id): Observable<any>{
    return this.http.get<any>(serverURL + 'survey/available-population-interview/' + study_id + '/' + person_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getOpenTextAnswers(study_id): Observable<QuestionWithStats[]>{
    return this.http.get<QuestionWithStats[]>(serverURL + 'analytics/open-text/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getSelectionAnswers(study_id): Observable<QuestionWithStats[]>{
    return this.http.get<QuestionWithStats[]>(serverURL + 'analytics/selection/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getTrueFalseAnswers(study_id): Observable<QuestionWithStats[]>{
    return this.http.get<QuestionWithStats[]>(serverURL + 'analytics/true-false/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getRangeAnswers(study_id): Observable<QuestionWithStats[]>{
    return this.http.get<QuestionWithStats[]>(serverURL + 'analytics/range/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  postConclusion(study_id, conclusion): Observable<Analytics>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Analytics>(serverURL + 'analytics/add/' + study_id, conclusion, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getAnalysis(study_id): Observable<Analytics>{
    return this.http.get<Analytics>(serverURL + 'analytics/find/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  postAnswers(study_id, person_id, survey): Observable<Survey> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Survey>(serverURL + 'survey/take-interview/' + study_id + '/' + person_id, survey, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
