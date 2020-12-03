import { Child } from './child';
import { Device } from '../constants/device';
import { Genero } from './genero';
import { EdoCivil } from './edocivil';
import { Place } from './place';

export class persondata {    
    primerNombre?: string;
    primerApellido?: string;
    documentoIdentidad?: string;
    fkGenero?: Genero;
    fkEdoCivil?: EdoCivil;
    fechaNacimiento?: string;
    // id_pais?: number;
    // id_estado?: number;
    // id_ciudad?: number;
    // id_parroquia?: number;
    fkLugar?: Place;
    codigo_pais?: number;
    telefono?: number;
    personas_hogar?: number;
    tiene_hijos?: boolean;
    hijos?: Child[];
    ocupacion?: string;
    id_nivel_academico?: number;
    id_nivel_socioeconomico?: number;
    dispositivos?: Device[];
    id_horario_inicial?: number;
    id_horario_final?: number;
}