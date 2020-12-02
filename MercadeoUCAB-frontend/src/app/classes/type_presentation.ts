import { Presentation } from './presentation';
import { ProductType } from './productType';

export class TypePresentation{
    _id: number;
    fkTipo: ProductType;
    fkPresentacion: Presentation;
}