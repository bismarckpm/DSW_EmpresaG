import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../../constants/serverURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { Category } from '../../../classes/products/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(serverURL + 'categories/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postCategory(category): Observable<Category>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Category>(serverURL + 'categories/add', category, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putCategory(category): Observable<Category>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put<Category>(serverURL + 'categories/update/' + category._id, category, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deleteCategory(category): Observable<Category>{
    return this.http.delete<Category>(serverURL + 'categories/delete/' + category._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
