import { Option } from './options';
import { Question } from './question';

export class PossibleAnswer{
    _id: number;
    fkOpcion: Option;
    fkPregunta?: Question;
}