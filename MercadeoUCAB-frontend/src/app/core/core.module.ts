import {NgModule, Optional, SkipSelf} from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RegisterService } from './services/auth/register.service';
import { LoginService } from './services/auth/login.service';
import { RecoveryService } from './services/auth/recovery.service';
import { PlaceService } from './services/profile/place.service';
import { PhoneService } from './services/profile/phone.service';
import { QuestionService } from './services/admin/studies/question.service';
import { CategoryService } from './services/admin/products/category.service';
import { SubcategoryService } from './services/admin/products/subcategory.service';
import { StudiesService } from './services/admin/studies/studies.service';
import { PresentationService } from './services/admin/products/presentation.service';
import { TypesService } from './services/admin/products/types.service';
import { UserSurveyService } from './services/surveys/user-survey.service';
import { BrandService } from './services/admin/products/brand.service';
import { OptionService } from './services/admin/studies/option.service';
import { SessionService } from './services/auth/session.service';
import { ProcessHttpMessageService } from './services/process-http-message.service';
import { serverURL } from './constants/serverURL';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
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
    SessionService,
    ProcessHttpMessageService,
    {provide: 'ServerURL', useValue: serverURL}
  ]
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule){
      throw new Error(
        'CoreModule is already loaded. Import it in AppModule only'
      );
    }
  }
}
