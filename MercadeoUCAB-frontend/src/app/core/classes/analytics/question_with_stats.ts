import { Question } from '../study/question';
import { OpenText } from './open_text';

export class QuestionWithStats{
    fkPregunta: Question;
    listRespuestasTexto?: OpenText[];
    promedioRangoInicial?: number;
    promedioRangoFinal?: number;
}
