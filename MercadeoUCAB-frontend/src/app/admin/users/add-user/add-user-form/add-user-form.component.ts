import { Component, OnInit, ViewChild} from '@angular/core';
import { SelectItem } from 'primeng/api';
import { Person } from '../../../../core/classes/profile/person';
import { UserService } from '../../../../core/services/admin/user.service';
import { ACCOUNT_XTATUS } from '../../../../core/constants/account_status';
import { Child } from '../../../../core/classes/profile/child';

import { NgxSpinnerService } from 'ngx-spinner';
import { MessageService } from 'primeng/api';
import { RxwebValidators } from '@rxweb/reactive-form-validators';
import { Router } from '@angular/router';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PlaceService } from 'src/app/core/services/profile/place.service';
import { replaceDateWithValue, replaceKeyWithValue } from 'src/app/core/functions/common_functions';
import { OcupacionService } from 'src/app/core/services/profile/ocupacion.service';
import { NivelAcademicoService } from 'src/app/core/services/profile/nivel-academico.service';
import { DeviceService } from 'src/app/core/services/profile/device.service';
import { DisponibilidadService } from 'src/app/core/services/profile/disponibilidad.service';
import { RolService } from 'src/app/core/services/profile/rol.service';
import { GeneroService } from 'src/app/core/services/profile/genero.service';
import { EdocivilService } from 'src/app/core/services/profile/edocivil.service';
import { Genero } from 'src/app/core/classes/profile/genero';


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
  estado_cuenta: SelectItem[];
  roles: SelectItem[];
  tieneHijos: SelectItem[];
  hijos: Child[] = [];
  niveles_academicos: SelectItem[];
  niveles_socioeconomicos: SelectItem[];
  paises: SelectItem[];
  estados: SelectItem[];
  ciudades: SelectItem[];
  parroquias: SelectItem[];
  codigos: SelectItem[];
  fecha_nacimiento: Date;
  persona: Person;
  dataPersona = false;
  loading = false;
  personErrorMessage: string;
  selectedGenreValue: number;
  selectedEdoCivilValue: number;
  selectedStatus: number;
  hasKids: boolean;
  ocupaciones: SelectItem[];
  sent_form = false;
  es: any;
  estado: boolean;
  ciudad: boolean;
  parroquia: boolean;
  rol: boolean;
  genero_h: Genero = {
    _id: 0,
    nombre: '',
  };

  /* Form */
  userForm: FormGroup;
  @ViewChild('uform') userFormDirective;

  formErrors = {
    correo_electronico: '',
    clave: '',
    confirmar_clave: '',
    primer_nombre: '',
    primer_apellido: '',
    documento_de_identificacion: '',
    genero: '',
    estado_civil: '',
    fecha_nacimiento: '',
    pais: '',
    ciudad: '',
    estado: '',
    parroquia: '',
    telefono: '',
    ocupacion: '',
    hijos: '',
    niv_academico: '',
    niv_socioeconomico: '',
    dispositivos: '',
    hora_inicial: '',
    hora_final: '',
    estado_cuenta: '',
    rol: '',
    personas_hogar: '',
    tiene_hijos: '',
    nombre_hijo: '',
    apellido_hijo: '',
    genero_hijo: '',
    fecha_de_nacimiento_hijo: ''
  };


  validationMessages = {
    estado_cuenta: {
      required: 'Estado de la cuenta es requerido',
    },
    rol: {
      required: 'Rol es requerido',
    },
    correo_electronico: {
      required: 'Correo electrónico es requerido',
      pattern: 'Correo electronico debe tener un formato válido'
    },
    clave: {
      required: 'Clave es requerida',
      pattern: 'Clave debe contener al menos 8 caracteres, 1 letra, 1 numero y 1 caracter especial'
    },
    confirmar_clave: {
      required: 'Confirmar clave es requerida',
      compare: 'Clave y confirmar clave deben coincidir'
    },
    primer_nombre: {
      required: 'Primer nombre es requerido',
      minlength: 'Primer nombre debe tener al menos 2 caracteres',
      maxlength: 'Primer nombre no puede tener más de 50 caracteres'
    },
    primer_apellido: {
      required: 'Primer apellido es requerido',
      minlength: 'Primer apellido debe tener al menos 2 caracteres',
      maxlength: 'Primer apellido no puede tener más de 50 caracteres'
    },
    documento_de_identificacion: {
      required: 'Documento de identificación es requerido',
      minlength: 'Documento de identificación debe tener al menos 8 caracteres',
      maxlength: 'Documento de identificación no debe pasar de los 50 caracteres'
    },
    genero: {
      required: 'Genero es requerido',
    },
    estado_civil: {
      required: 'Estado civil es requerido',
    },
    fecha_nacimiento: {
      required: 'Fecha de nacimiento es requerido',
    },
    pais: {
      required: 'Pais es requerido',
    },
    ciudad: {
      required: 'Ciudad es requerido',
    },
    estado: {
      required: 'Estado es requerido',
    },
    parroquia: {
      required: 'Parroquia es requerido',
    },
    telefono: {
      required: 'Teléfono es requerido',
      pattern: 'Teléfono debe ser un campo numérico',
    },
    ocupacion: {
      required: 'Ocupación es requerido',
    },
    niv_academico: {
      required: 'Nivel academico es requerido',
    },
    niv_socioeconomico: {
      required: 'Nivel socioeconomico es requerido',
    },
    dispositivos: {
      required: 'Dispositivos de dispositivos es requerido',
    },
    hora_inicial: {
      required: 'Hora inicial de disponibilidad es requerido',
    },
    hora_final: {
      required: 'Hora final de disponibilidad es requerido',
    },
    personas_hogar: {
      min: 'El número de personas con la que vive no puede ser cero',
      pattern: 'Personas en hogar debe ser numérico'
    },
    nombre_hijo: {
      required: 'El nombre del hijo es requerido',
      minlength: 'El nombre del hijo debe tener al menos 2 caracteres',
      maxlength: 'El nombre del hijo no debe exceder los 50 caracteres'
    },
    apellido_hijo: {
      required: 'El apellido del hijo es requerido',
      minlength: 'El apellido del hijo debe tener al menos 2 caracteres',
      maxlength: 'El apellido del hijo no debe exceder los 50 caracteres'
    },
    genero_hijo: {
      required: 'El género del hijo es requerido'
    },
    fecha_de_nacimiento_hijo: {
      required: 'La fecha de nacimiento del hijo es requerida'
    }

  };

  showKidsForm = false;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private userService: UserService,
    private placeService: PlaceService,
    private rolService: RolService,
    private ocupacionService: OcupacionService,
    private nivelAcademicoService: NivelAcademicoService,
    private deviceService: DeviceService,
    private disponibilidadService: DisponibilidadService,
    private generoService: GeneroService,
    private edoCivilService: EdocivilService,
    private messageService: MessageService) {
    this.createForm();
    this.generoService.getGeneros().subscribe((generos) => {
      this.generos = replaceKeyWithValue(generos);
    });

    this.edoCivilService.getEdosCiviles().subscribe((edos) => {
      this.edos_civil = replaceKeyWithValue(edos);
    });
    this.estado_cuenta = ACCOUNT_XTATUS;
    this.rolService.getRoles().subscribe((roles) => {
      this.roles = replaceKeyWithValue(roles);
    });
    this.tieneHijos = [
      {
        label: 'Si',
        value: true
      },
      {
        label: 'No',
        value: false
      }];

    this.placeService.getCountries().subscribe((countries) => {
        this.paises = replaceKeyWithValue(countries);
      });
    this.ocupacionService.getOcupaciones().subscribe((ocupations) => {
        this.ocupaciones = replaceKeyWithValue(ocupations);
      });
    this.nivelAcademicoService.getNivelesAcademicos().subscribe((niveles) => {
        this.niveles_academicos = replaceKeyWithValue(niveles);
      });
    this.deviceService.getDevices().subscribe((devices) => {
        this.dispositivos = replaceKeyWithValue(devices);
      });
    this.disponibilidadService.getDisponibilidades().subscribe((disponibilidades) => {
        console.log(replaceDateWithValue(disponibilidades));
        this.horario_inicial = replaceDateWithValue(disponibilidades);
        this.horario_final = replaceDateWithValue(disponibilidades);
      });
  }


  ngOnInit(): void {
    // this.spinner.show();
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
      dayNamesShort: ['dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb'],
      dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
      monthNamesShort: ['ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul', 'ago', 'sep', 'oct', 'nov', 'dic'],
      today: 'Hoy',
      clear: 'Borrar'
    };

    this.estado = true;
    this.ciudad = true;
    this.parroquia = true;

    this.placeService.getCountries().subscribe((countries) => {
      this.paises = replaceKeyWithValue(countries);
    });


    this.createForm();
  }

  createForm(){

    this.userForm = this.fb.group({
      correo_electronico: [
        this.userService.persona.email,
        [
          Validators.required,
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ],
      clave: [
        this.userService.persona.password,
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
      primer_nombre: [
        this.userService.persona.fkPersona.primerNombre,
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)
      ]],
      primer_apellido: [
        this.userService.persona.fkPersona.primerApellido,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50)
        ]
      ],
      documento_de_identificacion: [
        this.userService.persona.fkPersona.documentoIdentidad,
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(50)
        ]
      ],
      personas_hogar: [
        this.userService.persona.fkPersona.numero_personas_encasa,
        [
          Validators.min(1),
          Validators.pattern('^[0-9]*$')
        ]
      ],
      tiene_hijos: this.userService.persona.fkPersona.tiene_hijos,
      nombre_hijo: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50)
        ]
      ],
      apellido_hijo: [
        '',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50)
        ]
      ],
      genero_hijo: [
        null,
        [
          Validators.required
        ]
      ],
      fecha_de_nacimiento_hijo: [
        null,
        [
          Validators.required
        ]
      ],
      genero: this.userService.persona.fkPersona.fkGenero,
      dispositivos: this.userService.persona.fkPersona.dispositivos,
      estado_civil: this.userService.persona.fkPersona.fkEdoCivil,
      fecha_de_nacimiento: this.userService.persona.fkPersona.fechaNacimiento,
      estado_cuenta: this.userService.user.estado,
      horario_inicial: this.userService.persona.fkPersona.id_horario_inicial,
      horario_final: this.userService.persona.fkPersona.id_horario_final,
      nivel_academico: this.userService.persona.fkPersona.id_nivel_academico,
      nivel_socioeconomico: this.userService.persona.fkPersona.id_nivel_socioeconomico,
      pais: this.userService.persona.fkPersona.id_pais,
      estado: this.userService.persona.fkPersona.id_estado,
      ciudad: this.userService.persona.fkPersona.id_ciudad,
      parroquia: this.userService.persona.fkPersona.id_parroquia,
      codigo_pais: this.userService.persona.fkPersona.codigo_pais,
      ocupacion: this.userService.persona.fkPersona.ocupacion,
      rol: this.userService.persona.fkRol._id,
      telefono: [
        this.userService.persona.fkPersona.telefono.numero,
        [
          Validators.pattern('^[0-9]*$')
        ]
      ],
    });

    this.userForm.reset(this.userForm.value);

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
    this.sent_form = true;
// Informacion basica
    this.userService.persona.email = this.userForm.value.correo_electronico;
    this.userService.persona.fkRol._id = this.userForm.value.rol;
    this.userService.persona.password = this.userForm.value.clave;
    this.userService.persona.confirmar_clave = this.userForm.value.confirmar_clave;
    this.userService.persona.estado = 1;
// Si ES ENCUESTADO
    if (this.rol){
    this.userService.persona.fkPersona.primerNombre = this.userForm.value.primer_nombre;
    this.userService.persona.fkPersona.primerApellido = this.userForm.value.primer_apellido;
    this.userService.persona.fkPersona.documentoIdentidad = this.userForm.value.documento_de_identificacion;
    this.userService.persona.fkPersona.fkGenero._id = this.userForm.value.genero;
    this.userService.persona.fkPersona.fkEdoCivil._id = this.userForm.value.estado_civil;
    this.userService.persona.fkPersona.fechaNacimiento = this.userForm.value.fecha_de_nacimiento;
    this.userService.persona.fkPersona.dispositivos = this.userForm.value.dispositivos;
// Informacion socioeconomica
    this.userService.persona.fkPersona.ocupacion = this.userForm.value.ocupacion;
    this.userService.persona.fkPersona.id_nivel_academico = this.userForm.value.nivel_academico;
    this.userService.persona.fkPersona.id_nivel_socioeconomico = this.userForm.value.nivel_socioeconomico;
// Informacion familiar
    this.userService.persona.fkPersona.numero_personas_encasa = this.userForm.value.personas_hogar;
    this.userService.persona.fkPersona.tiene_hijos = this.userForm.value.tiene_hijos;
    this.userService.persona.fkPersona.hijos = this.hijos;
// Informacion de contacto
    this.userService.persona.fkPersona.codigo_pais = this.userForm.value.codigo_pais;
    this.userService.persona.fkPersona.telefono = this.userForm.value.telefono;
// Informacion de tiempo disponible
    this.userService.persona.fkPersona.id_horario_inicial._id = this.userForm.value.horario_inicial;
    this.userService.persona.fkPersona.id_horario_final._id = this.userForm.value.horario_final;

    if (this.parroquia && this.userForm.value.parroquia != 0){
      this.userService.persona.fkPersona.fkLugar._id = this.userForm.value.parroquia;
    }
    else if (this.ciudad && this.userForm.value.ciudad != 0){
      this.userService.persona.fkPersona.fkLugar._id = this.userForm.value.ciudad;
    }
    else if (this.estado && this.userForm.value.estado != 0){
      this.userService.persona.fkPersona.fkLugar._id = this.userForm.value.estado;
    }
    else {
      this.userService.persona.fkPersona.fkLugar._id = this.userForm.value.pais;
    }
    

    if (this.userService.persona.email && this.userService.persona.password
        && this.userService.persona.confirmar_clave && this.userService.persona.fkPersona.primerNombre
        && this.userService.persona.fkPersona.primerApellido && this.userService.persona.fkPersona.documentoIdentidad
        && this.userService.persona.fkPersona.fechaNacimiento && this.userService.persona.fkPersona.fkGenero
        && this.userService.persona.fkPersona.id_nivel_academico
        && this.userService.persona.fkRol._id
        && this.userService.persona.fkPersona.telefono
        && this.userService.persona.fkPersona.numero_personas_encasa){
          console.log(this.userService.persona);
        // this.userService.persona.id_estado && this.userService.persona.id_ciudad && this.userService.persona.id_parroquia
      // if(this.userForm.valid){
        /* SUBMIT FORM */
          this.userService.postPerson(this.userService.persona)
          .subscribe(person => {
            console.log('REGISTERED');
            this.userForm.reset();
            this.previousPage();
          },
          errorMessage => {
            console.log('FALLO');
            this.sent_form = false;
            this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
          });
      }
      else {
        this.showError();
        this.sent_form = false;
      }
    }
    else{
      this.userService.persona.fkPersona = this.userService.personadata;
      // Si NO es ENCUESTADO
      if (this.userService.persona.email && this.userService.persona.password
        && this.userService.persona.confirmar_clave && this.userService.persona.fkRol._id){

          console.log(this.userService.persona);

        /* SUBMIT FORM */
          this.userService.postPerson(this.userService.persona)
          .subscribe(person => {
            console.log('REGISTERED');
            this.userForm.reset(this.userForm.value);
            this.previousPage();
          },
          errorMessage => {
            console.log('FALLO');
            this.sent_form = false;
            this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
          });
      }
      else {
        this.showError();
        this.sent_form = false;
      }

      }

    }



    userHasKids(event){
      if (event.value === false){
        this.hijos = [];
        this.showKidsForm = false;
      }
    }

    showAddKidForm(){
      this.showKidsForm = true;
    }

    hideAddKidForm(){
      this.showKidsForm = false;
    }

    validateAddKidForm(){
      this.genero_h._id = this.userForm.value.genero_hijo;
      this.genero_h.nombre = this.generos[this.userForm.value.genero_hijo - 1].label;
      this.hijos.push({
          primerNombre: this.userForm.value.nombre_hijo,
          primerApellido: this.userForm.value.apellido_hijo,
          fkGenero: this.genero_h,
          fechaNacimiento: this.userForm.value.fecha_de_nacimiento_hijo
      });
      this.hideAddKidForm();
    }

    deleteChild(kid){
      const index = this.hijos.indexOf(kid);
      if (index > -1) {
        this.hijos.splice(index, 1);
      }
    }

  getStates(event){
    this.ciudades = [];
    this.parroquias = [];
    this.placeService.getStates(event.value).subscribe((states) => {
      if (states.length){
        this.estado = true;
        this.estados = replaceKeyWithValue(states);
      }
      else{
        this.estado = false;
        this.ciudad = false;
        this.parroquia = false;
      }
    });
  }

  getCities(event){
    this.parroquias = [];
    this.placeService.getCities(event.value).subscribe((cities) => {
      if (cities.length){
        this.ciudad = true;
        this.ciudades = replaceKeyWithValue(cities);
      }
      else{
        this.ciudad = false;
        this.parroquia = false;
      }
    });
  }

  getCounties(event){
    this.placeService.getCounties(event.value).subscribe((counties) => {
      if (counties.length){
        this.parroquia = true;
        this.parroquias = replaceKeyWithValue(counties);
      }
      else{
        this.parroquia = false;
      }
    });
  }

  checkRol(event){
    if (event.value === 4){
      this.rol = true;
    }
    else{
      this.rol = false;
    }
  }

  showError() {
    this.messageService.add({severity: 'error', summary: 'Error', detail: 'Faltan campos requeridos'});
  }

  previousPage(): void {
    this.router.navigate(['/users']);
    this.userForm.reset();
  }
}
