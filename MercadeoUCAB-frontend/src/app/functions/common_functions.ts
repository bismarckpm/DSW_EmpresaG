import { DatePipe } from '@angular/common'

export function replaceKey(obj){
    return obj.map(({ nombre: value, nombre: label, ...rest }) => ({ value, label, ...rest }));
}

export function replaceKeyWithValue(obj){
    return obj.map(({ _id: value, nombre: label, ...rest }) => ({ value, label, ...rest }));
}

export function replaceDateWithValue(obj){
    return obj.map(({ _id: value, horaInicial: label, ...rest }) => ({ value, label, ...rest }));
}