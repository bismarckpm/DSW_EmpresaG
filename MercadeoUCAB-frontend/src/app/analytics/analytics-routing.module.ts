import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MakeInterviewComponent } from './make-interview/make-interview.component';
import { StatisticsComponent } from './statistics/statistics.component';
import { AvailablePopulationComponent } from './available-population/available-population.component';
import { AnalysisRequestsComponent } from './analysis-requests/analysis-requests.component';
import { AuthorizedGuard } from '../core/guards/authorized.guard';
import { AnalystGuard } from '../core/guards/analyst.guard';

const routes: Routes = [
    {
      path: 'requests',
      component: AnalysisRequestsComponent,
      canActivate: [AuthorizedGuard, AnalystGuard]
    },
    {
      path: 'available-population',
      component: AvailablePopulationComponent,
      canActivate: [AuthorizedGuard, AnalystGuard]
    },
    {
      path: 'stats',
      component: StatisticsComponent,
      canActivate: [AuthorizedGuard, AnalystGuard]
    },
    {
      path: 'make-interview',
      component: MakeInterviewComponent,
      canActivate: [AuthorizedGuard, AnalystGuard]
    }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AnalyticsRoutingModule { }
