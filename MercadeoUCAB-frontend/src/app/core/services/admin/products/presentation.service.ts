import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { serverURL } from '../../../constants/serverURL';
import { TypePresentation } from '../../../classes/products/type_presentation';

@Injectable({
  providedIn: 'root'
})
export class PresentationService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getPresentations(): Observable<TypePresentation[]>{
    return this.http.get<TypePresentation[]>(serverURL + 'presentations/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postPresentation(presentation): Observable<TypePresentation>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<TypePresentation>(serverURL + 'presentations/add', presentation, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putPresentation(presentation): Observable<TypePresentation>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put<TypePresentation>(serverURL + 'presentations/update/' + presentation._id, presentation, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deletePresentation(presentation): Observable<TypePresentation>{
    return this.http.delete<TypePresentation>(serverURL + 'presentations/delete/' + presentation._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
