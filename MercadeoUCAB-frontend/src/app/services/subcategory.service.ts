import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
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

  deleteSubcategory(subcategory): Observable<Subcategory>{
    return this.http.delete<Subcategory>(baseURL + 'subcategories/' + subcategory.id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
