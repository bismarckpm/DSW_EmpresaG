import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { Router } from '@angular/router';
import { Recovery } from '../../core/classes/auth/recovery';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RecoveryService } from '../../core/services/auth/recovery.service';
import { ReorderableColumn } from 'primeng/table';

@Component({
  selector: 'app-recovery',
  templateUrl: './recovery.component.html',
  styleUrls: ['./recovery.component.scss'],
  providers: [MessageService]

})
export class RecoveryComponent implements OnInit {


   /* Form */
   recoveryForm: FormGroup;
   @ViewChild('aform') recoveryFormDirective;

   recovery: Recovery;

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
      private recoveryService: RecoveryService,
      private messageService: MessageService) {
      this.createForm();
    }


  ngOnInit(): void {
  }

  createForm(){
    this.recoveryForm = this.fb.group({
      correo_electronico: [
        this.recoveryService.correo,
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
    this.recoveryService.correo = this.recoveryForm.value.correo_electronico;

    if (this.recoveryForm.valid){
      this.postRecovery();
    }
    else{
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Correo electrónico no válido'});
    }
  }

  postRecovery(): void {

    this.recovery = new Recovery();
    this.recovery.correo = this.recoveryForm.value.correo_electronico;

    this.recoveryService.postRecovery(this.recovery).subscribe((res)=>{
      this.messageService.add({severity:'success', summary: 'Success', detail: "Correo de recuperación enviado."});
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
    });
  }

}
