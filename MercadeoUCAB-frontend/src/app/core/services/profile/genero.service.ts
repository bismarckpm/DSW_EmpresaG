import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { Genero } from '../../classes/profile/genero';
import { Observable } from 'rxjs';
import { serverURL } from '../../constants/serverURL';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

    getGeneros(): Observable<Genero[]> {
      return this.http.get<Genero[]>(serverURL + 'genero/consulta')
        .pipe(catchError(this.processHTTPMessageService.handleError))
    }
}
