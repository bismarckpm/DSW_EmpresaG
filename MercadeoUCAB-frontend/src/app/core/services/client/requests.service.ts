import { Injectable } from '@angular/core';
import { StudyRequest } from '../../classes/study/study_request';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { RequestWithFilter } from '../../classes/study/request_with_filter';
import { serverURL } from '../../constants/serverURL';
import { StudyWithFilter } from '../../classes/study/study_with_filter';
import { Respuesta } from '../../classes/respuesta';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  // getRequests(): Observable<RequestWithFilter[]> {
    getRequests(): Observable<Respuesta> {
    return this.http.get<Respuesta>(serverURL + 'requests/all')
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  // getUserRequest(person_id, request_id): Observable<RequestWithFilter>{
    getUserRequest(person_id, request_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'requests/find-specific-by-user/' + person_id + '/' + request_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  // getUserRequests(person_id): Observable<RequestWithFilter[]>{
    getUserRequests(person_id): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'requests/find-by-user/' + person_id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  // getRequest(rid): Observable<RequestWithFilter>{
    getRequest(rid): Observable<Respuesta>{
    return this.http.get<Respuesta>(serverURL + 'requests/find/' + rid)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  // updateStatus(study_request): Observable<StudyWithFilter>{
    updateStatus(study_request): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Respuesta>(serverURL + 'requests/update-status/' + study_request._id, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  // deleteRequest(request): Observable<RequestWithFilter>{
    deleteRequest(request): Observable<Respuesta>{
    return this.http.delete<Respuesta>(serverURL + 'requests/delete/' + request._id)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  // postRequest(request: RequestWithFilter): Observable<RequestWithFilter>{
    postRequest(request: RequestWithFilter): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<Respuesta>(serverURL + 'requests/add' , request, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  // putRequest(request): Observable<Request>{
    putRequest(request): Observable<Respuesta>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.put<Respuesta>(serverURL + 'requests/update/' + request._id, request, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
