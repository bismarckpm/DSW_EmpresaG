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
import { telefono } from '../classes/telefono';
import { Rol } from '../classes/rol';
import { Disponibilidad } from '../classes/disponibilidad';
import { serverURL } from '../constants/serverURL';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  hijos: Child[] = null;

  device: Device[] = null;

  // genero: Genero = null;
  // edoCivil: EdoCivil = null;

  // pais: Place = null;
  // ciudad: Place = null;
  // estado: Place = null;
  // Parroquia: Place = null;
  edoCivil: EdoCivil = {
    nombre: '',
    _id: 0,
  };
  genero: Genero = {
    nombre: '',
    _id: 0,
  };

  pais: Place = {
    nombre: '',
    _id: 0,
  };
  estado: Place = {
    nombre: '',
    _id: 0,
  };
  ciudad: Place = {
    nombre: '',
    _id: 0,
  };
  Parroquia: Place = {
    nombre: '',
    _id: 0,
  };
  lugar: Place = {
    nombre: '',
    _id: 0,
  };


  Telefono: telefono = {
    numero: 0,
  }

  horario_ini: Disponibilidad = {
    _id: 0,
    hora: null,
  };
  horario_fin: Disponibilidad = {
    _id: 0,
    hora: null,
  };

  personadata: persondata = {
    primerNombre: '',
    primerApellido: '',
    documentoIdentidad: '',
    fkGenero: this.genero,
    fkEdoCivil: this.edoCivil,
    fechaNacimiento: '01/01/1900',
    id_pais: this.pais,
    id_ciudad: this.ciudad,
    id_parroquia: this.Parroquia,
    id_estado: this.estado,
    fkLugar: this.lugar,
    telefono: this.Telefono,
    ocupacion: '0',
    numero_personas_encasa: 0,
    hijos: this.hijos,
    id_nivel_academico: 0,
    id_nivel_socioeconomico: 0,
    dispositivos: this.device,
    id_horario_inicial: this.horario_ini,
    id_horario_final: this.horario_fin
  }

  rol: Rol = {
    _id: 0,
    nombre: '',
  }

  persona: Person = {
    _id: 0,
    email: '',
    password: '',
    estado: 1,
    fkPersona: this.personadata,
    fkRol: this.rol,
  };

  user: Users = {
    _id: 0,
    email: '',
    password: '',
    estado: 1,
    persona: this.persona
  };


  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) { }

  getPerson(pid): Observable<Person>{
    return this.http.get<Person>(serverURL + 'user/'+pid)
      // .pipe(map(persona => persona[0]))
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }
  
  getPersons(): Observable<Person[]>{
    return this.http.get<Person[]>(serverURL + 'user')
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  // getNewPerson(pid): Observable<Person>{
  //   return this.http.get<Person>(baseURL + 'register', {params: {
  //     id: pid
  //   }})
  //     .pipe(map(person => person[0]))
  //     .pipe(catchError(this.processHTTPMessageService.handleError))
  // }

  postPerson(person): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Person>(serverURL + 'user/add', person, httpOptions)
    .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  // postRegPerson(user: Person): Observable<Person>{
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json'
  //     })
  //   };

  //   return this.http.post<Person>(baseURL + 'register', this.user, httpOptions)
  //     .pipe(catchError(this.processHTTPMessageService.handleError))
  // }

  deleteUser(user): Observable<Person>{
    return this.http.delete<Person>(serverURL + 'user/delete/' + user._id)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  putUser(persona: Person): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.put<Person>(serverURL + 'user/edit/' + persona._id, persona, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

}
