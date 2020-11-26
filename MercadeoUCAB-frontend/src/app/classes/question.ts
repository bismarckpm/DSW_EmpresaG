import { Option } from '../classes/options';
import { Answer } from '../classes/answer';
import { Analytics } from '../classes/analytics';

/* id_categoria/subcategoria: Es un campo para los IDs cuando se hagan los CRUD 
    los campo string son de muestra para la impresion de tablas y vistas previas
    Cuando se haga el backend, al hacer el request de get y get(id_pregunta) se usaran los atributos de texto y cuando
    se haga post(pregunta) y put(pregunta) se usaran los atributos id_x 
*/

export class Question {
    id: number;
    tipo?: string;
    pregunta: string;
    id_tipo?: number;
    id_categoria?: number;
    id_subcategoria?: number;
    categoria?: string;
    subcategoria?: string;
    opciones?: Option[];
    respuestas?: Answer;
    estadisticas?: Analytics;
}