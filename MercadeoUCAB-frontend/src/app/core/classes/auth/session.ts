import { Rol } from '../profile/rol';

export class Session{
  _id: number;
  fkRol: Rol;
  tokenLogin: string;
}
