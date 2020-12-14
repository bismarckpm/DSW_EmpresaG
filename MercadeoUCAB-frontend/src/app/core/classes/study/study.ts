import { Question } from './question';
import { Analytics } from '../analytics/analytics';
import { Person } from '../profile/person';

export class Study {
    _id: number;
    estado: number;
    fechaRealizacion: string;
    fechaCulminacion?: string;


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
    estadisticas?: Analytics;
    conclusion?: string;
    poblacion_disponible?: Person[];
}
