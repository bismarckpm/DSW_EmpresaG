import { Analytics } from '../classes/analytics'

export class Option {
    _id?: number;
    valor?: string;
    rangoInicial?: number;
    rangoFinal?: number;
    numeroDePersonas?: number;

    // TODO: Clean up, but not now to avoid compilation errors
    estadisticas?: Analytics;
}