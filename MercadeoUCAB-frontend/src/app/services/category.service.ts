import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { Category } from '../classes/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(baseURL + 'categories')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postCategory(category): Observable<Category>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Category>(baseURL + 'categories', category, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putCategory(category): Observable<Category>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put<Category>(baseURL + 'categories/' + category.id, category, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deleteCategory(category): Observable<Category>{
    return this.http.delete<Category>(baseURL + 'categories/' + category.id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
