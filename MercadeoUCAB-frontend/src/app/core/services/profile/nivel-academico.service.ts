import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AcademicLevel } from '../../classes/profile/academic_level';
import { serverURL } from '../../constants/serverURL';
import { ProcessHttpMessageService } from '../process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class NivelAcademicoService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

    getNivelesAcademicos(): Observable<AcademicLevel[]> {
      return this.http.get<AcademicLevel[]>(serverURL + 'nivelacademico/consulta')
        .pipe(catchError(this.processHTTPMessageService.handleError))
    }
}
