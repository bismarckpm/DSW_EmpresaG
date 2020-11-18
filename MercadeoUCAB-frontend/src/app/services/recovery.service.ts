import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { Observable } from 'rxjs';
import { baseURL } from '../constants/baseURL';


@Injectable({
  providedIn: 'root'
})
export class RecoveryService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  postRecovery(correo): Observable<String>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<String>(baseURL + 'correo', correo, httpOptions)
  }
}
