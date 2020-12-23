import { Brand } from './brand';
import { Product_type } from './product_type';

export class BrandType {
    _id: number;
    fkTipo: Product_type;
    fkMarca: Brand;
}
