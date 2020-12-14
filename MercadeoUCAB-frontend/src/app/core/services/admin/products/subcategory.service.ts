import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../../constants/serverURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { Subcategory } from '../../../classes/products/subcategory';
import { CategorySubcategory } from '../../../classes/products/category_subcategory';

@Injectable({
  providedIn: 'root'
})
export class SubcategoryService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getALLSubcategories(): Observable<CategorySubcategory[]> {
    return this.http.get<CategorySubcategory[]>(serverURL + 'subcategories/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getSubcategories(category_id): Observable<CategorySubcategory[]> {
    return this.http.get<CategorySubcategory[]>(serverURL + 'subcategories/filtered-by-category', {
      params: {
        category_id: category_id
      }}).pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postSubcategory(subcategory): Observable<CategorySubcategory>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<CategorySubcategory>(serverURL + 'subcategories/add', subcategory, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putSubcategory(subcategory): Observable<CategorySubcategory>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<CategorySubcategory>(serverURL + 'subcategories/update/' + subcategory._id, subcategory, httpOptions)
  }

  deleteSubcategory(subcategory): Observable<CategorySubcategory>{
    return this.http.delete<CategorySubcategory>(serverURL + 'subcategories/delete/' + subcategory._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
