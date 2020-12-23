import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Place, State, City } from '../../classes/profile/place';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { map, catchError } from 'rxjs/operators';
import { serverURL } from '../../constants/serverURL';

@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  constructor(private http: HttpClient,
              private processHTTPMessageService: ProcessHttpMessageService) { }

  getCountries(): Observable<Place[]> {
    return this.http.get<Place[]>(serverURL + 'lugar/consulta/0')
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getStates(country_id): Observable<Place[]>{
    return this.http.get<Place[]>(serverURL + 'lugar/consulta/' + country_id)
    .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getCities(state_id): Observable<Place[]>{
    return this.http.get<Place[]>(serverURL + 'lugar/consulta/' + state_id)
    .pipe(catchError(this.processHTTPMessageService.handleError));
  }

  getCounties(city_id): Observable<Place[]>{
    return this.http.get<Place[]>(serverURL + 'lugar/consulta/' + city_id)
    .pipe(catchError(this.processHTTPMessageService.handleError));
  }
}
