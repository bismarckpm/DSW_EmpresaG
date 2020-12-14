import { Injectable } from '@angular/core';
import { Person } from '../../classes/person';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../../constants/baseURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getPerson(pid): Observable<Person>{
    return this.http.get<Person>(baseURL + 'register', {params: {
      id: pid
    }})
      .pipe(map(person => person[0]))
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
