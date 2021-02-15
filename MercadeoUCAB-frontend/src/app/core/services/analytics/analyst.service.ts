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
import { Respuesta } from '../../classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class AnalystService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  // getAvailablePopulation(study_id): Observable<persondata[]>{
  //   return this.http.get<persondata[]>(serverURL + 'survey/available-population/' + study_id)
  //   .pipe(catchError(this.processHTTPMessageService.handleError));
  // }

  // isPersonPartOfAvailablePopulation(study_id, person_id): Observable<any>{
  //   return this.http.get<any>(serverURL + 'survey/available-population/' + study_id + '/' + person_id)
  //     .pipe(catchError(this.processHTTPMessageService.handleError));
  // }

  // isPersonPartOfAvailablePopulationInterview(study_id, person_id): Observable<any>{
  //   return this.http.get<any>(serverURL + 'survey/available-population-interview/' + study_id + '/' + person_id)
  //     .pipe(catchError(this.processHTTPMessageService.handleError));
  // }

  getAvailablePopulation(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'survey/available-population/' + study_id)
    .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  isPersonPartOfAvailablePopulation(study_id, person_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'survey/available-population/' + study_id + '/' + person_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  isPersonPartOfAvailablePopulationInterview(study_id, person_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'survey/available-population-interview/' + study_id + '/' + person_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }
  
  getOpenTextAnswers(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'analytics/open-text/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getSelectionAnswers(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'analytics/selection/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getTrueFalseAnswers(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'analytics/true-false/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getRangeAnswers(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'analytics/range/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  postConclusion(study_id, conclusion): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Respuesta>(serverURL + 'analytics/add/' + study_id, conclusion, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getAnalysis(study_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'analytics/find/' + study_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  postAnswers(study_id, person_id, survey): Observable<Respuesta> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Respuesta>(serverURL + 'survey/take-interview/' + study_id + '/' + person_id, survey, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }


  getAnswers(study_id, person_id, pregunta_id):Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'answers/' + study_id + "/" + person_id + "/" + pregunta_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

}
