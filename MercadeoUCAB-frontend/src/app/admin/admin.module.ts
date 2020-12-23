import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { AdminRoutingModule } from './admin-routing.module';
import { QuestionsComponent } from './questions/questions.component';
import { AddQuestionComponent } from './questions/add-question/add-question.component';
import { AddQuestionFormComponent } from './questions/add-question/add-question-form/add-question-form.component';
import { EditQuestionComponent } from './questions/edit-question/edit-question.component';
import { PreviewQuestionComponent } from './questions/preview-question/preview-question.component';
import { CategoriesComponent } from './categories/categories.component';
import { AddCategoryComponent } from './categories/add-category/add-category.component';
import { ExistingStudiesComponent } from './studies/existing-studies/existing-studies.component';
import { ViewStudyComponent } from './studies/view-study/view-study.component';
import { EditStudyComponent } from './studies/edit-study/edit-study.component';
import { CreateStudyComponent } from './studies/create-study/create-study.component';
import { StudyRequestsComponent } from './studies/study-requests/study-requests.component';
import { AddQuestionFromPoolComponent } from './studies/add-question-from-pool/add-question-from-pool.component';
import { SelectExistingComponent } from './studies/select-existing/select-existing.component';
import { PresentationsComponent } from './presentations/presentations.component';
import { AddPresentationComponent } from './presentations/add-presentation/add-presentation.component';
import { EditPresentationComponent } from './presentations/edit-presentation/edit-presentation.component';
import { UsersComponent } from './users/users.component';
import { AddUserComponent } from './users/add-user/add-user.component';
import { EditUserComponent } from './users/edit-user/edit-user.component';
import { ViewUsersSummaryComponent } from './users/view-users-summary/view-users-summary.component';
import { AddUserFormComponent } from './users/add-user/add-user-form/add-user-form.component';
import { EditCategoryComponent } from './categories/edit-category/edit-category.component';
import { SubcategoriesComponent } from './subcategories/subcategories.component';
import { AddSubcategoryComponent } from './subcategories/add-subcategory/add-subcategory.component';
import { EditSubcategoryComponent } from './subcategories/edit-subcategory/edit-subcategory.component';
import { BrandsComponent } from './brands/brands.component';
import { AddBrandComponent } from './brands/add-brand/add-brand.component';
import { EditBrandComponent } from './brands/edit-brand/edit-brand.component';
import { ProductTypesComponent } from './product-types/product-types.component';
import { AddProductTypeComponent } from './product-types/add-product-type/add-product-type.component';
import { EditProductTypeComponent } from './product-types/edit-product-type/edit-product-type.component';

@NgModule({
  declarations: [
    QuestionsComponent,
    AddQuestionComponent,
    AddQuestionFormComponent,
    EditQuestionComponent,
    PreviewQuestionComponent,
    CategoriesComponent,
    AddCategoryComponent,
    EditCategoryComponent,
    ExistingStudiesComponent,
    ViewStudyComponent,
    EditStudyComponent,
    CreateStudyComponent,
    StudyRequestsComponent,
    AddQuestionFromPoolComponent,
    SelectExistingComponent,
    PresentationsComponent,
    AddPresentationComponent,
    EditPresentationComponent,
    UsersComponent,
    AddUserComponent,
    EditUserComponent,
    ViewUsersSummaryComponent,
    AddUserFormComponent,
    SubcategoriesComponent,
    AddSubcategoryComponent,
    EditSubcategoryComponent,
    BrandsComponent,
    AddBrandComponent,
    EditBrandComponent,
    ProductTypesComponent,
    AddProductTypeComponent,
    EditProductTypeComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminModule { }
