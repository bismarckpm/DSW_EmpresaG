import { Routes } from '@angular/router';

import { LoginComponent } from '../auth/login/login.component';
import { RegisterComponent } from '../auth/register/register.component';
import { AccountComponent } from '../auth/register/account/account.component';
import { ContactComponent } from '../auth/register/contact/contact.component';
import { StatusComponent } from '../auth/register/status/status.component';
import { FamilyComponent } from '../auth/register/family/family.component';
import { RecoveryComponent } from '../auth/recovery/recovery.component';
import { QuestionsComponent } from '../admin/questions/questions.component'
import { AddQuestionComponent } from '../admin/questions/add-question/add-question.component'
import { EditQuestionComponent } from '../admin/questions/edit-question/edit-question.component';
import { PreviewQuestionComponent } from '../admin/questions/preview-question/preview-question.component';

/* STUDIES */
import { CreateStudyComponent } from '../admin/studies/create-study/create-study.component';
import { EditStudyComponent } from '../admin/studies/edit-study/edit-study.component';
import { ExistingStudiesComponent } from '../admin/studies/existing-studies/existing-studies.component';
import { StudyRequestsComponent } from '../admin/studies/study-requests/study-requests.component';
import { ViewStudyComponent } from '../admin/studies/view-study/view-study.component';

import { CategoriesComponent } from '../admin/categories/categories.component';
import { NotFoundComponent } from '../errors/not-found/not-found.component';
import { ProfileComponent } from '../common/profile/profile.component';
import { ChangeComponent } from '../auth/recovery/change/change.component';
import { SelectExistingComponent } from '../admin/studies/select-existing/select-existing.component';
import { PresentationsComponent } from '../admin/presentations/presentations.component';
import { UsersComponent } from '../admin/users/users.component';
import { AddUserComponent } from '../admin/users/add-user/add-user.component';
import { EditUserComponent } from '../admin/users/edit-user/edit-user.component';
import { AvailableSurveysComponent } from '../surveys/available-surveys/available-surveys.component';
import { TakeSurveyUserComponent } from '../surveys/take-survey-user/take-survey-user.component';
import { AnalysisRequestsComponent } from '../analytics/analysis-requests/analysis-requests.component';
import { AvailablePopulationComponent } from '../analytics/available-population/available-population.component';
import { StatisticsComponent } from '../analytics/statistics/statistics.component';
import { MakeInterviewComponent } from '../analytics/make-interview/make-interview.component';
import { MyRequestsComponent } from '../client/my-requests/my-requests.component';
import { CreateRequestComponent } from '../client/create-request/create-request.component';
import { ClientsStudyStatsComponent } from '../client/clients-study-stats/clients-study-stats.component';
import { EditRequestComponent } from '../client/edit-request/edit-request.component';
import { StudyPreviewComponent } from '../client/study-preview/study-preview.component';
import { SubcategoriesComponent } from '../admin/subcategories/subcategories.component';
import { BrandsComponent } from '../admin/brands/brands.component';
import { ProductTypesComponent } from '../admin/product-types/product-types.component';

export const routes: Routes = [
    {
      path: 'login',
      component: LoginComponent
    },
    {
      path: 'register',
      component: RegisterComponent,
      children: [
        {
          path: '',
          redirectTo: 'personal',
          pathMatch: 'full'
        },
        {
          path: 'personal',
          component: AccountComponent
        },
        {
          path: 'contact',
          component: ContactComponent
        },
        {
          path: 'family',
          component: FamilyComponent
        },
        {
          path: 'status',
          component: StatusComponent
        }
      ]
    },
    {
      path: 'recovery',
      component: RecoveryComponent
    },
    {
      path: 'change',
      component: ChangeComponent
    },
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
    {
      path: 'profile',
      component: ProfileComponent
    },

    /* SURVEYS */
    {
      path: 'available-surveys',
      component: AvailableSurveysComponent
    },
    {
      path: 'available-surveys/take',
      component: TakeSurveyUserComponent
    },

    /* ANALYST */
    {
      path: 'analysis-requests',
      component: AnalysisRequestsComponent
    },
    {
      path: 'available-population',
      component: AvailablePopulationComponent
    },
    {
      path: 'stats',
      component: StatisticsComponent
    },
    {
      path: 'make-interview',
      component: MakeInterviewComponent
    },

    /* CLIENT */
    {
      path: 'my-requests',
      component: MyRequestsComponent
    },
    {
      path: 'make-request',
      component: CreateRequestComponent
    },
    {
      path: 'edit-request',
      component: EditRequestComponent
    },
    {
      path: 'view-study/stats',
      component: ClientsStudyStatsComponent
    },
    {
      path: 'view-study',
      component: StudyPreviewComponent
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

    /* ERROR PAGES */
    {
      path: '404',
      component: NotFoundComponent
    },

    /* REDIRECCIONAR A LA RUTA EN LA QUE SE ESTE TRABAJANDO MIENTRAS TANTO */
    {
      path: '',
      redirectTo: 'types',
      pathMatch: 'full'
    }
];
