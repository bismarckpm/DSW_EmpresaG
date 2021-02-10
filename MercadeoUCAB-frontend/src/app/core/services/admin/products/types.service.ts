import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { serverURL } from '../../../constants/serverURL';
import { BrandType } from '../../../classes/products/brand_type';
import { Respuesta } from 'src/app/core/classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class TypesService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getALLTypes(): Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'types/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deleteType(product_type): Observable<Respuesta>{
    return this.http.delete<Respuesta>(serverURL + 'types/delete/' + product_type._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postType(product_type): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Respuesta>(serverURL + 'types/add', product_type, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putType(product_type): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.put<Respuesta>(serverURL + 'types/update/' + product_type._id, product_type, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
