import { Injectable } from '@angular/core';
import { Person } from '../../classes/person';
import { persondata } from '../../classes/persondata';
import { Child } from '../../classes/child';
import { serverURL } from '../../constants/serverURL';
import { from, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { baseURL } from '../../constants/baseURL';
import { map, catchError } from 'rxjs/operators';
import { ProcessHttpMessageService } from '../process-http-message.service';
import { EdoCivil } from '../../classes/edocivil';
import { NumberValueAccessor } from '@angular/forms';
import { Genero } from '../../classes/genero';
import { Place } from '../../classes/place';
import { telefono } from '../../classes/telefono';
import { Disponibilidad } from '../../classes/disponibilidad';

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

    // resultado: Boolean;

    // this.http.post(serverURL+"sesion/validRegister", this.user, {observe: 'response'})
    // .subscribe(resp => {
    //   console.log("VALOR DEL VALID REGISTER:")
    //   console.log(resp.status);
    //   console.log(resp.status.toString);
    // });

    return this.http.post<Person>(serverURL + 'sesion/validRegister', this.user, httpOptions)
      .pipe(catchError(this.processHTTPMessageService.handleError));
  }

}
