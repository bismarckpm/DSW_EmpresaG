import { Category } from './category';
import { Question } from './question';
import { Subcategory } from './subcategory';

export class QuestionCategorySubcategory {
    _id: number;
    fkCategoria: Category;
    fkSubcategoria?: Subcategory;
    fkPregunta: Question;
}