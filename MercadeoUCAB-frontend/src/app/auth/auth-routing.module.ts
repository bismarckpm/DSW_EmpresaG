import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AccountComponent } from './register/account/account.component';
import { ContactComponent } from './register/contact/contact.component';
import { FamilyComponent } from './register/family/family.component';
import { StatusComponent } from './register/status/status.component';
import { RecoveryComponent } from './recovery/recovery.component';
import { ChangeComponent } from './recovery/change/change.component';
import {GuestGuard} from '../core/guards/guest.guard';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [GuestGuard],
    children: [
      {
        path: '',
        redirectTo: 'personal',
        pathMatch: 'full'
      },
      {
        path: 'personal',
        component: AccountComponent
      },
      {
        path: 'contact',
        component: ContactComponent
      },
      {
        path: 'family',
        component: FamilyComponent
      },
      {
        path: 'status',
        component: StatusComponent
      }
    ]
  },
  {
    path: 'recovery',
    component: RecoveryComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'change',
    component: ChangeComponent,
    canActivate: [GuestGuard]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
