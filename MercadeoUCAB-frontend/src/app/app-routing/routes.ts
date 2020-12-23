import { Routes } from '@angular/router';
import { NotFoundComponent } from '../core/errors/not-found/not-found.component';

export const routes: Routes = [
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
