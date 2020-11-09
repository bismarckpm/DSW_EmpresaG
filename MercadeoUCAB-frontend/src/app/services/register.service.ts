import { Injectable } from '@angular/core';
import { Person } from '../classes/person';
import { Child } from '../classes/child';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  hijos: Child[] = null;

  user: Person = {
    correo_electronico: '',
    contraseña: '',
    confirmar_contraseña: '',
    primer_nombre: '',
    primer_apellido: '',
    documento_de_identificacion: '',
    fecha_de_nacimiento: null,
    hijos: this.hijos
  };

  constructor() {
  }
}
