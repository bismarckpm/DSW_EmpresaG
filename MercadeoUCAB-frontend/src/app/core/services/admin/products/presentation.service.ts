import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { serverURL } from '../../../constants/serverURL';
import { TypePresentation } from '../../../classes/products/type_presentation';
import { Respuesta } from 'src/app/core/classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  // getPresentations(): Observable<TypePresentation[]>{
    getPresentations(): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'presentations/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  // postPresentation(presentation): Observable<TypePresentation>{
    postPresentation(presentation): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Respuesta>(serverURL + 'presentations/add', presentation, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  // putPresentation(presentation): Observable<TypePresentation>{
    putPresentation(presentation): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put<Respuesta>(serverURL + 'presentations/update/' + presentation._id, presentation, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  // deletePresentation(presentation): Observable<TypePresentation>{
  deletePresentation(presentation): Observable<Respuesta>{
    return this.http.delete<Respuesta>(serverURL + 'presentations/delete/' + presentation._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
