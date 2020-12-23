import { Injectable } from '@angular/core';
import { Recovery } from '../../classes/auth/recovery';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { Observable } from 'rxjs';
import {serverURL} from '../../constants/serverURL';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecoveryService {

  correo: String;

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  postRecovery(recovery: Recovery): Observable<Recovery>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Recovery>(serverURL + 'recovery/' + recovery.correo, recovery, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
