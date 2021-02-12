import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../../constants/serverURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { Respuesta } from '../../../classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getCategories(): Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'categories/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postCategory(category): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Respuesta>(serverURL + 'categories/add', category, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putCategory(category): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put<Respuesta>(serverURL + 'categories/update/' + category._id, category, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deleteCategory(category): Observable<Respuesta>{
    return this.http.delete<Respuesta>(serverURL + 'categories/delete/' + category._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
