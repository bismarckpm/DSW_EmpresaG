import { Injectable } from '@angular/core';
import { Person } from '../classes/person';
import { Child } from '../classes/child';
import { Users } from '../classes/users';
import { Device } from '../constants/device';

import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../constants/baseURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../services/process-http-message.service';
import { persondata } from '../classes/persondata';
import { Genero } from '../classes/genero';
import { EdoCivil } from '../classes/edocivil';
import { Place } from '../classes/place';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  hijos: Child[] = null;

  device: Device[] = null;

  genero: Genero = null;
  edoCivil: EdoCivil = null;
  fklugar: Place = null;

  personadata: persondata = {
    primerNombre: '',
    primerApellido: '',
    documentoIdentidad: '',
    fkGenero: this.genero,
    fkEdoCivil: this.edoCivil,
    fechaNacimiento: '',
    // id_pais: 0,
    // id_ciudad: 0,
    // id_parroquia: 0,
    // id_estado: 0,
    fkLugar: this.fklugar,
    telefono: 0,
    ocupacion: '',
    personas_hogar: 0,
    hijos: this.hijos,
    id_nivel_academico: 0,
    id_nivel_socioeconomico: 0,
    dispositivos: this.device,
    id_horario_inicial: 0,
    id_horario_final: 0
  }

  persona: Person = {
    email: '',
    password: '',
    fkPersona: this.personadata
  };

  // Me falta ROL

  user: Users = {
    email: '',
    password: '',
    status: 0,
    persona: this.persona
  };


  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getPerson(pid): Observable<Person>{
    return this.http.get<Person>(baseURL + 'register', {params: {
      id: pid
    }})
      .pipe(map(persona => persona[0]))
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
  
  getPersons(): Observable<Person[]>{
    return this.http.get<Person[]>(baseURL + 'register')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  getNewPerson(pid): Observable<Person>{
    return this.http.get<Person>(baseURL + 'register', {params: {
      id: pid
    }})
      .pipe(map(person => person[0]))
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postPerson(person): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Person>(baseURL + 'register', person, httpOptions)
  }

  postRegPerson(user: Person): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Person>(baseURL + 'register', this.user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  deleteUser(user): Observable<Person>{
    return this.http.delete<Person>(baseURL + 'register/' + user.id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

}
