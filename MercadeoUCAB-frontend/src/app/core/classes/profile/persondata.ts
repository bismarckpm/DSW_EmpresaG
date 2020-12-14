import { Child } from './child';
import { Device } from '../../constants/device';
import { Genero } from './genero';
import { EdoCivil } from './edocivil';
import { Place } from './place';
import { telefono } from './telefono';
import { Disponibilidad } from './disponibilidad';

export class persondata {
    _id ?: number;
    primerNombre?: string;
    primerApellido?: string;
    documentoIdentidad?: string;
    fkGenero?: Genero;
    fkEdoCivil?: EdoCivil;
    fechaNacimiento?: string;
    id_pais?: Place;
    id_estado?: Place;
    id_ciudad?: Place;
    id_parroquia?: Place;
    fkLugar?: Place;
    codigo_pais?: number;
    telefono?: telefono;
    numero_personas_encasa?: number;
    tiene_hijos?: boolean;
    hijos?: Child[];
    ocupacion?: string;
    id_nivel_academico?: number;
    id_nivel_socioeconomico?: number;
    dispositivos?: Device[];
    id_horario_inicial?: Disponibilidad;
    id_horario_final?: Disponibilidad;
}
