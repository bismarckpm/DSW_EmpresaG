import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/api';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../../core/services/auth/register.service';
import { PlaceService } from '../../../core/services/profile/place.service';
// import { PhoneService } from '../../../core/services/profile/phone.service';
import { replaceKeyWithValue } from 'src/app/core/functions/common_functions';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  paises: SelectItem[];
  estados: SelectItem[];
  ciudades: SelectItem[];
  parroquias: SelectItem[];
  codigos: SelectItem[];

  estado: boolean;
  ciudad: boolean;
  parroquia: boolean;

  /* Form */
  contactForm: FormGroup;
  @ViewChild('cform') contactFormDirective;

  formErrors = {
    'telefono': ''
  };

  validationMessages = {
    'telefono': {
      'pattern': 'Teléfono debe ser un campo numérico'
    }
  }

  constructor(private router: Router,
    private registerService: RegisterService,
    private placeService: PlaceService,
    // private phoneService: PhoneService,
    private fb: FormBuilder) {

    this.estado = true;
    this.ciudad = true;
    this.parroquia = true;

    this.placeService.getCountries().subscribe((countries) => {
      this.paises = replaceKeyWithValue(countries);
    });

    // this.phoneService.getCodes().subscribe((codes) => {
    //   this.codigos = codes;
    // })

    this.createForm();
  }

  ngOnInit(): void {
  }

  createForm(){
    this.contactForm = this.fb.group({
      pais: this.registerService.user.fkPersona.id_pais._id,
      estado: this.registerService.user.fkPersona.id_estado._id,
      ciudad: this.registerService.user.fkPersona.id_ciudad._id,
      parroquia: this.registerService.user.fkPersona.id_parroquia._id,
      // codigo_pais: this.registerService.user.fkPersona.codigo_pais,
      telefono: [
        this.registerService.user.fkPersona.telefono.numero,
        [
          Validators.pattern('^[0-9]*$')
        ]
      ],
    });

    this.contactForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
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

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.contactForm){
      return;
    }

    const form = this.contactForm;
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
    this.registerService.user.fkPersona.id_pais._id = this.contactForm.value.pais;
    this.registerService.user.fkPersona.id_estado._id = this.contactForm.value.estado;
    this.registerService.user.fkPersona.id_ciudad._id = this.contactForm.value.ciudad;
    this.registerService.user.fkPersona.id_parroquia._id = this.contactForm.value.parroquia;

    if (this.parroquia && this.registerService.user.fkPersona.id_parroquia._id != 0){
      this.registerService.user.fkPersona.fkLugar._id = this.contactForm.value.parroquia;
    }
    else if (this.ciudad && this.registerService.user.fkPersona.id_ciudad._id != 0){
      this.registerService.user.fkPersona.fkLugar._id = this.contactForm.value.ciudad;
    }
    else if (this.estado && this.registerService.user.fkPersona.id_estado._id != 0){
      this.registerService.user.fkPersona.fkLugar._id = this.contactForm.value.estado;
    }
    else {
      this.registerService.user.fkPersona.fkLugar._id = this.contactForm.value.pais;
    }
    

    // this.registerService.user.fkPersona.codigo_pais = this.contactForm.value.codigo_pais;
    this.registerService.user.fkPersona.telefono = this.contactForm.value.telefono;

    if (this.contactForm.valid)
      this.nextPage();
  }

  previousPage(): void {
    this.router.navigate(['register'])
  }

  nextPage(): void {
    this.router.navigate(['register/family']);
  }

}
