import { Injectable } from '@angular/core';
import { Reset } from '../../classes/auth/reset';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { Observable } from 'rxjs';
import {serverURL} from '../../constants/serverURL';

@Injectable({
  providedIn: 'root'
})
export class ResetService {
  clave: String;
  confirmar_clave: String;

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  postReset(reset): Observable<Reset>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Reset>(serverURL + 'reset', reset, httpOptions);
  }
}
