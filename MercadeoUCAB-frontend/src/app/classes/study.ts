import { Question } from '../classes/question';

export class Study {
    id: number;
    id_estado?: number;
    estado?: string;
    categoria?: string;
    id_categoria?: number;
    subcategoria?: string;
    id_subcategoria?: string;
    genero?: string;
    id_genero?: number;
    nivel_academico?: string[];
    id_nivel_academico?: number[];
    nivel_socioeconomico?:string[];
    id_nivel_socioeconomico?: number[];
    lugares?: string[];
    id_lugares?: number[];
    preguntas?: Question[];
    tipo_filtro_geografico?: string;
}