import { Analytics } from './analytics';

export class Request {
    id: number;
    estado?: string;
    id_estado: number;
    
    /* TO MODIFY / ADD VALUES */
    id_autor?: number;
    id_categoria?: number;
    id_subcategoria?: string;
    id_genero?: number;
    id_nivel_academico?: number[];
    id_nivel_socioeconomico?: number[];
    id_lugares?: number[];
    tipo_filtro_geografico?: string;
    edad_minima?:number;
    edad_maxima?:number;

    /* TO PRINT IN TABLE */
    genero?: string;
    nivel_academico?: string[];
    nivel_socioeconomico?:string[];
    lugares?: string[];
    subcategoria?: string;
    categoria?: string;
    autor?: string;
}