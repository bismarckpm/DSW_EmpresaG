import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
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

const PRIMENG_MODULES = [
  CommonModule,
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
];

@NgModule({
  declarations: [],
  imports: [
    ...PRIMENG_MODULES
  ],
  exports: [
    ...PRIMENG_MODULES
  ]
})
export class PrimengModule { }
