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
import { ToastModule } from 'primeng/toast';
import { MenubarModule } from 'primeng/menubar';
import { MenuModule } from 'primeng/menu';

/* Modules */
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RxReactiveFormsModule } from '@rxweb/reactive-form-validators';

/* Constants */
import { baseURL } from './constants/baseURL';
import { serverURL } from './constants/serverURL';

/* Scripts */
import 'hammerjs';

/* Services */
import { RegisterService } from './services/register.service';
import { PlaceService } from './services/place.service';
import { PhoneService } from './services/phone.service';
import { LoginService } from './services/login.service';
import { RecoveryService } from './services/recovery.service';
import { ProcessHttpMessageService } from './services/process-http-message.service';

/* My components */
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AccountComponent } from './register/account/account.component';
import { ContactComponent } from './register/contact/contact.component';
import { StatusComponent } from './register/status/status.component';
import { FamilyComponent } from './register/family/family.component';
import { RecoveryComponent } from './recovery/recovery.component';
import { QuestionsComponent } from './questions/questions.component';
import { NavigationComponent } from './common/navigation/navigation.component';
import { FooterComponent } from './common/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent,
    ContactComponent,
    StatusComponent,
    FamilyComponent,
    RecoveryComponent,
    QuestionsComponent,
    NavigationComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RxReactiveFormsModule,
    CardModule,
    ButtonModule,
    StepsModule,
    InputTextModule,
    PasswordModule,
    DropdownModule,
    CalendarModule,
    TooltipModule,
    TableModule,
    MultiSelectModule,
    ToastModule,
    MenubarModule,
    MenuModule
  ],
  providers: [
    RegisterService,
    LoginService,
    RecoveryService,
    PlaceService,
    PhoneService,
    ProcessHttpMessageService,
    {provide: 'BaseURL', useValue: baseURL},
    {provide: 'ServerURL', useValue: serverURL}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
