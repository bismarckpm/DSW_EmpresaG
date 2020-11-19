import { Injectable } from '@angular/core';
import { Reset } from '../classes/reset';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { Observable } from 'rxjs';
import { baseURL } from '../constants/baseURL';

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
    return this.http.post<Reset>(baseURL + 'reset', reset, httpOptions)
  }
}
