import { Option } from './options';
import { Answer } from './answer';
import { Analytics } from '../analytics/analytics';
import { QuestionType } from './question_type';
import { PossibleAnswer } from './possible_answers';

export class Question {
    _id: number;
    fkTipoPregunta: QuestionType;
    pregunta: string;
    status: number;
    listOpciones?: Option[];
    listPosibleRespuestas?: PossibleAnswer[];
}
