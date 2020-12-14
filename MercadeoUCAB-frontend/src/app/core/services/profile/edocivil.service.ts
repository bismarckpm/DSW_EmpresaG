import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { EdoCivil } from '../../classes/profile/edocivil';
import { Observable } from 'rxjs';
import { serverURL } from '../../constants/serverURL';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EdocivilService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

    getEdosCiviles(): Observable<EdoCivil[]> {
      return this.http.get<EdoCivil[]>(serverURL + 'edocivil/consulta')
        .pipe(catchError(this.processHTTPMessageService.handleError))
    }
}
