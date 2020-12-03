import { Brand } from './brand';
import { ProductType } from './productType';

export class BrandType {
    _id: number;
    fkTipo: ProductType;
    fkMarca: Brand;
}