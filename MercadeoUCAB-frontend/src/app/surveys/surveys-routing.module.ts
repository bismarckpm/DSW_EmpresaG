import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TakeSurveyUserComponent } from './take-survey-user/take-survey-user.component';
import { AvailableSurveysComponent } from './available-surveys/available-surveys.component';
import { AuthorizedGuard } from '../core/guards/authorized.guard';
import { SurveyGuard } from '../core/guards/survey.guard';

const routes: Routes = [
  {
    path: 'available',
    component: AvailableSurveysComponent,
    canActivate: [AuthorizedGuard, SurveyGuard]
  },
  {
    path: 'available/take',
    component: TakeSurveyUserComponent,
    canActivate: [AuthorizedGuard, SurveyGuard]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SurveysRoutingModule { }
