import { Presentation } from './presentation';
import { Product_type } from './product_type';

export class TypePresentation{
    _id: number;
    fkTipo: Product_type;
    fkPresentacion: Presentation;
}
