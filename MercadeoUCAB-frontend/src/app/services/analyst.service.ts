import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProcessHttpMessageService } from './process-http-message.service';
import { baseURL } from '../constants/baseURL';
import { catchError, map } from 'rxjs/operators';

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
}
