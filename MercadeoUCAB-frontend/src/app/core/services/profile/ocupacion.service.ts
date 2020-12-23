import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Ocupacion } from '../../classes/profile/ocupacion';
import { serverURL } from '../../constants/serverURL';
import { ProcessHttpMessageService } from '../process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class OcupacionService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

    getOcupaciones(): Observable<Ocupacion[]> {
      return this.http.get<Ocupacion[]>(serverURL + 'ocupacion/consulta')
        .pipe(catchError(this.processHTTPMessageService.handleError))
    }
}
