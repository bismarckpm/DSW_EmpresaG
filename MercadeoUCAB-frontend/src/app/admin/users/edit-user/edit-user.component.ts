import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { replaceDateWithValue, replaceKeyWithValue } from '../../../core/functions/common_functions';
import { NgxSpinnerService } from 'ngx-spinner';
import { Person } from '../../../core/classes/profile/person';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/core/services/admin/user.service';
import { PhoneService } from 'src/app/core/services/profile/phone.service';
import { PlaceService } from 'src/app/core/services/profile/place.service';
import { Child } from 'src/app/core/classes/profile/child';
import { RxwebValidators } from '@rxweb/reactive-form-validators';
import { Device } from '../../../core/constants/device';
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


@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss'],
  providers: [MessageService]
})
export class EditUserComponent implements OnInit {
  constructor(private Activatedroute: ActivatedRoute,
              private router: Router,
              private messageService: MessageService,
              private userService: UserService,
              private rolService: RolService,
              private fb: FormBuilder,
              private spinner: NgxSpinnerService,
              private placeService: PlaceService,
              private phoneService: PhoneService,
              private generoService: GeneroService,
              private edoCivilService: EdocivilService,
              private deviceService: DeviceService,
              private ocupacionService: OcupacionService,
              private disponibilidadService: DisponibilidadService,
              private nivelAcademicoService: NivelAcademicoService,
    ) {

      this.estado_cuenta = ACCOUNT_XTATUS;

      this.ocupacionService.getOcupaciones().subscribe((ocupations) => {
        this.ocupaciones = replaceKeyWithValue(ocupations);
      });

      this.nivelAcademicoService.getNivelesAcademicos().subscribe((niveles) => {
        this.niveles_academicos = replaceKeyWithValue(niveles);
      });

      this.disponibilidadService.getDisponibilidades().subscribe((disponibilidades) => {
        console.log(replaceDateWithValue(disponibilidades));
        this.horario_inicial = replaceDateWithValue(disponibilidades);
        this.horario_final = replaceDateWithValue(disponibilidades);
      });

      this.deviceService.getDevices().subscribe((devices) => {
        this.dispositivos = replaceKeyWithValue(devices);
      });

      this.edoCivilService.getEdosCiviles().subscribe((edos) => {
        this.edos_civil = replaceKeyWithValue(edos);
      });

      this.rolService.getRoles().subscribe((roles) => {
        this.roles = replaceKeyWithValue(roles);
      });

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

      /* If query is empty return 404 */
      if ((this.Activatedroute.snapshot.queryParamMap.get('pid') || 0) === 0){
        this.router.navigate(['404']);
      }

      /* Get current user */
      else {
        this.spinner.show();
        this.loading = true;
        this.current_user = parseInt(this.Activatedroute.snapshot.queryParamMap.get('pid'), 10);
        this.userService.getPerson(this.current_user).subscribe((persona) => {
            this.persona = persona;

            if (this.persona){
              this.loading = false;
              if (this.persona.fkRol._id === 4){
                this.rol = true;
                this.persona.fkPersona.id_pais = new Place();
                this.persona.fkPersona.id_estado = new Place();
                this.persona.fkPersona.id_ciudad = new Place();
                this.persona.fkPersona.id_parroquia = new Place();
                this.manageLugar(persona.fkPersona.fkLugar);
                this.selectedDevices = this.persona.fkPersona.dispositivos;
                this.fecha_nacimiento = new Date(persona.fkPersona.fechaNacimiento);
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
                this.persona.fkPersona.documentoIdentidad = '';
                this.persona.fkPersona.fechaNacimiento = '';
                this.persona.fkPersona.hijos = [];
                this.persona.fkPersona.id_nivel_academico = 0;
                this.persona.fkPersona.numero_personas_encasa = 0;
                this.persona.fkPersona.ocupacion = '';
                this.persona.fkPersona.primerApellido = '';
                this.persona.fkPersona.primerNombre = '';
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
  }

  @Output() onEditUser = new EventEmitter<any>();
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
  ocupaciones: SelectItem[];
  fecha_nacimiento: Date;
  persona: Person;
  showKidsForm = false;
  dataPersona = false;
  loading = false;
  personErrorMessage: string;
  selectedGenreValue: number;
  selectedEdoCivilValue: number;
  selectedStatus: number;
  hasKids: boolean;
  sent_form = false;
  rol: boolean;
  es: any;
  current_user: number;
  estado: boolean;
  ciudad: boolean;
  parroquia: boolean;
  selectedDevices: Device[];

  /* Form */
  userForm: FormGroup;
  @ViewChild('uforms') userFormDirective;

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
  onModalClose: any;

  generochild = new Genero();

  ngOnInit(): void {this.spinner.show();
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

  }


  createForm(){
    this.userForm = this.fb.group({
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
      pais: this.persona.fkPersona.id_pais._id,
      estado: this.persona.fkPersona.id_estado._id,
      ciudad: this.persona.fkPersona.id_ciudad._id,
      parroquia: this.persona.fkPersona.id_parroquia._id,
      ocupacion: this.persona.fkPersona.ocupacion,
      rol: this.persona.fkRol._id,
      telefono: [
        this.persona.fkPersona.telefono,
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

  putUser(){
    this.userService.putUser(this.userService.persona).subscribe((p) => {
      this.persona = p;
      this.messageService.add({severity: 'success', summary: 'Éxito', detail: 'Usuario actualizado con éxito'});
      this.sent_form = false;
      // this.editUser();
      // this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    });
  }

  checkRol(event){
    if (event.value === 1){
      this.rol = true;
    }
    else{
      this.rol = false;
    }
  }


  onSubmit(){
    this.sent_form = true;
    // Informacion basica
    this.userService.persona._id = this.persona._id;
    this.userService.persona.email = this.persona.email;
    this.userService.persona.password = this.userForm.value.clave;
    this.userService.persona.confirmar_clave = this.userForm.value.confirmar_clave;
    this.userService.persona.estado = this.userForm.value.estado_cuenta;
    this.userService.persona.fkRol._id = this.userForm.value.rol;
    console.log('ola? 1');
    // USUARIO DE TIPO ENCUESTADO
    if (this.userService.persona.fkRol._id === 4){

      console.log('ola? 2');
      this.userService.persona.fkPersona._id = this.persona.fkPersona._id;
      this.userService.persona.fkPersona.primerNombre = this.persona.fkPersona.primerNombre;
      this.userService.persona.fkPersona.primerApellido = this.persona.fkPersona.primerApellido;
      this.userService.persona.fkPersona.documentoIdentidad = this.persona.fkPersona.documentoIdentidad;
      this.userService.persona.fkPersona.fkGenero._id = this.userForm.value.genero;
      this.userService.persona.fkPersona.fkEdoCivil._id = this.userForm.value.estado_civil;
      this.userService.persona.fkPersona.fechaNacimiento = this.persona.fkPersona.fechaNacimiento;
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

      if (this.userService.persona.fkPersona.documentoIdentidad
        && this.userService.persona.fkPersona.fechaNacimiento && this.userService.persona.fkPersona.fkGenero._id
        && this.userService.persona.fkPersona.id_nivel_academico && this.userService.persona.fkRol._id
        && this.userService.persona.fkPersona.id_pais  && this.userService.persona.fkPersona.telefono
        && this.userService.persona.fkPersona.numero_personas_encasa){


      console.log('Entro en IF');
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
      console.log('ola?');
      this.putUser();
      // }
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
    this.generochild._id = this.userForm.value.genero_hijo;
    this.generochild.nombre = this.generos[this.userForm.value.genero_hijo-1].label;
    this.hijos.push({
        primerNombre: this.userForm.value.nombre_hijo,
        primerApellido: this.userForm.value.apellido_hijo,
        fkGenero: this.generochild,
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

  showError() {
    this.messageService.add({severity: 'error', summary: 'Error', detail: 'Faltan campos requeridos'});
  }

  previousPage(): void {
    this.router.navigate(['/users']);
  }


  manageLugar(lugar: Place): void{
    // SI ES PAIS NOS DETENEMOS
    if (lugar.tipo === 0){

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
      });
    }
    else{

      switch (lugar.tipo){

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
          });
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
          });
          break;
        case 3:
          this.persona.fkPersona.id_parroquia = lugar;
          this.parroquia = true;
          break;
      }
      this.manageLugar(lugar.fkLugar);
    }
  }
}
