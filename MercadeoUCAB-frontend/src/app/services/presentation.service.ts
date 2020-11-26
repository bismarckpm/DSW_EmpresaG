import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { Presentation } from '../classes/presentation';
import { map, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { baseURL } from '../constants/baseURL';

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getPresentations(): Observable<Presentation[]>{
    return this.http.get<Presentation[]>(baseURL + 'presentations')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postPresentation(presentation): Observable<Presentation>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Presentation>(baseURL + 'presentations', presentation, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putPresentation(presentation): Observable<Presentation>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put<Presentation>(baseURL + 'presentations/' + presentation.id, presentation, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deletePresentation(presentation): Observable<Presentation>{
    return this.http.delete<Presentation>(baseURL + 'presentations/' + presentation.id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
