import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../../constants/serverURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { Subcategory } from '../../../classes/products/subcategory';
import { CategorySubcategory } from '../../../classes/products/category_subcategory';
import { Respuesta } from 'src/app/core/classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class SubcategoryService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  // getALLSubcategories(): Observable<CategorySubcategory[]> {
  getALLSubcategories(): Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'subcategories/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  // getSubcategories(category_id): Observable<CategorySubcategory[]> {
    getSubcategories(category_id): Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'subcategories/filtered-by-category', {
      params: {
        category_id: category_id
      }}).pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postSubcategory(subcategory): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Respuesta>(serverURL + 'subcategories/add', subcategory, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putSubcategory(subcategory): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Respuesta>(serverURL + 'subcategories/update/' + subcategory._id, subcategory, httpOptions)
  }

  deleteSubcategory(subcategory): Observable<Respuesta>{
    return this.http.delete<Respuesta>(serverURL + 'subcategories/delete/' + subcategory._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
