import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudyPreviewComponent } from './study-preview/study-preview.component';
import { ClientsStudyStatsComponent } from './clients-study-stats/clients-study-stats.component';
import { EditRequestComponent } from './edit-request/edit-request.component';
import { CreateRequestComponent } from './create-request/create-request.component';
import { MyRequestsComponent } from './my-requests/my-requests.component';
import { AuthorizedGuard } from '../core/guards/authorized.guard';
import { ClientGuard } from '../core/guards/client.guard';

const routes: Routes = [
  {
    path: 'my-requests',
    component: MyRequestsComponent,
    canActivate: [AuthorizedGuard, ClientGuard]
  },
  {
    path: 'make-request',
    component: CreateRequestComponent,
    canActivate: [AuthorizedGuard, ClientGuard]
  },
  {
    path: 'edit-request',
    component: EditRequestComponent,
    canActivate: [AuthorizedGuard, ClientGuard]
  },
  {
    path: 'view-study/stats',
    component: ClientsStudyStatsComponent,
    canActivate: [AuthorizedGuard, ClientGuard]
  },
  {
    path: 'view-study',
    component: StudyPreviewComponent,
    canActivate: [AuthorizedGuard, ClientGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
