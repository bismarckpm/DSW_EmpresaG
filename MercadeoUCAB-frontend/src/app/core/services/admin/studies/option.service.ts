import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Option } from '../../../classes/study/options';
import { serverURL } from '../../../constants/serverURL';
import { ProcessHttpMessageService } from '../../process-http-message.service';

@Injectable({
  providedIn: 'root'
})
export class OptionService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  deleteOption(option): Observable<Option>{
    return this.http.delete(serverURL + 'options/delete/' + option._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
