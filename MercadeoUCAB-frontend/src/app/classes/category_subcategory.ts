import { Category } from './category';
import { Subcategory } from './subcategory';

export class CategorySubcategory{
    _id: number;
    fkCategoria: Category;
    fkSubcategoria: Subcategory;
}