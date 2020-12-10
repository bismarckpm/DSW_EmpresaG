import { Question } from '../question';
import { OpenText } from './open_text';

export class QuestionWithStats{
    fkPregunta: Question;
    listRespuestasTexto?: OpenText[];
    promedioRangoInicial?: number;
    promedioRangoFinal?: number;
}