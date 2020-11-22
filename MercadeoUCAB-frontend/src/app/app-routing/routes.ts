import { Routes } from '@angular/router';

import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { AccountComponent } from '../register/account/account.component';
import { ContactComponent } from '../register/contact/contact.component';
import { StatusComponent } from '../register/status/status.component';
import { FamilyComponent } from '../register/family/family.component';
import { RecoveryComponent } from '../recovery/recovery.component';
import { QuestionsComponent } from '../questions/questions.component'
import { AddQuestionComponent } from '../questions/add-question/add-question.component'
import { EditQuestionComponent } from '../questions/edit-question/edit-question.component';
import { PreviewQuestionComponent } from '../questions/preview-question/preview-question.component';

/* STUDIES */
import { CreateStudyComponent } from '../studies/create-study/create-study.component';
import { EditStudyComponent } from '../studies/edit-study/edit-study.component';
import { ExistingStudiesComponent } from '../studies/existing-studies/existing-studies.component';
import { StudyRequestsComponent } from '../studies/study-requests/study-requests.component';
import { ViewStudyComponent } from '../studies/view-study/view-study.component';

import { CategoriesComponent } from '../categories/categories.component';
import { AddCategoryComponent } from '../categories/add-category/add-category.component';
import { NotFoundComponent } from '../errors/not-found/not-found.component';
import { ProfileComponent } from '../profile/profile.component';
import { componentFactoryName } from '@angular/compiler';
import { ChangeComponent } from '../recovery/change/change.component';
import { SelectExistingComponent } from '../studies/select-existing/select-existing.component';
import { PresentationsComponent } from '../presentations/presentations.component';
import { UsersComponent } from '../users/users.component';
import { AddUserComponent } from '../users/add-user/add-user.component';
import { EditUserComponent } from '../users/edit-user/edit-user.component';
import { AvailableSurveysComponent } from '../surveys/available-surveys/available-surveys.component';
import { TakeSurveyUserComponent } from '../surveys/take-survey-user/take-survey-user.component';
import { AnalysisRequestsComponent } from '../analytics/analysis-requests/analysis-requests.component';
import { AvailablePopulationComponent } from '../analytics/available-population/available-population.component';
import { StatisticsComponent } from '../analytics/statistics/statistics.component';
import { MakeInterviewComponent } from '../analytics/make-interview/make-interview.component';

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
    {
      path: 'categories',
      component: CategoriesComponent
    },
    {
      path: 'categories/add',
      component: AddCategoryComponent
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

    /* PRODUCTS */
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
      redirectTo: 'available-surveys', 
      pathMatch: 'full' 
    }
];