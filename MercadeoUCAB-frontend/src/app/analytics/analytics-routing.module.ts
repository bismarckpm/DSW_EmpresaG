import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MakeInterviewComponent } from './make-interview/make-interview.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { AvailablePopulationComponent } from './available-population/available-population.component';
import { AnalysisRequestsComponent } from './analysis-requests/analysis-requests.component';

const routes: Routes = [
    {
      path: 'requests',
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
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AnalyticsRoutingModule { }
