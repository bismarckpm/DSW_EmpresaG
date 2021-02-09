import { Injectable } from '@angular/core';
import { Reset } from '../../classes/auth/reset';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {serverURL} from '../../constants/serverURL';
import { Respuesta } from '../../classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class ResetService {
  clave: String;
  confirmar_clave: String;

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  postReset(reset): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Respuesta>(serverURL + 'recovery/' + reset.token + '/pass/' + reset.clave, reset, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  postVerificar(reset): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Respuesta>(serverURL + 'recovery/' + reset.token, reset, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
