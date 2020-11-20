import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { ProductType } from '../classes/productType';
import { map, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { baseURL } from '../constants/baseURL';

@Injectable({
  providedIn: 'root'
})
export class TypesService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getTypes(): Observable<ProductType[]> {
    return this.http.get<ProductType[]>(baseURL + 'types')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
