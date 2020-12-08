import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProcessHttpMessageService } from './process-http-message.service';
import { baseURL } from '../constants/baseURL';
import { catchError, map } from 'rxjs/operators';
import { serverURL } from '../constants/serverURL';
import { Observable } from 'rxjs';
import { persondata } from '../classes/persondata';

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
}
