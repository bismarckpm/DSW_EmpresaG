import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TakeSurveyUserComponent } from './take-survey-user/take-survey-user.component';
import { AvailableSurveysComponent } from './available-surveys/available-surveys.component';

const routes: Routes = [
  {
    path: 'available',
    component: AvailableSurveysComponent
  },
  {
    path: 'available/take',
    component: TakeSurveyUserComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SurveysRoutingModule { }
