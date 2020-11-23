import { Question } from '../classes/question';
import { Analytics } from '../classes/analytics';

export class Study {
    id: number;
    id_estado?: number;
    estado?: string;

    /* TO MODIFY / ADD VALUES */
    
    id_categoria?: number;
    id_subcategoria?: string;
    id_genero?: number;
    id_nivel_academico?: number[];
    id_nivel_socioeconomico?: number[];
    id_lugares?: number[];
    preguntas?: Question[];
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

    /* FOR USER VIEW */
    n_preguntas?: number;
    tiempo_estimado?: number;

    /* FOR ANALYTICS */
    estadisticas: Analytics;
}