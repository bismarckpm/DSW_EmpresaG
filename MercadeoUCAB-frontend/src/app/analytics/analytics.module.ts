import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { AnalyticsRoutingModule } from './analytics-routing.module';
import { AnalysisRequestsComponent } from './analysis-requests/analysis-requests.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { AvailablePopulationComponent } from './available-population/available-population.component';
import { MakeInterviewComponent } from './make-interview/make-interview.component';

@NgModule({
  declarations: [
    AnalysisRequestsComponent,
    StatisticsComponent,
    AvailablePopulationComponent,
    MakeInterviewComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    AnalyticsRoutingModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AnalyticsModule { }
