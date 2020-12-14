import { Analytics } from '../analytics/analytics';
import { Option } from './options'

export class Answer {
    usuario_id: number;
    respuesta_texto?: string;
    opcion_seleccionada?: Option[];
    rango_inicial?: number;
    rango_final?: number;
    estadisticas?: Analytics;
}
