import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../../constants/serverURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../../process-http-message.service';
import { Brand } from '../../../classes/products/brand';
import { SubcategoryBrand } from '../../../classes/products/subcategory_brand';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getALLBrands(): Observable<SubcategoryBrand[]> {
    return this.http.get<SubcategoryBrand[]>(serverURL + 'brands/all')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getBrands(subcategory_id): Observable<SubcategoryBrand[]> {
    return this.http.get<SubcategoryBrand[]>(serverURL + 'brands/filtered-by-subcategory', {
      params: {
        subcategory_id: subcategory_id
      }
    }).pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postBrand(brand): Observable<SubcategoryBrand> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<SubcategoryBrand>(serverURL + 'brands/add', brand, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putBrand(brand): Observable<SubcategoryBrand> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<SubcategoryBrand>(serverURL + 'brands/update/' + brand._id, brand, httpOptions)
  }

  deleteBrand(brand): Observable<SubcategoryBrand> {
    return this.http.delete<SubcategoryBrand>(serverURL + 'brands/delete/' + brand._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
