import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { replaceKeyWithValue } from '../../functions/common_functions';
import { Option } from '../../classes/options';
import { NgxSpinnerService } from "ngx-spinner";
import { Person } from '../../classes/person';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { PhoneService } from 'src/app/services/phone.service';
import { PlaceService } from 'src/app/services/place.service';


@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent implements OnInit {

  usuario: Person;
  userErrorMessage: string;
  sent_form: boolean = false;
  loading: boolean = true;
  current_user: number;

  /* Form */
  userForm: FormGroup;
  @ViewChild('uforms')

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

      /* If query is empty return 404 */
      if ((this.Activatedroute.snapshot.queryParamMap.get('qid')||0) == 0){
        this.router.navigate(['404']);
      }

      /* Get current user */
      // ------------------------------ ARREGLANDO ESTO ----------------------------------------------------------
    //   else {
    //     this.spinner.show();
    //     this.current_user = parseInt(this.Activatedroute.snapshot.queryParamMap.get('qid'));
    //     this.userService.getPerson(this.current_user).subscribe((person) => {
    //       this.usuario = person;
    //       if (this.usuario){
    //         this.createForm();
    //       }
    //     else {
    //       this.router.navigate(['404']);
    //     }
    //   });
    // }
  
  // ngOnInit(): void {
  //   this.categoryService.getCategories().subscribe((categories) => {
  //     this.loading = false;
  //     this.categorias = replaceKeyWithValue(categories);
  //     this.spinner.hide();
  //   }, errorMessage => {
  //     this.loading = false;
  //     this.categoriesErrorMessage = errorMessage;
  //     this.spinner.hide();
  //   })
}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}