import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SelectItem } from 'primeng/api';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';

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

  /* Form */
  contactForm: FormGroup;
  
  constructor(private router: Router, private registerService: RegisterService, private fb: FormBuilder) { 
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
      telefono: this.registerService.user.telefono,
    });
  }

  onSubmit(){
    this.registerService.user.id_pais = this.contactForm.value.pais;
    this.registerService.user.id_estado = this.contactForm.value.estado;
    this.registerService.user.id_ciudad = this.contactForm.value.ciudad;
    this.registerService.user.id_parroquia = this.contactForm.value.parroquia;
    this.registerService.user.codigo_pais = this.contactForm.value.codigo_pais;
    this.registerService.user.telefono = this.contactForm.value.telefono;
    
    this.nextPage();
  }

  previousPage(): void {
    this.router.navigate(['register'])
  }

  nextPage(): void {
    this.router.navigate(['register/family']);
  }

}
