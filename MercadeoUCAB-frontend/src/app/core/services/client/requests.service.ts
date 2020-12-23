import { Injectable } from '@angular/core';
import { StudyRequest } from '../../classes/study/study_request';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { RequestWithFilter } from '../../classes/study/request_with_filter';
import { serverURL } from '../../constants/serverURL';
import { StudyWithFilter } from '../../classes/study/study_with_filter';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  getRequests(): Observable<RequestWithFilter[]> {
    return this.http.get<RequestWithFilter[]>(serverURL + 'requests/all')
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getUserRequest(person_id, request_id): Observable<RequestWithFilter>{
    return this.http.get<RequestWithFilter>(serverURL + 'requests/find-specific-by-user/' + person_id + '/' + request_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getUserRequests(person_id): Observable<RequestWithFilter[]>{
    return this.http.get<RequestWithFilter[]>(serverURL + 'requests/find-by-user/' + person_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getRequest(rid): Observable<RequestWithFilter>{
    return this.http.get<RequestWithFilter>(serverURL + 'requests/find/' + rid)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  updateStatus(study_request): Observable<StudyWithFilter>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<StudyWithFilter>(serverURL + 'requests/update-status/' + study_request._id, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  deleteRequest(request): Observable<RequestWithFilter>{
    return this.http.delete<RequestWithFilter>(serverURL + 'requests/delete/' + request._id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  postRequest(request: RequestWithFilter): Observable<RequestWithFilter>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<RequestWithFilter>(serverURL + 'requests/add' , request, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  putRequest(request): Observable<Request>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Request>(serverURL + 'requests/update/' + request._id, request, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
