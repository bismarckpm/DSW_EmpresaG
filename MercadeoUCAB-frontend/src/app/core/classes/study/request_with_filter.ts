import { AcademicLevel } from '../profile/academic_level';
import { Category } from '../products/category';
import { CivilStatus } from '../profile/civil_status';
import { Gender } from '../profile/gender';
import { Place } from '../profile/place';
import { StudyRequest } from './study_request';
import { SocioEconomicStatus } from '../profile/socioeconomic_status';
import { Subcategory } from '../products/subcategory';
import { Study } from './study';

export class RequestWithFilter{
    _id: number;
    nombre?: string;
    edadMinima?: number;
    edadMaxima?: number;
    fkCategoria?: Category;
    fkSolicitud: StudyRequest;
    fkEstudio?: Study;
    fkEdoCivil?: CivilStatus;
    fkGenero?: Gender;
    fkLugar?: Place;
    fkNivelAcademico?: AcademicLevel;
    fkNivelSocioeconomico?: SocioEconomicStatus;
    fkSubcategoria?: Subcategory;
    tipoFiltroLugar?: number;
}
