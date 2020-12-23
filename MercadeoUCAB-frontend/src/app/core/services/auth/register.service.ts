import { Injectable } from '@angular/core';
import { Person } from '../../classes/profile/person';
import { persondata } from '../../classes/profile/persondata';
import { Child } from '../../classes/profile/child';
import { serverURL } from '../../constants/serverURL';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { EdoCivil } from '../../classes/profile/edocivil';
import { Genero } from '../../classes/profile/genero';
import { Place } from '../../classes/profile/place';
import { telefono } from '../../classes/profile/telefono';
import { Disponibilidad } from '../../classes/profile/disponibilidad';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  hijos: Child[] = null;
  edocivil: EdoCivil = {
    _id: 0,
    nombre: '',
  };
  genero: Genero = {
    _id: 0,
    nombre: '',
  };

  pais: Place = {
    _id: 0,
    nombre: '',
  };
  estado: Place = {
    _id: 0,
    nombre: '',
  };
  ciudad: Place = {
    _id: 0,
    nombre: '',
  };
  parroquia: Place = {
    _id: 0,
    nombre: '',
  };
  lugar: Place = {
    _id: 0,
    nombre: '',
  };

  telefono: telefono = {
    numero: 0,
  };



  horario_ini: Disponibilidad = {
    _id: 0,
    hora: null,
  };
  horario_fin: Disponibilidad = {
    _id: 0,
    hora: null,
  };

  personaData: persondata = {
    hijos: this.hijos,
    fkEdoCivil: this.edocivil,
    fkGenero: this.genero,
    id_pais: this.pais,
    id_estado: this.estado,
    id_ciudad: this.ciudad,
    id_parroquia: this.parroquia,
    fkLugar: this.lugar,
    codigo_pais: 0,
    telefono: this.telefono,
    id_horario_inicial: this.horario_ini,
    id_horario_final: this.horario_fin
  };

  user: Person = {
    email: '',
    password: '',
    fkPersona: this.personaData
  };

  constructor(private http: HttpClient,
    private processHTTPMessageService: ProcessHttpMessageService) {
  }

  postRegister(user: Person): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    // return this.http.post<Person>(baseURL + 'register', this.user, httpOptions)
    return this.http.post<Person>(serverURL + 'sesion/register', this.user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError))
  }

  postValidRegister(): Observable<Person>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<Person>(serverURL + 'sesion/validRegister', this.user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

}
