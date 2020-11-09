import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

/* PrimeNG Modules */
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { StepsModule } from 'primeng/steps';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { TooltipModule } from 'primeng/tooltip';
import { TableModule } from 'primeng/table';
import { MultiSelectModule } from 'primeng/multiselect';

/* Modules */
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

/* Constants */
import { baseURL } from './constants/baseURL';

/* Scripts */
import 'hammerjs';

/* Services */
import { RegisterService } from './services/register.service';

/* My components */
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AccountComponent } from './register/account/account.component';
import { ContactComponent } from './register/contact/contact.component';
import { StatusComponent } from './register/status/status.component';
import { FamilyComponent } from './register/family/family.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent,
    ContactComponent,
    StatusComponent,
    FamilyComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    CardModule,
    ButtonModule,
    StepsModule,
    InputTextModule,
    PasswordModule,
    DropdownModule,
    CalendarModule,
    TooltipModule,
    TableModule,
    MultiSelectModule
  ],
  providers: [
    RegisterService,
    {provide: 'BaseURL', useValue: baseURL}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
