import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Place } from '../classes/place'
import { ProcessHttpMessageService } from './process-http-message.service';
import { baseURL } from '../constants/baseURL'
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getCountries(): Observable<Place[]> {
    return this.http.get<Place[]>(baseURL + 'countries')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getStates(country_id): Observable<Place[]>{
    return this.http.get<Place[]>(baseURL + 'states', {
      params: {
        country_id: country_id
      }
    }).pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getCities(state_id): Observable<Place[]>{
    return this.http.get<Place[]>(baseURL + 'cities', {
      params: {
        state_id: state_id
      }
    }).pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getCounties(city_id): Observable<Place[]>{
    return this.http.get<Place[]>(baseURL + 'counties', {
      params: {
        city_id: city_id
      }
    }).pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
