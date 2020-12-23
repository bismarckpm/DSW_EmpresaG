import { AcademicLevel } from '../profile/academic_level';
import { Category } from '../products/category';
import { CivilStatus } from '../profile/civil_status';
import { Gender } from '../profile/gender';
import { Place } from '../profile/place';
import { SocioEconomicStatus } from '../profile/socioeconomic_status';
import { Study } from './study';
import { Subcategory } from '../products/subcategory';

export class StudyWithFilter {
    _id: number;
    nombre?: string;
    edadMinima?: number;
    edadMaxima?: number;
    fkCategoria?: Category;
    fkEstudio: Study;
    fkEdoCivil?: CivilStatus;
    fkGenero?: Gender;
    fkLugar?: Place;
    fkNivelAcademico?: AcademicLevel;
    fkNivelSocioeconomico?: SocioEconomicStatus;
    fkSubcategoria?: Subcategory;
    tipoFiltroLugar?: number;
}
