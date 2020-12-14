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
import { AuthorizedGuard } from '../core/guards/authorized.guard';
import { AdminGuard } from '../core/guards/admin.guard';

const routes: Routes = [
    /* QUESTIONS */
    {
      path: 'questions',
      component: QuestionsComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'questions/add',
      component: AddQuestionComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'questions/edit',
      component: EditQuestionComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'questions/view',
      component: PreviewQuestionComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },

    /* STUDIES */
    {
      path: 'studies/existing',
      component: ExistingStudiesComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'studies/create',
      component: CreateStudyComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'studies/select-existing',
      component: SelectExistingComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'studies/edit',
      component: EditStudyComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'studies/view',
      component: ViewStudyComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'studies/requests',
      component: StudyRequestsComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },

    /* PRODUCTS */
      {
        path: 'categories',
        component: CategoriesComponent,
        canActivate: [AuthorizedGuard, AdminGuard]
      },
      {
        path: 'subcategories',
        component: SubcategoriesComponent,
        canActivate: [AuthorizedGuard, AdminGuard]
      },
      {
        path: 'brands',
        component: BrandsComponent,
        canActivate: [AuthorizedGuard, AdminGuard]
      },
      {
        path: 'types',
        component: ProductTypesComponent,
        canActivate: [AuthorizedGuard, AdminGuard]
      },
      {
        path: 'presentations',
        component: PresentationsComponent,
        canActivate: [AuthorizedGuard, AdminGuard]
      },

    /* USERS */
    {
      path: 'users',
      component: UsersComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'users/add',
      component: AddUserComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },
    {
      path: 'users/edit',
      component: EditUserComponent,
      canActivate: [AuthorizedGuard, AdminGuard]
    },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
