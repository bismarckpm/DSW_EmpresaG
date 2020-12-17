import { Injectable } from '@angular/core';
import { Users } from '../../classes/auth/users';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../constants/serverURL';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../process-http-message.service';
import {Session} from '../../classes/auth/session';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  validateLogin(user: Users): Observable<Session> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Session>(serverURL + 'login/authenticate', user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  logout(token: string): Observable<Users> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Users>(serverURL + 'login/logout/' + token, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
