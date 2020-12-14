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
import { NgxSpinnerModule } from 'ngx-spinner';
import { DialogModule } from 'primeng/dialog';
import { ChartModule } from 'primeng/chart';
import { EditorModule } from 'primeng/editor';


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
import { RegisterService } from './services/auth/register.service';
import { PlaceService } from './services/profile/place.service';
import { PhoneService } from './services/profile/phone.service';
import { LoginService } from './services/auth/login.service';
import { RecoveryService } from './services/auth/recovery.service';
import { QuestionService } from './services/admin/studies/question.service';
import { CategoryService } from './services/admin/products/category.service';
import { SubcategoryService } from './services/admin/products/subcategory.service';
import { StudiesService } from './services/admin/studies/studies.service';
import { PresentationService } from './services/admin/products/presentation.service';
import { TypesService } from './services/admin/products/types.service';
import { UserSurveyService } from './services/surveys/user-survey.service';
import { BrandService } from './services/admin/products/brand.service';
import { OptionService } from './services/admin/studies/option.service';
import { ProcessHttpMessageService } from './services/process-http-message.service';

/* My components */
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { AccountComponent } from './auth/register/account/account.component';
import { ContactComponent } from './auth/register/contact/contact.component';
import { StatusComponent } from './auth/register/status/status.component';
import { FamilyComponent } from './auth/register/family/family.component';
import { RecoveryComponent } from './auth/recovery/recovery.component';
import { QuestionsComponent } from './admin/questions/questions.component';
import { NavigationComponent } from './common/navigation/navigation.component';
import { FooterComponent } from './common/footer/footer.component';
import { AddQuestionComponent } from './admin/questions/add-question/add-question.component';
import { EditQuestionComponent } from './admin/questions/edit-question/edit-question.component';
import { PreviewQuestionComponent } from './admin/questions/preview-question/preview-question.component';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { ServerDisconnectedComponent } from './errors/server-disconnected/server-disconnected.component';
import { CategoriesComponent } from './admin/categories/categories.component';
import { AddCategoryComponent } from './admin/categories/add-category/add-category.component';
import { EditCategoryComponent } from './admin/categories/edit-category/edit-category.component'
import { ExistingStudiesComponent } from './admin/studies/existing-studies/existing-studies.component';
import { ViewStudyComponent } from './admin/studies/view-study/view-study.component';
import { EditStudyComponent } from './admin/studies/edit-study/edit-study.component';
import { CreateStudyComponent } from './admin/studies/create-study/create-study.component';
import { StudyRequestsComponent } from './admin/studies/study-requests/study-requests.component';
import { AddQuestionFromPoolComponent } from './admin/studies/add-question-from-pool/add-question-from-pool.component';
import { ProfileComponent } from './common/profile/profile.component';
import { AddQuestionFormComponent } from './admin/questions/add-question/add-question-form/add-question-form.component';
import { ChangeComponent } from './auth/recovery/change/change.component';
import { SelectExistingComponent } from './admin/studies/select-existing/select-existing.component';
import { PresentationsComponent } from './admin/presentations/presentations.component';
import { AddPresentationComponent } from './admin/presentations/add-presentation/add-presentation.component';
import { EditPresentationComponent } from './admin/presentations/edit-presentation/edit-presentation.component';
import { UsersComponent } from './admin/users/users.component';
import { AddUserComponent } from './admin/users/add-user/add-user.component';
import { EditUserComponent } from './admin/users/edit-user/edit-user.component';
import { ViewUsersSummaryComponent } from './admin/users/view-users-summary/view-users-summary.component';
import { AddUserFormComponent } from './admin/users/add-user/add-user-form/add-user-form.component';
import { AvailableSurveysComponent } from './surveys/available-surveys/available-surveys.component';
import { TakeSurveyUserComponent } from './surveys/take-survey-user/take-survey-user.component';
import { AnalysisRequestsComponent } from './analytics/analysis-requests/analysis-requests.component';
import { StatisticsComponent } from './analytics/statistics/statistics.component';
import { AvailablePopulationComponent } from './analytics/available-population/available-population.component';
import { MakeInterviewComponent } from './analytics/make-interview/make-interview.component';
import { MyRequestsComponent } from './client/my-requests/my-requests.component';
import { CreateRequestComponent } from './client/create-request/create-request.component';
import { ClientsStudyStatsComponent } from './client/clients-study-stats/clients-study-stats.component';
import { EditRequestComponent } from './client/edit-request/edit-request.component';
import { StudyPreviewComponent } from './client/study-preview/study-preview.component';
import { SubcategoriesComponent } from './admin/subcategories/subcategories.component';
import { AddSubcategoryComponent } from './admin/subcategories/add-subcategory/add-subcategory.component';
import { EditSubcategoryComponent } from './admin/subcategories/edit-subcategory/edit-subcategory.component';
import { BrandsComponent } from './admin/brands/brands.component';
import { AddBrandComponent } from './admin/brands/add-brand/add-brand.component';
import { EditBrandComponent } from './admin/brands/edit-brand/edit-brand.component';
import { ProductTypesComponent } from './admin/product-types/product-types.component';
import { AddProductTypeComponent } from './admin/product-types/add-product-type/add-product-type.component';
import { EditProductTypeComponent } from './admin/product-types/edit-product-type/edit-product-type.component';

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
    ProfileComponent,
    AddQuestionFormComponent,
    ChangeComponent,
    SelectExistingComponent,
    PresentationsComponent,
    AddPresentationComponent,
    EditPresentationComponent,
    UsersComponent,
    AddUserComponent,
    EditUserComponent,
    ViewUsersSummaryComponent,
    AddUserFormComponent,
    AvailableSurveysComponent,
    TakeSurveyUserComponent,
    AnalysisRequestsComponent,
    StatisticsComponent,
    AvailablePopulationComponent,
    MakeInterviewComponent,
    MyRequestsComponent,
    CreateRequestComponent,
    EditCategoryComponent,
    ClientsStudyStatsComponent,
    EditRequestComponent,
    StudyPreviewComponent,
    SubcategoriesComponent,
    AddSubcategoryComponent,
    EditSubcategoryComponent,
    BrandsComponent,
    AddBrandComponent,
    EditBrandComponent,
    ProductTypesComponent,
    AddProductTypeComponent,
    EditProductTypeComponent
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
    DialogModule,
    ChartModule,
    EditorModule
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
    PresentationService,
    TypesService,
    UserSurveyService,
    BrandService,
    OptionService,
    ProcessHttpMessageService,
    {provide: 'BaseURL', useValue: baseURL},
    {provide: 'ServerURL', useValue: serverURL}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
