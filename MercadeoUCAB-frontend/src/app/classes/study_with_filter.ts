import { AcademicLevel } from '../classes/academic_level';
import { Category } from './category';
import { CivilStatus } from './civil_status';
import { Gender } from './gender';
import { Place } from './place';
import { SocioEconomicStatus } from './socioeconomic_status';
import { Study } from './study';
import { Subcategory } from './subcategory';

export class StudyWithFilter {
    _id: number;
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