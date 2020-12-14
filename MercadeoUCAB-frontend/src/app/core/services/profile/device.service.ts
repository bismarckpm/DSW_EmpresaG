import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Device } from '../../constants/device';
import { serverURL } from '../../constants/serverURL';
import { ProcessHttpMessageService } from '../process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

    getDevices(): Observable<Device[]> {
      return this.http.get<Device[]>(serverURL + 'dispositivo/consulta')
        .pipe(catchError(this.processHTTPMessageService.handleError));
    }
}
