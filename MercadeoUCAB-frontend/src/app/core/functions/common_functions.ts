export function replaceKeyWithValue(obj){
    return obj.map(({ _id: value, nombre: label, ...rest }) => ({ value, label, ...rest }));
}

export function replaceDateWithValue(obj){
    return obj.map(({ _id: value, hora: label, ...rest }) => ({ value, label, ...rest }));
}
