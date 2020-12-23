import { Category } from '../products/category';
import { Question } from './question';
import { Subcategory } from '../products/subcategory';

export class QuestionCategorySubcategory {
    _id: number;
    fkCategoria: Category;
    fkSubcategoria?: Subcategory;
    fkPregunta: Question;
}
