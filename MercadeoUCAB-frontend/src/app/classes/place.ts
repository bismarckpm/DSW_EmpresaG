import { SocioEconomicStatus } from './socioeconomic_status';

export class Place {
    _id?: number;
    nombre?: string;
    tipo?: number;
    fkNivelSocioeconomico?: SocioEconomicStatus;
    fkLugar?: Place;
    label?: string;
    value?: number;
}

export class State {
    id: number;
    nombre: string;
    country_id: number;
}

export class City {
    id: number;
    nombre: string;
    state_id: number;
}