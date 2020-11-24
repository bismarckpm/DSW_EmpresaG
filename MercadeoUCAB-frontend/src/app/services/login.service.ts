import { Injectable } from '@angular/core';
import { Person } from '../classes/person';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  user: Person = {
    correo_electronico: '',
    clave: ''
  };

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  validateLogin(user: Person): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Person>(baseURL + 'login', this.user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }


}
