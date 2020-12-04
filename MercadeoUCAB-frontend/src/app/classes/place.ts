export class Place {
    _id?: number;
    nombre?: string;
    label: string;
    value: number;
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