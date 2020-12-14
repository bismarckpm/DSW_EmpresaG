import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { serverURL } from '../../../constants/serverURL';
import { BrandType } from '../../../classes/products/brand_type';

@Injectable({
  providedIn: 'root'
})
export class TypesService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getALLTypes(): Observable<BrandType[]> {
    return this.http.get<BrandType[]>(serverURL + 'types/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deleteType(product_type): Observable<BrandType>{
    return this.http.delete<BrandType>(serverURL + 'types/delete/' + product_type._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postType(product_type): Observable<BrandType>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<BrandType>(serverURL + 'types/add', product_type, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putType(product_type): Observable<BrandType>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put<BrandType>(serverURL + 'types/update/' + product_type._id, product_type, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
