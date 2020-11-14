import { Option } from '../classes/options';

/* id_categoria/subcategoria: Es un campo para los IDs cuando se hagan los CRUD 
    los campo string son de muestra para la impresion de tablas y vistas previas
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
}