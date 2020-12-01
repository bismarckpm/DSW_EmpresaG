import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { Brand } from '../classes/brand';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getALLBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>(baseURL + 'brands')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getBrands(subcategory_id): Observable<Brand[]> {
    return this.http.get<Brand[]>(baseURL + 'brands', {
      params: {
        id_subcategoria: subcategory_id
      }
    }).pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postBrand(brand): Observable<Brand> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Brand>(baseURL + 'brands', brand, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putBrand(brand): Observable<Brand> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Brand>(baseURL + 'brands/' + brand.id, brand, httpOptions)
  }

  deleteBrand(brand): Observable<Brand> {
    return this.http.delete<Brand>(baseURL + 'brands/' + brand.id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
