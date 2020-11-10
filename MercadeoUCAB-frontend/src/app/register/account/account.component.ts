import { Component, OnInit, ViewChild } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { GENDERS } from '../../constants/gender';
import { CIVIL_STATUSES } from '../../constants/civil_status';
import { Router } from '@angular/router'

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';
import { RxwebValidators } from '@rxweb/reactive-form-validators'

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  generos: SelectItem[];
  estados_civiles: SelectItem[];

  /* Form */
  accountForm: FormGroup;
  @ViewChild('aform') accountFormDirective;

  formErrors = {
    'correo_electronico': '',
    'clave': '',
    'confirmar_clave': '',
    'primer_nombre': '',
    'primer_apellido': '',
    'documento_de_identificacion': '',
  };

  validationMessages = {
    'correo_electronico': {
      'required': 'Correo electrónico es requerido',
      'pattern': 'Correo electronico debe tener un formato válido'
    },
    'clave': {
      'required': 'Clave es requerida',
      'pattern': 'Clave debe contener al menos 8 caracteres, 1 letra, 1 numero y 1 caracter especial'
    },
    'confirmar_clave': {
      'required': 'Confirmar clave es requerida',
      'compare': 'Clave y confirmar clave deben coincidir'
    },
    'primer_nombre':{
      'required': 'Primer nombre es requerido',
      'minlength': 'Primer nombre debe tener al menos 2 caracteres',
      'maxlength': 'Primer nombre no puede tener más de 50 caracteres'
    },
    'primer_apellido':{
      'required': 'Primer apellido es requerido',
      'minlength': 'Primer apellido debe tener al menos 2 caracteres',
      'maxlength': 'Primer apellido no puede tener más de 50 caracteres'
    },
    'documento_de_identificacion':{
      'required': 'Documento de identificación es requerido',
      'minlength': 'Documento de identificación debe tener al menos 8 caracteres',
      'maxlength': 'Documento de identificación no debe pasar de los 50 caracteres'
    }
  };

  es: any;

  constructor(private router: Router,
    private fb: FormBuilder,
    private registerService: RegisterService) { 
    this.generos = GENDERS;
    this.estados_civiles = CIVIL_STATUSES;
    this.createForm();
  }

  ngOnInit(): void {
    this.es = {
      firstDayOfWeek: 1,
      dayNames: [ "Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado" ],
      dayNamesShort: [ "dom","lun","mar","mié","jue","vie","sáb" ],
      dayNamesMin: [ "D","L","M","X","J","V","S" ],
      monthNames: [ "Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" ],
      monthNamesShort: [ "ene","feb","mar","abr","may","jun","jul","ago","sep","oct","nov","dic" ],
      today: 'Hoy',
      clear: 'Borrar'
  }
  }

  createForm(){
    this.accountForm = this.fb.group({
      correo_electronico: [
        this.registerService.user.correo_electronico,
        [
          Validators.required,
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ],
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
      ],
      primer_nombre: [this.registerService.user.primer_nombre,
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)
      ]],
      primer_apellido: [
        this.registerService.user.primer_apellido,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50)
        ]
      ],
      documento_de_identificacion: [this.registerService.user.documento_de_identificacion,
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(50)
        ]
      ],
      genero: this.registerService.user.genero,
      estado_civil: this.registerService.user.estado_civil,
      fecha_de_nacimiento: this.registerService.user.fecha_de_nacimiento
    });

    
    this.accountForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.accountForm){
      return;
    }

    const form = this.accountForm;
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
    this.registerService.user.correo_electronico = this.accountForm.value.correo_electronico;
    this.registerService.user.clave = this.accountForm.value.clave;
    this.registerService.user.confirmar_clave = this.accountForm.value.confirmar_clave;
    this.registerService.user.primer_nombre = this.accountForm.value.primer_nombre;
    this.registerService.user.primer_apellido = this.accountForm.value.primer_apellido;
    this.registerService.user.documento_de_identificacion = this.accountForm.value.documento_de_identificacion;
    this.registerService.user.genero = this.accountForm.value.genero;
    this.registerService.user.estado_civil = this.accountForm.value.estado_civil;
    this.registerService.user.fecha_de_nacimiento = this.accountForm.value.fecha_de_nacimiento;

    if (this.accountForm.valid){
      this.nextPage();
    }
  }

  nextPage(): void {
    this.router.navigate(['register/contact']);
  }

}
