export class Study {
    id: number;
    categoria?: string;
    id_categoria?: number;
    subcategoria?: string;
    id_subcategoria?: string;
    hijos?: boolean;
    genero?: string;
    id_genero?: number;
    nivel_academico?: string[];
    id_nivel_academico?: number[];
    nivel_socioeconomico?:string[];
    id_nivel_socioeconomico?: number[];
    lugares?: string[];
    id_lugares?: number[];
}