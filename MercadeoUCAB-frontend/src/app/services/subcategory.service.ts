import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { Subcategory } from '../classes/subcategory';

@Injectable({
  providedIn: 'root'
})
export class SubcategoryService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getALLSubcategories(): Observable<Subcategory[]> {
    return this.http.get<Subcategory[]>(baseURL + 'subcategories')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getSubcategories(category_id): Observable<Subcategory[]> {
    return this.http.get<Subcategory[]>(baseURL + 'subcategories', {
      params: {
        id_categoria: category_id
      }}).pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postSubcategory(subcategory): Observable<Subcategory>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Subcategory>(baseURL + 'subcategories', subcategory, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putSubcategory(subcategory): Observable<Subcategory>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Subcategory>(baseURL + 'subcategories/' + subcategory.id, subcategory, httpOptions)
  }

  deleteSubcategory(subcategory): Observable<Subcategory>{
    return this.http.delete<Subcategory>(baseURL + 'subcategories/' + subcategory.id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
