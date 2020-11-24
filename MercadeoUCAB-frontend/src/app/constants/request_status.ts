export class RequestState {
    label: string;
    value: number;
}

export const REQUEST_STATES = [
    {
        label: 'Sin procesar',
        value: 1
    },
    {
        label: 'En progreso',
        value: 2
    },
    {
        label: 'Terminado',
        value: 3
    }
];