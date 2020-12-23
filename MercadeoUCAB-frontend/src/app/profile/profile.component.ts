import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { CardModule } from 'primeng/card';
import { Person } from '../core/classes/profile/person';
import { RxwebValidators } from '@rxweb/reactive-form-validators';
import { GENDERS } from '../core/constants/gender';
import { CIVIL_STATUSES } from '../core/constants/civil_status';
import { Device, DEVICES } from '../core/constants/device';
import { Rol } from '../core/classes/profile/rol';
import { STUDY_STATES } from '../core/constants/study_states'; // Se usara NO borrar
import { SOCIAL_STATUSES } from '../core/constants/social_status';
import { SCHEDULES } from '../core/constants/schedule';
import { ACADEMICS } from '../core/constants/academics';
import { ACCOUNT_XTATUS } from 'src/app/core/constants/account_status';
import { RolService } from 'src/app/core/services/profile/rol.service';
import { GeneroService } from 'src/app/core/services/profile/genero.service';
import { EdocivilService } from 'src/app/core/services/profile/edocivil.service';
import { DeviceService } from 'src/app/core/services/profile/device.service';
import { DisponibilidadService } from 'src/app/core/services/profile/disponibilidad.service';
import { NivelAcademicoService } from 'src/app/core/services/profile/nivel-academico.service';
import { persondata } from 'src/app/core/classes/profile/persondata';
import { OcupacionService } from 'src/app/core/services/profile/ocupacion.service';
import { Place } from 'src/app/core/classes/profile/place';
import { Genero } from 'src/app/core/classes/profile/genero';
import { EdoCivil } from 'src/app/core/classes/profile/edocivil';
import { Disponibilidad } from 'src/app/core/classes/profile/disponibilidad';
import { telefono } from 'src/app/core/classes/profile/telefono';
import { NgxSpinnerService } from "ngx-spinner";

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SessionService } from '../core/services/auth/session.service';
import { UserService } from '../core/services/admin/user.service';
import { Child } from '../core/classes/profile/child';
import { ActivatedRoute, Router } from '@angular/router';
import { PlaceService } from '../core/services/profile/place.service';
import { PhoneService } from '../core/services/profile/phone.service';
import { replaceDateWithValue, replaceKeyWithValue } from '../core/functions/common_functions';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
  providers: [MessageService]
})
export class ProfileComponent implements OnInit {
  generos: SelectItem[];
  edos_civil: SelectItem[];
  tieneHijos: SelectItem[];
  fecha_nacimiento: Date;
  persona: Person;
  dataPersona: boolean = false;
  loading: boolean = false;
  personErrorMessage: string;
  selectedGenreValue: number;
  selectedEdoCivilValue: number;
  hasKids: boolean;
  es: any;
  
  dispositivos: SelectItem[];
  horario_inicial: SelectItem[];
  horario_final: SelectItem[];
  estado_cuenta: SelectItem[];
  // roles: SelectItem[];
  hijos: Child[] = [];
  niveles_academicos: SelectItem[];
  niveles_socioeconomicos: SelectItem[];
  paises: SelectItem[];
  estados: SelectItem[];
  ciudades: SelectItem[];
  parroquias: SelectItem[];
  codigos: SelectItem[];
  ocupaciones: SelectItem[];
  showKidsForm = false;
  showClaveForm = false;
  selectedStatus: number;
  sent_form: boolean = false;
  current_user: number;
  estado: boolean;
  ciudad: boolean;
  parroquia: boolean;
  selectedDevices: Device[];

  ocupacion: String;
  lvlAcademico: String;
  dir_bella: String;

  /* Form */
  profileForm: FormGroup;
  @ViewChild('pform') profileFormDirective;
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
    // 'rol': '',
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
    // 'rol':{
    //   'required': 'Rol es requerido',
    // },
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
    // private rolService: RolService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private placeService: PlaceService,
    private phoneService: PhoneService,
    private generoService: GeneroService,
    private edoCivilService: EdocivilService,
    private deviceService: DeviceService,
    private sessionService: SessionService,
    private ocupacionService: OcupacionService,
    private disponibilidadService: DisponibilidadService,
    private nivelAcademicoService: NivelAcademicoService,
    ) {

      this.dir_bella = "";
      // this.niveles_socioeconomicos = SOCIAL_STATUSES;
      this.estado_cuenta = ACCOUNT_XTATUS;

      this.ocupacionService.getOcupaciones().subscribe((ocupations) => {
        this.ocupaciones = replaceKeyWithValue(ocupations);
      });
      // this.niveles_academicos = ACADEMICS;
      this.nivelAcademicoService.getNivelesAcademicos().subscribe((niveles) => {
        this.niveles_academicos = replaceKeyWithValue(niveles);
      });
      // this.horario_inicial = SCHEDULES;
      // this.horario_final = SCHEDULES;
      this.disponibilidadService.getDisponibilidades().subscribe((disponibilidades) => {
        this.horario_inicial = replaceDateWithValue(disponibilidades);
        this.horario_final = replaceDateWithValue(disponibilidades);
      });
      // this.dispositivos = DEVICES;
      this.deviceService.getDevices().subscribe((devices) => {
        this.dispositivos = replaceKeyWithValue(devices);
      });
      // this.edos_civil = CIVIL_STATUSES;
      this.edoCivilService.getEdosCiviles().subscribe((edos) => {
        this.edos_civil = replaceKeyWithValue(edos);
      });
      // this.roles = ROLES;
      // this.rolService.getRoles().subscribe((roles) => {
      //   this.roles = replaceKeyWithValue(roles);
      // });
      // this.generos = GENDERS;
      this.generoService.getGeneros().subscribe((generos) => {
        this.generos = replaceKeyWithValue(generos);
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

        this.spinner.show();
        this.loading = true;
        // this.current_user = parseInt(this.Activatedroute.snapshot.queryParamMap.get('pid'));
        this.userService.getPerson(this.sessionService.getCurrentSession()._id).subscribe((persona) => {
        // this.userService.getPerson(this.current_user).subscribe((persona) => {
            this.persona = persona;

            console.log(this.persona);
            if (this.persona){
              this.loading = false;
              if(this.persona.fkRol._id == 4){
                this.dataPersona = true;
                this.persona.fkPersona.id_pais = new Place();
                this.persona.fkPersona.id_estado = new Place();
                this.persona.fkPersona.id_ciudad = new Place();
                this.persona.fkPersona.id_parroquia = new Place();
                this.manageLugar(persona.fkPersona.fkLugar);
                this.selectedDevices = this.persona.fkPersona.dispositivos;
              // this.selectedGenreValue = persona.fkPersona.fkGenero.value;
              // this.selectedEdoCivilValue = persona.fkPersona.fkEdoCivil.value;

                this.ocupacion = this.ocupaciones[parseInt(this.persona.fkPersona.ocupacion)-1].label;
                this.lvlAcademico = this.niveles_academicos[this.persona.fkPersona.id_nivel_academico-1].label;
                this.parseDireccion(this.persona.fkPersona.fkLugar);
                this.fecha_nacimiento = new Date(persona.fkPersona.fechaNacimiento);
                // this.hasKids = persona.fkPersona.tiene_hijos;
                this.hijos = persona.fkPersona.hijos;
                if (this.hijos.length > 0){
                  this.hasKids = true;
                  this.persona.fkPersona.tiene_hijos = true;
                }
                else{
                  this.hasKids = false;
                  this.persona.fkPersona.tiene_hijos = false;
                }

              }
              else{
                this.persona.fkPersona = new persondata();
                this.persona.fkPersona.id_pais = new Place();
                this.persona.fkPersona.id_estado = new Place();
                this.persona.fkPersona.id_ciudad = new Place();
                this.persona.fkPersona.id_parroquia = new Place();
                this.persona.fkPersona.fkLugar = new Place();
                this.persona.fkPersona.fkEdoCivil = new EdoCivil();
                this.persona.fkPersona.fkGenero = new Genero();
                this.persona.fkPersona.id_horario_inicial = new Disponibilidad();
                this.persona.fkPersona.id_horario_final = new Disponibilidad();
                this.persona.fkPersona.dispositivos = [];
                this.persona.fkPersona.documentoIdentidad = "";
                this.persona.fkPersona.fechaNacimiento = "";
                this.persona.fkPersona.hijos = [];
                this.persona.fkPersona.id_nivel_academico = 0;
                this.persona.fkPersona.numero_personas_encasa = 0;
                this.persona.fkPersona.ocupacion = "";
                this.persona.fkPersona.primerApellido = "";
                this.persona.fkPersona.primerNombre = "";
                this.persona.fkPersona.telefono = new telefono();
                this.persona.fkPersona.tiene_hijos = false;

              }
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

  }

  createForm() {
    this.profileForm = this.fb.group({
      correo_electronico: [
        this.persona.email,
        [
          Validators.required,
          Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
        ]
      ],
      clave: [
        '',
        [
          Validators.required,
          Validators.pattern(/^(?=.*\d)(?=.*[a-zA-Z])(?=.*[\W_]).{8,40}$/)
        ]
      ],
      confirmar_clave: [
        '',
        [
          Validators.required,
          RxwebValidators.compare({fieldName: 'clave'})
        ]
      ],
      primer_nombre: [
        this.persona.fkPersona.primerNombre,
      [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)
      ]],
      primer_apellido: [
        this.persona.fkPersona.primerApellido,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50)
        ]
      ],
      documento_de_identificacion: [
        this.persona.fkPersona.documentoIdentidad,
        [
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(50)
        ]
      ],
      personas_hogar: [
        this.persona.fkPersona.numero_personas_encasa,
        [
          Validators.min(1),
          Validators.pattern('^[0-9]*$')
        ]
      ],
      tiene_hijos: this.persona.fkPersona.tiene_hijos,
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
      genero: this.persona.fkPersona.fkGenero._id,
      dispositivos: this.persona.fkPersona.dispositivos.values,
      estado_civil: this.persona.fkPersona.fkEdoCivil._id,
      fecha_de_nacimiento: this.persona.fkPersona.fechaNacimiento,
      estado_cuenta: this.persona.estado,
      horario_inicial: this.persona.fkPersona.id_horario_inicial._id,
      horario_final: this.persona.fkPersona.id_horario_final._id,
      nivel_academico: this.persona.fkPersona.id_nivel_academico,
      // nivel_socioeconomico: this.persona.fkPersona.id_nivel_socioeconomico,
      pais: this.persona.fkPersona.id_pais._id,
      estado: this.persona.fkPersona.id_estado._id,
      ciudad: this.persona.fkPersona.id_ciudad._id,
      parroquia: this.persona.fkPersona.id_parroquia._id,
      // codigo_pais: this.persona.fkPersona.codigo_pais,
      ocupacion: this.persona.fkPersona.ocupacion,
      // rol: this.persona.fkRol._id,
      telefono: [
        this.persona.fkPersona.telefono,
        [
          Validators.pattern('^[0-9]*$')
        ]
      ],
    });


    this.profileForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  
  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.profileForm){
      return;
    }

    const form = this.profileForm;
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

  
  userHasKids(event){
    if (event.value == false){
      this.hijos = [];
      this.showKidsForm = false;
    }
  }

  showAddKidForm(){
    this.showKidsForm = true;
  }

  showChangeClaveForm(){
    this.showClaveForm = true;
  }

  hideChangeClaveForm(){
    this.showClaveForm = false;
  }

  hideAddKidForm(){
    this.showKidsForm = false;
  }

  generochild = new Genero();

  validateAddKidForm(){
    this.generochild._id = this.profileForm.value.genero_hijo;
    this.generochild.nombre = this.generos[this.profileForm.value.genero_hijo-1].label;
      this.hijos.push({
        primerNombre: this.profileForm.value.nombre_hijo,
        primerApellido: this.profileForm.value.apellido_hijo,
        fkGenero: this.generochild,
        fechaNacimiento: this.profileForm.value.fecha_de_nacimiento_hijo
    });
    this.hideAddKidForm();

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
      if (states.length){
        this.estado = true;
        this.estados = replaceKeyWithValue(states);
      }
      else{
        this.estado = false;
        this.ciudad = false;
        this.parroquia = false;
      }
    })
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
    })
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
    })
  }

  showError() {
    this.messageService.add({severity:'error', summary: 'Error', detail: 'Faltan campos requeridos'});
  }

  manageLugar(lugar: Place): void{
    // SI ES PAIS NOS DETENEMOS
    if (lugar.tipo == 0){

      this.persona.fkPersona.id_pais = lugar;
      this.placeService.getStates(this.persona.fkPersona.id_pais._id).subscribe((states) => {
        if (states.length){
          this.estado = true;
          this.estados = replaceKeyWithValue(states);
        }
        else{
          this.estado = false;
          this.ciudad = false;
          this.parroquia = false;
        }
      })
    }
    else{

      switch(lugar.tipo){

        case 1:
          this.persona.fkPersona.id_estado = lugar;
          this.estado = true;
          this.placeService.getCities(this.persona.fkPersona.id_estado._id).subscribe((cities) => {
            if (cities.length){
              this.ciudad = true;
              this.ciudades = replaceKeyWithValue(cities);
            }
            else{
              this.ciudad = false;
              this.parroquia = false;
            }
          })
          break;
        case 2:
          this.persona.fkPersona.id_ciudad = lugar;
          this.ciudad = true;
          this.placeService.getCounties(this.persona.fkPersona.id_ciudad._id).subscribe((counties) => {
            if (counties.length){
              this.parroquia = true;
              this.parroquias = replaceKeyWithValue(counties);
            }
            else{
              this.parroquia = false;
            }
          })
          break;
        case 3:

          this.persona.fkPersona.id_parroquia = lugar;
          this.parroquia = true;
          break;

      }

      this.manageLugar(lugar.fkLugar);

    }

  }

  

  parseDireccion(lugar: Place): void{
    // SI ES PAIS NOS DETENEMOS
    if (lugar.tipo == 0){

        this.dir_bella += lugar.nombre +", ";
    }
    else{

      if (lugar.fkLugar != null){
        this.parseDireccion(lugar.fkLugar);
      
        switch(lugar.tipo){

          case 1:
            this.dir_bella += lugar.nombre +", ";
            break;
          case 2:
            this.dir_bella += lugar.nombre +" ";
            break;
          case 3:

            this.dir_bella += lugar.nombre +" ";
            break;
        }
      }
    }
  }

  putUser(){
    this.userService.putUser(this.userService.persona).subscribe((p)=>{
      this.persona = p;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Usuario actualizado con éxito'});
      this.sent_form = false;
      // this.router.navigate(['/profile']);
      // this.editUser();
      // this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })

    this.hideChangeClaveForm();
  }
  
  onSubmit(){
    this.sent_form = true;
    // Informacion basica
    this.userService.persona._id = this.persona._id;
    this.userService.persona.email = this.persona.email;
    if (this.showClaveForm){
      this.userService.persona.password = this.profileForm.value.clave;
      this.userService.persona.confirmar_clave = this.profileForm.value.confirmar_clave;
    }
    else{
      this.userService.persona.password = "";
      this.userService.persona.confirmar_clave = "";
    }
    this.userService.persona.estado = this.persona.estado;
    this.userService.persona.fkRol._id = this.persona.fkRol._id;
    
    // USUARIO DE TIPO ENCUESTADO
    if (this.userService.persona.fkRol._id == 4){

      
    this.userService.persona.fkPersona._id = this.persona.fkPersona._id;
      // this.userService.persona.fkPersona.primerNombre = this.userForm.value.primer_nombre;
      this.userService.persona.fkPersona.primerNombre = this.persona.fkPersona.primerNombre;
      // this.userService.persona.fkPersona.primerApellido = this.userForm.value.primer_apellido;
      this.userService.persona.fkPersona.primerApellido = this.persona.fkPersona.primerApellido;
      // this.userService.persona.fkPersona.documentoIdentidad = this.userForm.value.documento_de_identificacion;
      this.userService.persona.fkPersona.documentoIdentidad = this.persona.fkPersona.documentoIdentidad;
      this.userService.persona.fkPersona.fkGenero._id = this.profileForm.value.genero;
      this.userService.persona.fkPersona.fkEdoCivil._id = this.profileForm.value.estado_civil;
      // this.userService.persona.fkPersona.fechaNacimiento = this.userForm.value.fecha_de_nacimiento;
      this.userService.persona.fkPersona.fechaNacimiento = this.persona.fkPersona.fechaNacimiento;
      this.userService.persona.fkPersona.dispositivos = this.profileForm.value.dispositivos;
  // Informacion socioeconomica
      this.userService.persona.fkPersona.ocupacion = this.profileForm.value.ocupacion;
      this.userService.persona.fkPersona.id_nivel_academico = this.profileForm.value.nivel_academico;
      this.userService.persona.fkPersona.id_nivel_socioeconomico = this.profileForm.value.nivel_socioeconomico;
  // Informacion familiar
      this.userService.persona.fkPersona.numero_personas_encasa = this.profileForm.value.personas_hogar;
      this.userService.persona.fkPersona.tiene_hijos = this.profileForm.value.tiene_hijos;
      this.userService.persona.fkPersona.hijos = this.hijos;
  // Informacion de contacto
      this.userService.persona.fkPersona.codigo_pais = this.profileForm.value.codigo_pais;
      this.userService.persona.fkPersona.telefono = this.profileForm.value.telefono;
  // Informacion de tiempo disponible
      this.userService.persona.fkPersona.id_horario_inicial._id = this.profileForm.value.horario_inicial;
      this.userService.persona.fkPersona.id_horario_final._id = this.profileForm.value.horario_final;
    
      if (this.parroquia && this.profileForm.value.parroquia != 0){
        this.userService.persona.fkPersona.fkLugar._id = this.profileForm.value.parroquia;
      }
      else if (this.ciudad && this.profileForm.value.ciudad != 0){
        this.userService.persona.fkPersona.fkLugar._id = this.profileForm.value.ciudad;
      }
      else if (this.estado && this.profileForm.value.estado != 0){
        this.userService.persona.fkPersona.fkLugar._id = this.profileForm.value.estado;
      }
      else {
        this.userService.persona.fkPersona.fkLugar._id = this.profileForm.value.pais;
      }

      if (this.userService.persona.fkPersona.documentoIdentidad
        && this.userService.persona.fkPersona.fechaNacimiento && this.userService.persona.fkPersona.fkGenero._id
        && this.userService.persona.fkPersona.id_nivel_academico && this.userService.persona.fkRol._id
        && this.userService.persona.fkPersona.id_pais  && this.userService.persona.fkPersona.telefono
        && this.userService.persona.fkPersona.numero_personas_encasa){


        /* SUBMIT FORM */
        this.sent_form = true;
        // if (!this.userForm.valid){
        //   this.sent_form = false;
        //   this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
        // }
        // else {

      // this.persona.fkPersona = this.persona.find(o => o.value == this.persona.fkPersona._id).label;

        this.putUser();
        // }
      }
    }
    // USUARIO DE TIPO NO ENCUESTADO
    else{
      /* SUBMIT FORM */
      this.sent_form = true;
      // if (!this.userForm.valid){
      //   this.sent_form = false;
      //   this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
      // }
      // else {

        // this.persona.fkPersona = this.persona.find(o => o.value == this.persona.fkPersona._id).label;
        
        this.putUser();
      // }
    }
  }

}
