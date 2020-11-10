import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/api';
import { GENDERS } from '../../constants/gender';
import { Child } from '../../classes/child';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';

@Component({
  selector: 'app-family',
  templateUrl: './family.component.html',
  styleUrls: ['./family.component.scss']
})
export class FamilyComponent implements OnInit {
  tieneHijos: SelectItem[];
  generos: SelectItem[];
  hijos: Child[] = [];
  
  /* Form */
  familyForm: FormGroup;
  @ViewChild('fform') familyFormDirective;

  formErrors = {
    'personas_hogar': '',
    'tiene_hijos': '',
    'nombre_hijo': '',
    'apellido_hijo': '',
    'genero_hijo': '',
    'fecha_de_nacimiento_hijo': ''
  }

  validationMessages = {
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
  }

  /* Interactive Form */
  showKidsForm = false;

  es: any;


  constructor(private router: Router, private registerService: RegisterService, private fb: FormBuilder) { 
    this.generos = GENDERS;
    this.tieneHijos = [
    {
      label: 'Si',
      value: true
    },
    {
      label: 'No',
      value: false
    }];

    this.createForm();
  }

  ngOnInit(): void {
    if (this.registerService.user.hijos)
      this.hijos = this.registerService.user.hijos;

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
    this.familyForm = this.fb.group({
      personas_hogar: [
        this.registerService.user.personas_hogar,
        [
          Validators.min(1),
          Validators.pattern('^[0-9]*$')
        ]
      ],
      tiene_hijos: this.registerService.user.tiene_hijos,
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
    });

    this.familyForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });

  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.familyForm){
      return;
    }

    const form = this.familyForm;
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

  hideAddKidForm(){
    this.showKidsForm = false;
  }

  validateAddKidForm(){
    if (this.familyForm.valid){
      this.hijos.push({
        primer_nombre: this.familyForm.value.nombre_hijo,
        primer_apellido: this.familyForm.value.apellido_hijo,
        genero: this.familyForm.value.genero_hijo,
        fecha_de_nacimiento: this.familyForm.value.fecha_de_nacimiento_hijo
    });
    this.hideAddKidForm();
    }
  }

  deleteChild(kid){
    let index = this.hijos.indexOf(kid)
    if (index > -1)
      this.hijos.splice(index, 1)
  }

  onSubmit(){
    this.registerService.user.personas_hogar = this.familyForm.value.personas_hogar;
    this.registerService.user.tiene_hijos = this.familyForm.value.tiene_hijos;
    this.registerService.user.hijos = this.hijos;

    if (this.formErrors.personas_hogar == ''){
      this.nextPage();

    }
  }

  previousPage(): void {
    this.router.navigate(['register/contact'])
  }

  nextPage(): void {
    this.router.navigate(['register/status']);
  }

}
