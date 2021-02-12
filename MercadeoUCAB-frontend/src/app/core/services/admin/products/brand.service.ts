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
export class BrandService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getALLBrands(): Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'brands/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getBrands(subcategory_id): Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'brands/filtered-by-subcategory', {
      params: {
        subcategory_id: subcategory_id
      }
    }).pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postBrand(brand): Observable<Respuesta> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Respuesta>(serverURL + 'brands/add', brand, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putBrand(brand): Observable<Respuesta> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Respuesta>(serverURL + 'brands/update/' + brand._id, brand, httpOptions)
  }

  deleteBrand(brand): Observable<Respuesta> {
    return this.http.delete<Respuesta>(serverURL + 'brands/delete/' + brand._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
