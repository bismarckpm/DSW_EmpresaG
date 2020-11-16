import { Injectable } from '@angular/core';
import { Study } from '../classes/study';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class StudiesService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getStudies(): Observable<Study[]> {
    return this.http.get<Study[]>(baseURL + 'studies')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getStudy(sid): Observable<Study>{
    return this.http.get<Study>(baseURL + 'studies', {params: {
      id: sid
    }})
    .pipe(map(study => study[0]))
    .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  appendQuestionToStudy(sid, study, qid): Observable<Study>{
    return null;
  }
}
