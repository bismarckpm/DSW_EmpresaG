import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudyPreviewComponent } from './study-preview/study-preview.component';
import { ClientsStudyStatsComponent } from './clients-study-stats/clients-study-stats.component';
import { EditRequestComponent } from './edit-request/edit-request.component';
import { CreateRequestComponent } from './create-request/create-request.component';
import { MyRequestsComponent } from './my-requests/my-requests.component';

const routes: Routes = [
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
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
