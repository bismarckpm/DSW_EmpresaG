import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router'

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  /* Form */
  loginForm: FormGroup;
  @ViewChild('lform') loginFormDirective;

  formErrors = {
    'correo_electronico': '',
    'clave': '',
  };

  validationMessages = {
    'correo_electronico': {
      'required': 'Correo electrónico es requerido',
      'pattern': 'Correo electronico debe tener un formato válido'
    },
    'clave': {
      'required': 'Clave es requerida'
    }
  };

  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) {
     this.createForm();
    }

  ngOnInit(): void {
  }

  createForm(){
    this.loginForm = this.fb.group({
      correo_electronico: [
        this.loginService.user.correo_electronico,
        [
          Validators.required,
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ],
      clave: [this.loginService.user.clave,
        [
          Validators.required
        ]
      ]
    });

    
    this.loginForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.loginForm){
      return;
    }

    const form = this.loginForm;
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
    this.loginService.user.correo_electronico = this.loginForm.value.correo_electronico;
    this.loginService.user.clave = this.loginForm.value.clave;

    this.loginService.validateLogin(this.loginService.user)
      .subscribe(person => {

        /* Limpiar servicio
        for (var member in this.registerService.user) {
          delete this.registerService.user[member];
        };

        this.registerService.user.correo_electronico = '';
        this.registerService.user.clave = '';*/

        // TODO: Redireccion a la ruta apropiada cuando el auth este listo
        console.log("Iniciando sesion")
        //console.log(person)
      },
      errorMessage => {

      })

    // if (this.loginForm.valid){
    //   this.nextPage();
    // }
    // else{
    //   this.messageService.add({severity:'error', summary: 'Error', detail: 'Hubo datos inválidos o incompletos'});
    // }
  }

  nextPage(): void {
    this.router.navigate(['questions/add']);
  }

}
