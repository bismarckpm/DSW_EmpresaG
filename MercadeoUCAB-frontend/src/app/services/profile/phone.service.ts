import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PhoneCode } from '../../classes/phonecode'
import { ProcessHttpMessageService } from '../process-http-message.service';
import { baseURL } from '../../constants/baseURL'
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getCodes(): Observable<PhoneCode[]>{
    return this.http.get<PhoneCode[]>(baseURL + 'phonecodes')
    .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
