import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Rol } from '../../classes/profile/rol';
import { serverURL } from '../../constants/serverURL';
import { ProcessHttpMessageService } from '../process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

    getRoles(): Observable<Rol[]> {
      return this.http.get<Rol[]>(serverURL + 'rol/consulta')
        .pipe(catchError(this.processHTTPMessageService.handleError))
    }
}
