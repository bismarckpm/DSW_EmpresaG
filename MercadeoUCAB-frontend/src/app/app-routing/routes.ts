import { Routes } from '@angular/router';
import { NotFoundComponent } from '../core/errors/not-found/not-found.component';

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
      path: 'change/:hash',
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
      redirectTo: 'login',
      pathMatch: 'full'
    }
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full'
  },
  {
    path: 'auth',
    loadChildren: () => import('../auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'profile',
    loadChildren: () => import('../profile/profile.module').then(m => m.ProfileModule)
  },
  {
    path: 'admin',
    loadChildren: () => import('../admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'surveys',
    loadChildren: () => import('../surveys/surveys.module').then(m => m.SurveysModule)
  },
  {
    path: 'analytics',
    loadChildren: () => import('../analytics/analytics.module').then(m => m.AnalyticsModule)
  },
  {
    path: 'client',
    loadChildren: () => import('../client/client.module').then(m => m.ClientModule)
  },
  /* ERROR PAGES */
  {
    path: '404',
    component: NotFoundComponent
  },
];
