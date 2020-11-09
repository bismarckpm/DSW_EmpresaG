import { Routes } from '@angular/router';

import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { AccountComponent } from '../register/account/account.component';
import { ContactComponent } from '../register/contact/contact.component';

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
          component: AccountComponent
        },
        {
          path: 'contact',
          component: ContactComponent
        }
      ]
    },
    /* REDIRECCIONAR A LA RUTA EN LA QUE SE ESTE TRABAJANDO MIENTRAS TANTO */
    { 
      path: '', 
      redirectTo: '/register', 
      pathMatch: 'full' 
    }
];