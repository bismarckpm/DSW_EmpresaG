import { Routes } from '@angular/router';

import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { AccountComponent } from '../register/account/account.component';
import { ContactComponent } from '../register/contact/contact.component';
import { StatusComponent } from '../register/status/status.component';
import { FamilyComponent } from '../register/family/family.component';
import { RecoveryComponent } from '../recovery/recovery.component';

export const routes: Routes = [
    { 
      path: 'login',  
      component: LoginComponent 
    },
    {
      path: 'register',
      component: RegisterComponent,
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
      component: RecoveryComponent
    },
    
    /* REDIRECCIONAR A LA RUTA EN LA QUE SE ESTE TRABAJANDO MIENTRAS TANTO */
    { 
      path: '', 
      redirectTo: '/register/personal', 
      pathMatch: 'full' 
    }
];