import { Injectable } from '@angular/core';
import { Users } from '../../classes/auth/users';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { serverURL } from '../../constants/serverURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  validateLogin(user: Users): Observable<Users> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Users>(serverURL + 'login/' + user.email + '/link/' + user.password, user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }


}
