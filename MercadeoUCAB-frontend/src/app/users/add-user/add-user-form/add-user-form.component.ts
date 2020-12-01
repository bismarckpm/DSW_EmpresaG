import { Component, OnInit, ViewChild} from '@angular/core';
import { SelectItem } from 'primeng/api';
import { CardModule } from 'primeng/card';
import { Person } from '../../../classes/person';
import { UserService } from '../../../services/user.service';
import { GENDERS } from '../../../constants/gender';
import { DEVICES } from '../../../constants/device';
import { CIVIL_STATUSES } from '../../../constants/civil_status';
import { ROLES } from '../../../constants/rol';
import { ACCOUNT_XTATUS } from '../../../constants/account_status';

import { NgxSpinnerService } from "ngx-spinner";
import { ConfirmationService } from 'primeng/api';
import { MenuItem, MessageService, PrimeNGConfig } from 'primeng/api';
import { RxwebValidators } from '@rxweb/reactive-form-validators'
import { Router } from '@angular/router'



/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SCHEDULES } from 'src/app/constants/schedule';
import { SOCIAL_STATUSES } from 'src/app/constants/social_status';
import { ACADEMICS } from 'src/app/constants/academics';


@Component({
  selector: 'app-add-user-form',
  templateUrl: './add-user-form.component.html',
  styleUrls: ['./add-user-form.component.scss'],
  providers: [MessageService]

})

export class AddUserFormComponent implements OnInit {

  generos: SelectItem[];
  dispositivos: SelectItem[];
  horario_inicial: SelectItem[];
  horario_final: SelectItem[];
  edos_civil: SelectItem[];
  account_stat: SelectItem[];
  roles: SelectItem[];
  tieneHijos: SelectItem[];
  niveles_academicos: SelectItem[];
  niveles_socioeconomicos: SelectItem[];
  fecha_nacimiento: Date;
  persona: Person;
  dataPersona: boolean = false;
  loading: boolean = false;
  personErrorMessage: string;
  selectedGenreValue: number;
  selectedEdoCivilValue: number;
  selectedHoraI: number;
  selectedHoraF: number;
  selectedPais: number;
  selectedEstado: number;
  selectedCiudad: number;
  selectedParroquia: number;
  selectedStatus: number;
  selectRol: number;
  hasKids: boolean;
  sent_form: boolean = false;
  es: any;

  /* Form */
  userForm: FormGroup;
  @ViewChild('uform') userFormDirective;

  formErrors = {
    'correo_electronico': '',
    'clave': '',
    'confirmar_clave': '',
    'primer_nombre': '',
    'primer_apellido': '',
    'documento_de_identificacion': '',
    'genero': '',
    'estado_civil': '',
    'fecha_nacimiento': '',
    'pais': '',
    'ciudad': '',
    'estado': '',
    'parroquia': '',
    'telefono': '',
    'ocupacion': '',
    'personas_hogar': '',
    'hijos': '',
    'niv_academico': '',
    'niv_socioeconomico': '',
    'dispositivos': '',
    'hora_inicial': '',
    'hora_final': '',
    'estado_cuenta': '',
    'rol': ''
  };


  validationMessages = {
    'estado_cuenta': {
      'required': 'Estado de la cuenta es requerido',
    },
    'rol':{
      'required': 'Rol es requerido',
    },
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
    },
    'genero': {
      'required': 'Genero es requerido',
    },
    'estado_civil': {
      'required': 'Estado civil es requerido',
    },
    'fecha_nacimiento': {
      'required': 'Fecha de nacimiento es requerido',
    },
    'pais': {
      'required': 'Pais es requerido',
    },
    'ciudad': {
      'required': 'Ciudad es requerido',
    },
    'estado': {
      'required': 'Estado es requerido',
    },
    'parroquia': {
      'required': 'Parroquia es requerido',
    },
    'telefono': {
      'required': 'Teléfono es requerido',
    },
    'ocupacion': {
      'required': 'Ocupación es requerido',
    },
    'personas_hogar': {
      'required': 'Cantidad de personas en el hogar es requerido',
    },
    'hijos': {
      'required': 'Cantidad de hijos es requerido / En el caso de no tener, seleccione 0',
    },
    'niv_academico': {
      'required': 'Nivel academico es requerido',
    },
    'niv_socioeconomico': {
      'required': 'Nivel socioeconomico es requerido',
    },
    'dispositivos': {
      'required': 'Dispositivos de dispositivos es requerido',
    },
    'hora_inicial': {
      'required': 'Hora inicial de disponibilidad es requerido',
    },
    'hora_final': {
      'required': 'Hora final de disponibilidad es requerido',
    },

  };

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private userService: UserService,
    private messageService: MessageService) {
    this.createForm();
    this.generos = GENDERS;
    this.dispositivos = DEVICES;
    this.horario_inicial = SCHEDULES;
    this.horario_final = SCHEDULES;
    this.edos_civil = CIVIL_STATUSES;
    this.niveles_academicos = ACADEMICS;
    this.niveles_socioeconomicos = SOCIAL_STATUSES;
    this.account_stat = ACCOUNT_XTATUS;
    this.roles = ROLES;
    this.tieneHijos = [
      {
        label: 'Si',
        value: true
      },
      {
        label: 'No',
        value: false
      }];
      
  }

  ngOnInit(): void {
    this.spinner.show();
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
      dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
      dayNamesMin: ["D", "L", "M", "X", "J", "V", "S"],
      monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
      monthNamesShort: ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"],
      today: 'Hoy',
      clear: 'Borrar'
    }

    this.loading = true;
    this.userService.getNewPerson(1).subscribe((p) => {
      this.persona = p;
      this.loading = false;
      this.selectedGenreValue = Number.parseInt(p.genero);  
      this.selectedEdoCivilValue = Number.parseInt(p.estado_civil);
      this.fecha_nacimiento = new Date(p.fecha_de_nacimiento);
      this.hasKids = p.tiene_hijos;

      this.createForm();
      this.spinner.hide();
    }, errorMessage => {
      this.loading = false;
      this.spinner.hide();
      this.personErrorMessage = errorMessage;
    })
  }

  createForm(){
    this.userForm = this.fb.group({
      correo_electronico: [
        this.userService.persona.correo_electronico,
        [
          Validators.required,
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ],
      clave: [this.userService.persona.clave,
        [
          Validators.required,
          Validators.pattern(/^(?=.*\d)(?=.*[a-zA-Z])(?=.*[\W_]).{8,40}$/)
        ]
      ],
      confirmar_clave: [
        this.userService.persona.confirmar_clave,
        [
          Validators.required,
          RxwebValidators.compare({fieldName: 'clave'})
        ]
      ],
      primer_nombre: [this.userService.persona.primer_nombre,
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)
      ]],
      primer_apellido: [
        this.userService.persona.primer_apellido,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50)
        ]
      ],
      documento_de_identificacion: [this.userService.persona.documento_de_identificacion,
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(50)
        ]
      ],
      genero: this.userService.persona.genero,
      dispositivos: this.userService.persona.dispositivos,
      estado_civil: this.userService.persona.estado_civil,
      fecha_de_nacimiento: this.userService.persona.fecha_de_nacimiento,
      estado_cuenta: this.userService.user.status,
      horario_inicial: this.userService.persona.id_horario_inicial,
      horario_final: this.userService.persona.id_horario_final,
      nivel_academico: this.userService.persona.id_nivel_academico,
      nivel_socioeconomico: this.userService.persona.id_nivel_socioeconomico,
    });
  
    this.userForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }


  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.userForm){
      return;
    }

    const form = this.userForm;
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
    this.userService.persona.correo_electronico = this.userForm.value.correo_electronico;
    this.userService.persona.clave = this.userForm.value.clave;
    this.userService.persona.confirmar_clave = this.userForm.value.confirmar_clave;
    this.userService.persona.primer_nombre = this.userForm.value.primer_nombre;
    this.userService.persona.primer_apellido = this.userForm.value.primer_apellido;
    this.userService.persona.documento_de_identificacion = this.userForm.value.documento_de_identificacion;
    this.userService.persona.genero = this.userForm.value.genero;
    this.userService.persona.estado_civil = this.userForm.value.estado_civil;
    this.userService.persona.fecha_de_nacimiento = this.userForm.value.fecha_de_nacimiento;
    this.userService.persona.dispositivos = this.userForm.value.dispositivos;
    this.userService.persona.id_nivel_academico = this.userForm.value.nivel_academico;
    this.userService.persona.id_nivel_socioeconomico = this.userForm.value.nivel_socioeconomico;

    if (this.userService.persona.correo_electronico && this.userService.persona.clave
      && this.userService.persona.confirmar_clave && this.userService.persona.primer_nombre
      && this.userService.persona.primer_apellido && this.userService.persona.documento_de_identificacion
      && this.userService.persona.fecha_de_nacimiento && this.userService.persona.genero
      && this.userService.persona.id_nivel_academico && this.userService.persona.id_nivel_socioeconomico){

      /* SUBMIT FORM */
      this.userService.postRegPerson(this.userService.persona)
        .subscribe(person => {
          console.log("REGISTERED")
        },
        errorMessage => {
          this.sent_form = false;
          this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
        })
    }
    else {
      this.showError()
      this.sent_form = false;
    }
    //console.log(this.registerService.user);
  }

  showError() {
    this.messageService.add({severity:'error', summary: 'Error', detail: 'Faltan campos requeridos'});
  }

  previousPage(): void {
    this.router.navigate(['/users'])
  }
}
