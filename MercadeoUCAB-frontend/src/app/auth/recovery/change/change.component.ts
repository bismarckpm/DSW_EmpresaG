import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../../core/services/auth/register.service';
import { RxwebValidators } from '@rxweb/reactive-form-validators'
import { Reset } from 'src/app/core/classes/auth/reset';
import { ResetService } from 'src/app/core/services/auth/reset.service';


@Component({
  selector: 'app-change',
  templateUrl: './change.component.html',
  styleUrls: ['./change.component.scss'],
  providers: [MessageService]

})
export class ChangeComponent implements OnInit {

  token: String;
  reset: Reset;
  currentURL: String[];

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

    constructor(private Activatedroute:ActivatedRoute,
      private router: Router,
      private fb: FormBuilder,
      private resetService: ResetService,
      private messageService: MessageService) {
      this.createForm();
      this.currentURL = this.router.url.split('/');
      this.token = this.currentURL[this.currentURL.length - 1];
      this.reset = new Reset();
      this.reset.token = this.token;
    }

  ngOnInit(): void {
    this.postVerificar();
  }

  createForm(){
    this.changeForm = this.fb.group({
      clave: [this.resetService.clave,
        [
          Validators.required,
          Validators.pattern(/^(?=.*\d)(?=.*[a-zA-Z])(?=.*[\W_]).{8,40}$/)
        ]
      ],
      confirmar_clave: [
        this.resetService.confirmar_clave,
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


    if (this.changeForm.valid){

      this.reset.clave = this.changeForm.value.clave;

      this.postReset();
      this.nextPage();
    }
    else{
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Claves inválidas'});
    }
  }


  postReset(): void {

    this.resetService.postReset(this.reset).subscribe((res)=>{
      this.messageService.add({severity:'success', summary: 'Success', detail: "Clave cambiada correctamente."});
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
    });
  }

  postVerificar(): void {
    this.resetService.postVerificar(this.reset).subscribe((res)=>{
      if (res == null)
      this.router.navigate(['404']);
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
    });
  }

  nextPage(): void {
    this.router.navigate(['login']);
  }

}
