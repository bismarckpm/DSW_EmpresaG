import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { MenuItem } from 'primeng/api';
import {ButtonModule} from 'primeng/button';
import { Router } from '@angular/router'


/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../services/register.service';
import { RxwebValidators } from '@rxweb/reactive-form-validators'

@Component({
  selector: 'app-recovery',
  templateUrl: './recovery.component.html',
  styleUrls: ['./recovery.component.scss'],
  providers: [MessageService]

})
export class RecoveryComponent implements OnInit {

   /* Form */
   recoveryForm: FormGroup;
   @ViewChild('rform') recoveryFormDirective;
 
   formErrors = {
     'correo_electronico': ''
   };
 
   validationMessages = {
     'correo_electronico': {
       'required': 'Correo electrónico es requerido',
       'pattern': 'Correo electronico debe tener un formato válido'
     }
    };

    es: any;

    constructor(private router: Router,
      private fb: FormBuilder,
      private registerService: RegisterService,
      private messageService: MessageService) { 
      this.createForm();
    }
  

  ngOnInit(): void {
  }

  createForm(){
    this.recoveryForm = this.fb.group({
      correo_electronico: [
        this.registerService.user.correo_electronico,
        [
          Validators.required,
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ]
    });
  
    this.recoveryForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.recoveryForm){
      return;
    }

    const form = this.recoveryForm;
    for (const field in this.formErrors){
      if (this.formErrors.hasOwnProperty(field)){
        // clear previous error message if any
        this.formErrors[field] = '';
        const control = form.get(field);

        // if field is modified by user
        if (control && control.dirty && !control.valid){
          const messages = this.validationMessages[field];

          // check if i'm adding the error message to the field
          for (const key in control.errors){
            if (control.errors.hasOwnProperty(key)){
              this.formErrors[field] += messages[key] + ' ';
            }
          }
        }
      }
    }
  }

  onSubmit(){
    this.registerService.user.correo_electronico = this.recoveryForm.value.correo_electronico;

    if (this.recoveryForm.valid){
      this.sendEmail();
    }
    else{
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Correo electrónico no válido'});
    }
  }

  sendEmail(): void {
    // Validar correo existente
    // Enviar correo con enlace de cambio de clave.
  }

}
