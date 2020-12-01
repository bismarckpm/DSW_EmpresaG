import { Analytics } from '../classes/analytics'

export class Option {
    id?:number;
    valor?:string;
    rango_inicial?:number;
    rango_final?:number;
    estadisticas?: Analytics;
}