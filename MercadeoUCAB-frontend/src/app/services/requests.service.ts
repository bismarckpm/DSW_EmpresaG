import { Injectable } from '@angular/core';
import { Request } from '../classes/request';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(baseURL + 'requests')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
