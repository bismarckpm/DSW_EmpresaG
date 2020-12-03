import { Injectable } from '@angular/core';
import { Person } from '../classes/person';
import { persondata } from '../classes/persondata';
import { Child } from '../classes/child';
import { serverURL } from '../constants/serverURL';
import { from, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  hijos: Child[] = null;

  personaData: persondata = {
    hijos: this.hijos
  };

  user: Person = {
    email: '',
    password: '',
    fkPersona: this.personaData
  };

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) {
  }

  postRegister(user: Person): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    // return this.http.post<Person>(baseURL + 'register', this.user, httpOptions)
    return this.http.post<Person>(serverURL + 'sesion/register', this.user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
