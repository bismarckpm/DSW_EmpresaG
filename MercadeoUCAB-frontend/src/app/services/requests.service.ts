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

  getRequest(rid): Observable<Request>{
    return this.http.get<Request>(baseURL + 'requests', {params: {
      id: rid
    }})
      .pipe(map(request => request[0]))
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postRequest(request): Observable<Request>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Request>(baseURL + 'requests' , request, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putRequest(request): Observable<Request>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Request>(baseURL + 'requests/' + request.id, request, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
