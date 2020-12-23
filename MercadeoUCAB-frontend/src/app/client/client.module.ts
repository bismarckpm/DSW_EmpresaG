import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { ClientRoutingModule } from './client-routing.module';
import { MyRequestsComponent } from './my-requests/my-requests.component';
import { CreateRequestComponent } from './create-request/create-request.component';
import { ClientsStudyStatsComponent } from './clients-study-stats/clients-study-stats.component';
import { EditRequestComponent } from './edit-request/edit-request.component';
import { StudyPreviewComponent } from './study-preview/study-preview.component';

@NgModule({
  declarations: [
    MyRequestsComponent,
    CreateRequestComponent,
    ClientsStudyStatsComponent,
    EditRequestComponent,
    StudyPreviewComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ClientRoutingModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClientModule { }
