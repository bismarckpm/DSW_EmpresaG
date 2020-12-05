import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Place, State, City } from '../classes/place'
import { ProcessHttpMessageService } from './process-http-message.service';
import { baseURL } from '../constants/baseURL'
import { map, catchError } from 'rxjs/operators';
import { serverURL } from '../constants/serverURL';

@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getCountries(): Observable<Place[]> {
    return this.http.get<Place[]>(serverURL + 'lugar/consulta/0')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getStates(country_id): Observable<Place[]>{
    // return this.http.get<Place[]>(baseURL + 'states', {
    //   params: {
    //     country_id: country_id
    //   }
    // }).pipe(catchError(this.processHTTPMessageService.handleError))
    return this.http.get<Place[]>(serverURL + 'lugar/consulta/'+country_id)
    .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getState(state_id): Observable<State>{
    return this.http.get<State>(baseURL + 'states', {
      params: {
        id: state_id
      }
    })
      .pipe(map(state => state[0]))
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getCities(state_id): Observable<Place[]>{
    // return this.http.get<Place[]>(baseURL + 'cities', {
    //   params: {
    //     state_id: state_id
    //   }
    // }).pipe(catchError(this.processHTTPMessageService.handleError))
    return this.http.get<Place[]>(serverURL + 'lugar/consulta/'+state_id)
    .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getCounties(city_id): Observable<Place[]>{
    // return this.http.get<Place[]>(baseURL + 'counties', {
    //   params: {
    //     city_id: city_id
    //   }
    // }).pipe(catchError(this.processHTTPMessageService.handleError))
    return this.http.get<Place[]>(serverURL + 'lugar/consulta/'+city_id)
    .pipe(catchError(this.processHTTPMessageService.handleError))
  }
}
