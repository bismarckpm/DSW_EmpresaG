export function replaceKey(obj){
    return obj.map(({ nombre: value, nombre: label, ...rest }) => ({ value, label, ...rest }));
}

export function replaceKeyWithValue(obj){
    return obj.map(({ id: value, nombre: label, ...rest }) => ({ value, label, ...rest }));
}

export function removeUnderscore(obj){
    return obj.map(({ _id: id, ...rest }) => ({ id, ...rest }));
}