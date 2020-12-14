import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { SurveysRoutingModule } from './surveys-routing.module';
import { AvailableSurveysComponent } from './available-surveys/available-surveys.component';
import { TakeSurveyUserComponent } from './take-survey-user/take-survey-user.component';

@NgModule({
  declarations: [
    AvailableSurveysComponent,
    TakeSurveyUserComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    SurveysRoutingModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SurveysModule { }
