export class Country {
    label: string;
    value: number;
}

export class State {
    label: string;
    value: number;
    country_id: number;
}

export class City {
    label: string;
    value: number;
    state_id: number;
}

export class County {
    label: string;
    value: number;
    city_id: string;
}