import { Option } from '../classes/options';
import { Answer } from '../classes/answer';
import { Analytics } from '../classes/analytics';
import { QuestionType } from './question_type';

export class Question {
    _id: number;
    fkTipoPregunta: QuestionType;
    pregunta: string;
    status: number;
    listOpciones?: Option[];

    //TODO: Clean up, but not for now to avoid compilation errors

    id: number;
    tipo?: string;
    id_tipo?: number;
    id_categoria?: number;
    id_subcategoria?: number;
    categoria?: string;
    subcategoria?: string;
    opciones?: Option[];
    respuestas?: Answer;
    estadisticas?: Analytics;
}