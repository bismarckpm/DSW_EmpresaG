import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/api';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';
import { PlaceService } from '../../services/place.service'; 
import { PhoneService } from '../../services/phone.service';

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

  contactErrorMessages: string;
  
  constructor(private router: Router, 
    private registerService: RegisterService, 
    private placeService: PlaceService, 
    private phoneService: PhoneService,
    private fb: FormBuilder) { 
    this.placeService.getCountries().subscribe((countries) => {
      this.paises = countries;
    });

    this.phoneService.getCodes().subscribe((codes) => {
      this.codigos = codes;
    })

    this.createForm();
  }

  ngOnInit(): void {
  }

  createForm(){
    this.contactForm = this.fb.group({
      pais: this.registerService.user.id_pais,
      estado: this.registerService.user.id_estado,
      ciudad: this.registerService.user.id_ciudad,
      parroquia: this.registerService.user.id_parroquia,
      codigo_pais: this.registerService.user.codigo_pais,
      telefono: [
        this.registerService.user.telefono,
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
    this.placeService.getStates(event.value).subscribe((states) => {
      this.estados = states;
    })
  }

  getCities(event){
    this.placeService.getCities(event.value).subscribe((cities) => {
      this.ciudades = cities;
    })
  }

  getCounties(event){
    this.placeService.getCounties(event.value).subscribe((counties) => {
      this.parroquias = counties;
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
    this.registerService.user.id_pais = this.contactForm.value.pais;
    this.registerService.user.id_estado = this.contactForm.value.estado;
    this.registerService.user.id_ciudad = this.contactForm.value.ciudad;
    this.registerService.user.id_parroquia = this.contactForm.value.parroquia;
    this.registerService.user.codigo_pais = this.contactForm.value.codigo_pais;
    this.registerService.user.telefono = this.contactForm.value.telefono;

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
