import { Injectable } from '@angular/core';
import { Recovery } from '../../classes/recovery';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { Observable } from 'rxjs';
import { baseURL } from '../../constants/baseURL';


@Injectable({
  providedIn: 'root'
})
export class RecoveryService {

  correo: String;

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  postRecovery(recovery): Observable<Recovery>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Recovery>(baseURL + 'recovery', recovery, httpOptions)
  }
}
