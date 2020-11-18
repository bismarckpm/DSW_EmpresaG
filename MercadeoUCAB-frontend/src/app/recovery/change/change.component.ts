import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';
import { RxwebValidators } from '@rxweb/reactive-form-validators'

@Component({
  selector: 'app-change',
  templateUrl: './change.component.html',
  styleUrls: ['./change.component.scss'],
  providers: [MessageService]

})
export class ChangeComponent implements OnInit {

  /* Form */
  changeForm: FormGroup;
  @ViewChild('cform') changeFormDirective;

  formErrors = {
    'clave': '',
    'confirmar_clave': ''
  };

  validationMessages = {
    'clave': {
      'required': 'Clave es requerida',
      'pattern': 'Clave debe contener al menos 8 caracteres, 1 letra, 1 numero y 1 caracter especial'
    },
    'confirmar_clave': {
      'required': 'Confirmar clave es requerida',
      'compare': 'Clave y confirmar clave deben coincidir'
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
    this.changeForm = this.fb.group({
      clave: [this.registerService.user.clave,
        [
          Validators.required,
          Validators.pattern(/^(?=.*\d)(?=.*[a-zA-Z])(?=.*[\W_]).{8,40}$/)
        ]
      ],
      confirmar_clave: [
        this.registerService.user.confirmar_clave,
        [
          Validators.required,
          RxwebValidators.compare({fieldName: 'clave'})
        ]
      ]
    });
  
    this.changeForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.changeForm){
      return;
    }

    const form = this.changeForm;
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
    this.registerService.user.clave = this.changeForm.value.clave;
    this.registerService.user.confirmar_clave = this.changeForm.value.confirmar_clave;

    if (this.changeForm.valid){
      this.nextPage();
    }
    else{
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Claves inválidas'});
    }
  }

  nextPage(): void {
    this.router.navigate(['login']);
  }

}
