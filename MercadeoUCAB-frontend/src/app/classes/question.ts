import { Filter } from '../classes/filter';

export class Question {
    id: number;
    tipo: string;
    pregunta: string;
    categoria: string;
    subcategoria: string;
    filtros_adicionales: Filter[];
}