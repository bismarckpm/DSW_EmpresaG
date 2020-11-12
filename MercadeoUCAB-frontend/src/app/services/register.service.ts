import { Injectable } from '@angular/core';
import { Person } from '../classes/person';
import { Child } from '../classes/child';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  hijos: Child[] = null;

  user: Person = {
    correo_electronico: '',
    clave: '',
    hijos: this.hijos
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

    return this.http.post<Person>(baseURL + 'register', this.user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
