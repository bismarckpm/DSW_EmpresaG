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
export class UserService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getPerson(): Observable<Person[]>{
    return this.http.get<Person[]>(baseURL + 'register')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postPerson(person): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Person>(baseURL + 'register', person, httpOptions)
  }

  deleteUser(user): Observable<Person>{
    return this.http.delete<Person>(baseURL + 'register/' + user.documento_de_identificacion)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

}
