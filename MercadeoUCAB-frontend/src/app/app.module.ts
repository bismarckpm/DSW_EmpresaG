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
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { RadioButtonModule } from 'primeng/radiobutton';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { NgxSpinnerModule } from "ngx-spinner";
import { DialogModule } from 'primeng/dialog';

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
import { QuestionService } from './services/question.service';
import { CategoryService } from './services/category.service';
import { SubcategoryService } from './services/subcategory.service';
import { StudiesService } from './services/studies.service';
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
import { AddQuestionComponent } from './questions/add-question/add-question.component';
import { EditQuestionComponent } from './questions/edit-question/edit-question.component';
import { PreviewQuestionComponent } from './questions/preview-question/preview-question.component';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { ServerDisconnectedComponent } from './errors/server-disconnected/server-disconnected.component';
import { CategoriesComponent } from './categories/categories.component';
import { AddCategoryComponent } from './categories/add-category/add-category.component';
import { ExistingStudiesComponent } from './studies/existing-studies/existing-studies.component';
import { ViewStudyComponent } from './studies/view-study/view-study.component';
import { EditStudyComponent } from './studies/edit-study/edit-study.component';
import { CreateStudyComponent } from './studies/create-study/create-study.component';
import { StudyRequestsComponent } from './studies/study-requests/study-requests.component';
import { AddQuestionFromPoolComponent } from './studies/add-question-from-pool/add-question-from-pool.component';
import { CreateNewQuestionComponent } from './studies/create-new-question/create-new-question.component';
import { ProfileComponent } from './profile/profile.component';

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
    AddQuestionComponent,
    EditQuestionComponent,
    PreviewQuestionComponent,
    NotFoundComponent,
    ServerDisconnectedComponent,
    CategoriesComponent,
    AddCategoryComponent,
    ExistingStudiesComponent,
    ViewStudyComponent,
    EditStudyComponent,
    CreateStudyComponent,
    StudyRequestsComponent,
    AddQuestionFromPoolComponent,
    CreateNewQuestionComponent,
    ProfileComponent,
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
    MenuModule,
    ConfirmDialogModule,
    RadioButtonModule,
    InputTextareaModule,
    ProgressSpinnerModule,
    CheckboxModule,
    NgxSpinnerModule,
    DialogModule
  ],
  providers: [
    RegisterService,
    LoginService,
    RecoveryService,
    PlaceService,
    PhoneService,
    QuestionService,
    CategoryService,
    SubcategoryService,
    StudiesService,
    ProcessHttpMessageService,
    {provide: 'BaseURL', useValue: baseURL},
    {provide: 'ServerURL', useValue: serverURL}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
