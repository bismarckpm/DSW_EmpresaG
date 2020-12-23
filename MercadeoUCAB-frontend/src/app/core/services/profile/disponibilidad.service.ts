import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Disponibilidad } from '../../classes/profile/disponibilidad';
import { serverURL } from '../../constants/serverURL';
import { ProcessHttpMessageService } from '../process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class DisponibilidadService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

    getDisponibilidades(): Observable<Disponibilidad[]> {
      return this.http.get<Disponibilidad[]>(serverURL + 'disponibilidad/consulta')
        .pipe(catchError(this.processHTTPMessageService.handleError))
    }
}
