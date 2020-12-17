import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../../core/services/auth/login.service';
import { Users } from '../../core/classes/auth/users';
import {SessionService} from '../../core/services/auth/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit {

  sent_form = false;
  user: Users;

  /* Form */
  loginForm: FormGroup;
  @ViewChild('lform') loginFormDirective;

  formErrors = {
    correo_electronico: '',
    clave: '',
  };

  validationMessages = {
    correo_electronico: {
      required: 'Correo electrónico es requerido',
      pattern: 'Correo electronico debe tener un formato válido'
    },
    clave: {
      required: 'Clave es requerida'
    }
  };

  constructor(private fb: FormBuilder,
              private loginService: LoginService,
              private messageService: MessageService,
              private sessionService: SessionService,
              private router: Router) {
    this.createForm();
  }

  ngOnInit(): void {
  }

  createForm() {
    this.loginForm = this.fb.group({
      correo_electronico: [
        '',
        [
          Validators.required,
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ],
      clave: [
        '',
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

  onValueChange(data?: any) {
    /* If form hasn't been created */
    if (!this.loginForm) {
      return;
    }

    const form = this.loginForm;
    for (const field in this.formErrors) {
      if (this.formErrors.hasOwnProperty(field)) {
        // clear previous error message if any
        this.formErrors[field] = '';
        const control = form.get(field);

        // if field is modified by user
        if (control && control.dirty && !control.valid) {
          const messages = this.validationMessages[field];

          // check if i'm adding the error message to the field
          for (const key in control.errors) {
            if (control.errors.hasOwnProperty(key)) {
              this.formErrors[field] += messages[key] + ' ';
            }
          }
        }
      }
    }
  }

  onSubmit() {
    this.sent_form = true;
    this.user = new Users();
    this.user.email = this.loginForm.value.correo_electronico;
    this.user.password = this.loginForm.value.clave;

    this.loginService.validateLogin(this.user)
      .subscribe(person => {
      if (person == null){
        this.messageService.add({severity: 'error', summary: 'Error', detail: 'Usuario o clave incorrectos.'});
      }
      else{
        this.sessionService.setCurrentSession(person);
        this.messageService.add({severity: 'success', summary: 'Exito', detail: 'Usuario validado correctamente.'});
        this.nextPage();
      }

      this.sent_form = false;
      },
      errorMessage => {
        this.sent_form = false;
        this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
      });
  }

  nextPage(): void {
    this.router.navigate(['/profile']);
  }
}
