import { Analytics } from '../analytics/analytics';

export class Option {
    _id?: number;
    valor?: string;
    rangoInicial?: number;
    rangoFinal?: number;
    numeroDePersonas?: number;
    estadisticas?: Analytics;
}
