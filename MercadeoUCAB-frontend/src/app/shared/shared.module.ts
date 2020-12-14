import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RxReactiveFormsModule } from '@rxweb/reactive-form-validators';
import { PrimengModule } from './primeng.module';
import { NavigationComponent } from './navigation/navigation.component';
import { FooterComponent } from './footer/footer.component';
import { NotFoundComponent } from '../core/errors/not-found/not-found.component';
import { ServerDisconnectedComponent } from '../core/errors/server-disconnected/server-disconnected.component';

import 'hammerjs';

@NgModule({
  declarations: [
    NavigationComponent,
    FooterComponent,
    NotFoundComponent,
    ServerDisconnectedComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RxReactiveFormsModule,
    PrimengModule
  ],
  exports: [
    NavigationComponent,
    FooterComponent,
    NotFoundComponent,
    ServerDisconnectedComponent,
    FormsModule,
    ReactiveFormsModule,
    RxReactiveFormsModule,
    PrimengModule
  ]
})
export class SharedModule { }
