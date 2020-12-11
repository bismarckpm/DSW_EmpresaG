import { AcademicLevel } from '../classes/academic_level';
import { Category } from './category';
import { CivilStatus } from './civil_status';
import { Gender } from './gender';
import { Place } from './place';
import { StudyRequest } from './study_request';
import { SocioEconomicStatus } from './socioeconomic_status';
import { Subcategory } from './subcategory';
import { Study } from './study';

export class RequestWithFilter{
    _id: number;
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