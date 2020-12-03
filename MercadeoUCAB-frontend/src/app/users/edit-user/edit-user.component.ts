import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MenuItem, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { replaceKeyWithValue } from '../../functions/common_functions';
import { Option } from '../../classes/options';
import { NgxSpinnerService } from "ngx-spinner";
import { Person } from '../../classes/person';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { PhoneService } from 'src/app/services/phone.service';
import { PlaceService } from 'src/app/services/place.service';
import { Child } from 'src/app/classes/child';
import { RxwebValidators } from '@rxweb/reactive-form-validators';
import { GENDERS } from '../../constants/gender';
import { CIVIL_STATUSES } from '../../constants/civil_status';
import { DEVICES } from '../../constants/device';
import { ROLES } from '../../constants/rol';
import { STUDY_STATES } from '../../constants/study_states';
import { SOCIAL_STATUSES } from '../../constants/social_status';
import { SCHEDULES } from '../../constants/schedule';
import { ACADEMICS } from '../../constants/academics';
import { ACCOUNT_XTATUS } from 'src/app/constants/account_status';







@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss'],
  providers: [MessageService]
})
export class EditUserComponent implements OnInit {

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
  showKidsForm = false;
  dataPersona: boolean = false;
  loading: boolean = false;
  personErrorMessage: string;
  selectedGenreValue: number;
  selectedEdoCivilValue: number;
  selectedStatus: number;
  hasKids: boolean;
  sent_form: boolean = false;
  es: any;
  current_user: number;

  /* Form */
  userForm: FormGroup;
  @ViewChild('uforms') userFormDirective;

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
    'hijos': '',
    'niv_academico': '',
    'niv_socioeconomico': '',
    'dispositivos': '',
    'hora_inicial': '',
    'hora_final': '',
    'estado_cuenta': '',
    'rol': '',
    'personas_hogar': '',
    'tiene_hijos': '',
    'nombre_hijo': '',
    'apellido_hijo': '',
    'genero_hijo': '',
    'fecha_de_nacimiento_hijo': ''
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
      'pattern': 'Teléfono debe ser un campo numérico',
    },
    'ocupacion': {
      'required': 'Ocupación es requerido',
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
    'personas_hogar':{
      'min': 'El número de personas con la que vive no puede ser cero',
      'pattern': 'Personas en hogar debe ser numérico'
    },
    'nombre_hijo': {
      'required': 'El nombre del hijo es requerido',
      'minlength': 'El nombre del hijo debe tener al menos 2 caracteres',
      'maxlength': 'El nombre del hijo no debe exceder los 50 caracteres'
    },
    'apellido_hijo': {
      'required': 'El apellido del hijo es requerido',
      'minlength': 'El apellido del hijo debe tener al menos 2 caracteres',
      'maxlength': 'El apellido del hijo no debe exceder los 50 caracteres'
    },
    'genero_hijo': {
      'required': 'El género del hijo es requerido'
    },
    'fecha_de_nacimiento_hijo': {
      'required': 'La fecha de nacimiento del hijo es requerida'
    }

  };


  constructor(private Activatedroute:ActivatedRoute,
    private router:Router,
    private messageService: MessageService,
    private userService: UserService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private placeService: PlaceService, 
    private phoneService: PhoneService
    ) { 

      this.generos = GENDERS;
      this.edos_civil = CIVIL_STATUSES;
      this.dispositivos = DEVICES;
      this.horario_inicial = SCHEDULES;
      this.horario_final = SCHEDULES;
      this.niveles_academicos = ACADEMICS;
      this.niveles_socioeconomicos = SOCIAL_STATUSES;
      this.estado_cuenta = ACCOUNT_XTATUS;
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
      this.placeService.getCountries().subscribe((countries) => {
        this.paises = countries;
      });
  
      this.phoneService.getCodes().subscribe((codes) => {
        this.codigos = codes;
      });

      // this.selectedStatus = STATUS_CODES;

      /* If query is empty return 404 */
      if ((this.Activatedroute.snapshot.queryParamMap.get('pid')||0) == 0){
        this.router.navigate(['404']);
      }

      /* Get current user */
      else {
        this.spinner.show();
        this.loading = true;
        this.current_user = parseInt(this.Activatedroute.snapshot.queryParamMap.get('pid'));
        this.userService.getPerson(this.current_user).subscribe((persona) => {
            this.persona = persona;
            if (this.persona){
              this.loading = false;
              this.selectedGenreValue = Number.parseInt(persona.genero);
              this.selectedEdoCivilValue = Number.parseInt(persona.estado_civil);
              this.fecha_nacimiento = new Date(persona.fecha_de_nacimiento);
              this.hasKids = persona.tiene_hijos;
              this.hijos = persona.hijos;
        
              this.createForm();
              this.spinner.hide();
            }
            else {
              this.loading = false;
              this.spinner.hide();
              this.router.navigate(['404']);
            }
        });
      }
  }

  ngOnInit(): void {this.spinner.show();
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

  }

  createForm(){
    this.userForm = this.fb.group({
      correo_electronico: [
        this.persona.correo_electronico,
        [
          Validators.required, 
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ],
      clave: [
        this.persona.clave,
        [
          Validators.required,
          Validators.pattern(/^(?=.*\d)(?=.*[a-zA-Z])(?=.*[\W_]).{8,40}$/)
        ]
      ],
      confirmar_clave: [
        this.persona.confirmar_clave,
        [
          Validators.required,
          RxwebValidators.compare({fieldName: 'clave'})
        ]
      ],
      primer_nombre: [
        this.persona.primer_nombre,
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)
      ]],
      primer_apellido: [
        this.persona.primer_apellido,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50)
        ]
      ],
      documento_de_identificacion: [
        this.persona.documento_de_identificacion,
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(50)
        ]
      ],
      personas_hogar: [
        this.persona.personas_hogar,
        [
          Validators.min(1),
          Validators.pattern('^[0-9]*$')
        ]
      ],
      tiene_hijos: 
      this.persona.tiene_hijos,
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
      genero: this.persona.genero,
      // dispositivos: this.persona.dispositivos,
      estado_civil: this.persona.estado_civil,
      fecha_de_nacimiento: this.persona.fecha_de_nacimiento,
      estado_cuenta: this.userService.user.status, 
      horario_inicial: this.persona.id_horario_inicial,
      horario_final: this.persona.id_horario_final,
      nivel_academico: this.persona.id_nivel_academico,
      nivel_socioeconomico: this.persona.id_nivel_socioeconomico,
      pais: this.persona.id_pais,
      estado: this.persona.id_estado,
      ciudad: this.persona.id_ciudad,
      parroquia: this.persona.id_parroquia,
      codigo_pais: this.persona.codigo_pais,
      ocupacion: this.persona.ocupacion,
      telefono: [
        this.persona.telefono,
        [
          Validators.pattern('^[0-9]*$')
        ]
      ],
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
    this.sent_form = true;
// Informacion basica
    this.userService.persona.correo_electronico = this.userForm.value.correo_electronico;
    this.userService.persona.clave = this.userForm.value.clave;
    this.userService.persona.confirmar_clave = this.userForm.value.confirmar_clave;
    // this.userService.persona.estado_cuenta = this.userForm.value.estado_cuenta;
    this.userService.persona.primer_nombre = this.userForm.value.primer_nombre;
    this.userService.persona.primer_apellido = this.userForm.value.primer_apellido;
    this.userService.persona.documento_de_identificacion = this.userForm.value.documento_de_identificacion;
    this.userService.persona.genero = this.userForm.value.genero;
    this.userService.persona.estado_civil = this.userForm.value.estado_civil;
    this.userService.persona.fecha_de_nacimiento = this.userForm.value.fecha_de_nacimiento;
    this.userService.persona.dispositivos = this.userForm.value.dispositivos;
// Informacion socioeconomica
    this.userService.persona.ocupacion = this.userForm.value.ocupacion;
    this.userService.persona.id_nivel_academico = this.userForm.value.nivel_academico;
    this.userService.persona.id_nivel_socioeconomico = this.userForm.value.nivel_socioeconomico;
// Informacion familiar
    this.userService.persona.personas_hogar = this.userForm.value.personas_hogar;
    this.userService.persona.tiene_hijos = this.userForm.value.tiene_hijos;
    this.userService.persona.hijos = this.hijos;
// Informacion de contacto
    this.userService.persona.id_pais = this.userForm.value.pais;
    this.userService.persona.id_estado = this.userForm.value.estado;
    this.userService.persona.id_ciudad = this.userForm.value.ciudad;
    this.userService.persona.id_parroquia = this.userForm.value.parroquia;
    this.userService.persona.codigo_pais = this.userForm.value.codigo_pais;
    this.userService.persona.telefono = this.userForm.value.telefono;
// Informacion de tiempo disponible
    this.userService.persona.id_horario_inicial = this.userForm.value.horario_inicial;
    this.userService.persona.id_horario_final = this.userForm.value.horario_final;


    if (this.userService.persona.correo_electronico && this.userService.persona.clave
      && this.userService.persona.confirmar_clave && this.userService.persona.primer_nombre
      && this.userService.persona.primer_apellido && this.userService.persona.documento_de_identificacion
      && this.userService.persona.fecha_de_nacimiento && this.userService.persona.genero
      && this.userService.persona.id_nivel_academico && this.userService.persona.id_nivel_socioeconomico
      && this.userService.persona.id_pais 
      && this.userService.persona.codigo_pais && this.userService.persona.telefono
      && this.userService.persona.personas_hogar){

      // this.userService.persona.id_estado && this.userService.persona.id_ciudad && this.userService.persona.id_parroquia
    // if(this.userForm.valid){
      /* SUBMIT FORM */
      this.userService.postPerson(this.userService.persona)
        .subscribe(person => {
          console.log("REGISTERED")
          this.previousPage();
        },
        errorMessage => {
          console.log("FALLO")
          this.sent_form = false;
          this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
        })
    }
    else {
      this.showError()
      this.sent_form = false;
    }
  }

  userHasKids(event){
    if (event.value == false){
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
    if (this.userForm.valid){
      this.hijos.push({
        primer_nombre: this.userForm.value.nombre_hijo,
        primer_apellido: this.userForm.value.apellido_hijo,
        genero: this.userForm.value.genero_hijo,
        fecha_de_nacimiento: this.userForm.value.fecha_de_nacimiento_hijo
    });
    this.hideAddKidForm();
    }
  }

  deleteChild(kid){
    let index = this.hijos.indexOf(kid)
    if (index > -1)
      this.hijos.splice(index, 1)
  }

  getStates(event){
    this.ciudades = [];
    this.parroquias = [];
    this.placeService.getStates(event.value).subscribe((states) => {
      this.estados = states;
    })
  }

  getCities(event){
    this.parroquias = [];
    this.placeService.getCities(event.value).subscribe((cities) => {
      this.ciudades = cities;
    })
  }

  getCounties(event){
    this.placeService.getCounties(event.value).subscribe((counties) => {
      this.parroquias = counties;
    })
  }

  showError() {
    this.messageService.add({severity:'error', summary: 'Error', detail: 'Faltan campos requeridos'});
  }

  previousPage(): void {
    this.router.navigate(['/users'])
  }

}