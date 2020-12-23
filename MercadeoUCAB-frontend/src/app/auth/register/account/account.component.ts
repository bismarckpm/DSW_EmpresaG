import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { GENDERS } from '../../../core/constants/gender';
import { CIVIL_STATUSES } from '../../../core/constants/civil_status';
import {replaceKeyWithValue} from '../../../core/functions/common_functions';
import { Router } from '@angular/router'

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../../core/services/auth/register.service';
import { RxwebValidators } from '@rxweb/reactive-form-validators'
import { GeneroService } from '../../../core/services/profile/genero.service';
import { EdocivilService } from '../../../core/services/profile/edocivil.service';
import { from } from 'rxjs';
import { errorMonitor } from 'events';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss'],
  providers: [MessageService]
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
    private registerService: RegisterService,
    private messageService: MessageService,
    private generoService: GeneroService,
    private edocivilService: EdocivilService) {
    // this.generos = GENDERS;
    this.generoService.getGeneros().subscribe((genres) => {
      this.generos = replaceKeyWithValue(genres);
    });
    // this.estados_civiles = CIVIL_STATUSES;
    this.edocivilService.getEdosCiviles().subscribe((edosciviles) => {
      this.estados_civiles = replaceKeyWithValue(edosciviles);
    });
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
        this.registerService.user.email,
        [
          Validators.required,
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ],
      clave: [this.registerService.user.password,
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
      primer_nombre: [this.registerService.user.fkPersona.primerNombre,
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)
      ]],
      primer_apellido: [
        this.registerService.user.fkPersona.primerApellido,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50)
        ]
      ],
      documento_de_identificacion: [this.registerService.user.fkPersona.documentoIdentidad,
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(50)
        ]
      ],
      genero: this.registerService.user.fkPersona.fkGenero,
      estado_civil: this.registerService.user.fkPersona.fkEdoCivil,
      fecha_de_nacimiento: this.registerService.user.fkPersona.fechaNacimiento
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
    console.log(this.accountForm.value.correo_electronico);
    console.log(this.accountForm.value.clave);
    console.log(this.accountForm.value.confirmar_clave);
    console.log(this.accountForm.value.primer_nombre);
    console.log(this.accountForm.value.primer_apellido);
    console.log(this.accountForm.value.documento_de_identificacion);
    console.log(this.accountForm.value.genero);
    console.log(this.accountForm.value.estado_civil);
    console.log(this.accountForm.value.fecha_de_nacimiento);

    this.registerService.user.email = this.accountForm.value.correo_electronico;
    this.registerService.user.password = this.accountForm.value.clave;
    this.registerService.user.confirmar_clave = this.accountForm.value.confirmar_clave;
    this.registerService.user.fkPersona.primerNombre = this.accountForm.value.primer_nombre;
    this.registerService.user.fkPersona.primerApellido = this.accountForm.value.primer_apellido;
    this.registerService.user.fkPersona.documentoIdentidad = this.accountForm.value.documento_de_identificacion;
    this.registerService.user.fkPersona.fkGenero._id = this.accountForm.value.genero;
    this.registerService.user.fkPersona.fkEdoCivil._id = this.accountForm.value.estado_civil;
    this.registerService.user.fkPersona.fechaNacimiento = this.accountForm.value.fecha_de_nacimiento;

    if (this.accountForm.valid){
      console.log('epa, achantate...');

      this.registerService.postValidRegister().subscribe((person) =>{
        this.nextPage();
      }, errorMessage => {
        this.messageService.add({severity:'error', summary: 'Error', detail: 'El correo utilizado ya se encuentra registrado.'});
      });

      // if (this.registerService.postValidRegister()){
      //   this.nextPage();
      // }
      // else{
      // }

    }
    else{
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Hubo datos inválidos o incompletos en el formulario'});
    }
  }

  nextPage(): void {
    this.router.navigate(['register/contact']);
  }

}
