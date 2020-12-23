import { persondata } from '../profile/persondata';
import { PossibleAnswer } from './possible_answers';
import { Question } from './question';

export class Survey{
    respuestaTexto?:string;
    respuestaRangoInicial?: number;
    respuestaRangoFinal?: number;
    fkPosibleRespuesta?: PossibleAnswer;
    fkPregunta: Question;
    id_estudio: number;
    id_persona: number;
}
