import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProcessHttpMessageService } from './process-http-message.service';
import { baseURL } from '../constants/baseURL';
import { catchError, map } from 'rxjs/operators';
import { serverURL } from '../constants/serverURL';
import { Observable } from 'rxjs';
import { persondata } from '../classes/persondata';
import { Survey } from '../classes/survey';
import { QuestionWithStats } from '../classes/analytics/question_with_stats';
import { Analytics } from '../classes/analytics/analytics';

@Injectable({
  providedIn: 'root'
})
export class AnalystService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getStats(study_id){
    return this.http.get(baseURL + 'stats', {params: {
      id: study_id
    }})
      .pipe(map(study => study[0]))
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getAvailablePopulation(study_id): Observable<persondata[]>{
    return this.http.get<persondata[]>(serverURL + 'survey/available-population/' + study_id)
    .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  isPersonPartOfAvailablePopulation(study_id, person_id): Observable<any>{
    return this.http.get<any>(serverURL + 'survey/available-population/' + study_id + '/' + person_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getOpenTextAnswers(study_id): Observable<QuestionWithStats[]>{
    return this.http.get<QuestionWithStats[]>(serverURL + 'analytics/open-text/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
  
  getSelectionAnswers(study_id): Observable<QuestionWithStats[]>{
    return this.http.get<QuestionWithStats[]>(serverURL + 'analytics/selection/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getTrueFalseAnswers(study_id): Observable<QuestionWithStats[]>{
    return this.http.get<QuestionWithStats[]>(serverURL + 'analytics/true-false/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getRangeAnswers(study_id): Observable<QuestionWithStats[]>{
    return this.http.get<QuestionWithStats[]>(serverURL + 'analytics/range/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postConclusion(study_id, conclusion): Observable<Analytics>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Analytics>(serverURL + 'analytics/add/' + study_id, conclusion, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getAnalysis(study_id): Observable<Analytics>{
    return this.http.get<Analytics>(serverURL + 'analytics/find/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
