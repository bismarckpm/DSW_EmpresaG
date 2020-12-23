import { Injectable } from '@angular/core';
import { Person } from '../../classes/profile/person';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../process-http-message.service';
import {serverURL} from '../../constants/serverURL';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  getPerson(pid): Observable<Person>{
    return this.http.get<Person>(serverURL + 'register', {params: {
      id: pid
    }})
      .pipe(map(person => person[0]))
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
