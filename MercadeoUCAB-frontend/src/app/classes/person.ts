import { Child } from './child';
// import { Device } from '../constants/device';
import { persondata } from './persondata';

export class Person {
    email: string;
    password: string;
    confirmar_clave?: string;
    fkPersona: persondata;
    // primer_nombre?: string;
    // primer_apellido?: string;
    // documento_de_identificacion?: string;
    // genero?: string;
    // estado_civil?: string;
    // fecha_de_nacimiento?: string;
    // id_pais?: number;
    // id_estado?: number;
    // id_ciudad?: number;
    // id_parroquia?: number;
    // codigo_pais?: number;
    // telefono?: number;
    // personas_hogar?: number;
    // tiene_hijos?: boolean;
    // hijos?: Child[];
    // ocupacion?: string;
    // id_nivel_academico?: number;
    // id_nivel_socioeconomico?: number;
    // dispositivos?: Device[];
    // id_horario_inicial?: number;
    // id_horario_final?: number;
}

export var test: Person;