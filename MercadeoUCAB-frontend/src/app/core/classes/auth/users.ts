import { Person } from '../profile/person';
import { Rol } from '../profile/rol';

export class Users {
    _id?: Number;
    estado?: Number;
    email: String;
    password: String;
    persona?: Person;
    fkRol?: Rol;
}
