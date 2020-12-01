import { Brand } from './brand';
import { Subcategory } from './subcategory';

export class SubcategoryBrand{
    _id: number;
    fkSubcategoria: Subcategory;
    fkMarca: Brand;
}