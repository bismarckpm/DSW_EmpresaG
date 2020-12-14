import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PhoneCode } from '../../classes/profile/phonecode';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { catchError } from 'rxjs/operators';
import {serverURL} from '../../constants/serverURL';

@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  getCodes(): Observable<PhoneCode[]>{
    return this.http.get<PhoneCode[]>(serverURL + 'phonecodes')
    .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
