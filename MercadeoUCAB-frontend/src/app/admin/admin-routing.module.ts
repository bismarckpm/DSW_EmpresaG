import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { QuestionsComponent } from './questions/questions.component';
import { AddQuestionComponent } from './questions/add-question/add-question.component';
import { EditQuestionComponent } from './questions/edit-question/edit-question.component';
import { PreviewQuestionComponent } from './questions/preview-question/preview-question.component';
import { ExistingStudiesComponent } from './studies/existing-studies/existing-studies.component';
import { CreateStudyComponent } from './studies/create-study/create-study.component';
import { SelectExistingComponent } from './studies/select-existing/select-existing.component';
import { EditStudyComponent } from './studies/edit-study/edit-study.component';
import { ViewStudyComponent } from './studies/view-study/view-study.component';
import { StudyRequestsComponent } from './studies/study-requests/study-requests.component';
import { ProductTypesComponent } from './product-types/product-types.component';
import { PresentationsComponent } from './presentations/presentations.component';
import { BrandsComponent } from './brands/brands.component';
import { SubcategoriesComponent } from './subcategories/subcategories.component';
import { CategoriesComponent } from './categories/categories.component';
import { EditUserComponent } from './users/edit-user/edit-user.component';
import { AddUserComponent } from './users/add-user/add-user.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
    /* QUESTIONS */
    {
      path: 'questions',
      component: QuestionsComponent,
    },
    {
      path: 'questions/add',
      component: AddQuestionComponent
    },
    {
      path: 'questions/edit',
      component: EditQuestionComponent
    },
    {
      path: 'questions/view',
      component: PreviewQuestionComponent
    },

    /* STUDIES */
    {
      path: 'studies/existing',
      component: ExistingStudiesComponent
    },
    {
      path: 'studies/create',
      component: CreateStudyComponent
    },
    {
      path: 'studies/select-existing',
      component: SelectExistingComponent
    },
    {
      path: 'studies/edit',
      component: EditStudyComponent
    },
    {
      path: 'studies/view',
      component: ViewStudyComponent
    },
    {
      path: 'studies/requests',
      component: StudyRequestsComponent
    },

    /* PRODUCTS */
      {
        path: 'categories',
        component: CategoriesComponent
      },
      {
        path: 'subcategories',
        component: SubcategoriesComponent
      },
      {
        path: 'brands',
        component: BrandsComponent
      },
      {
        path: 'types',
        component: ProductTypesComponent
      },
      {
        path: 'presentations',
        component: PresentationsComponent
      },

    /* USERS */
    {
      path: 'users',
      component: UsersComponent
    },
    {
      path: 'users/add',
      component: AddUserComponent
    },
    {
      path: 'users/edit',
      component: EditUserComponent
    },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
