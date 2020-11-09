import { Component, OnInit } from '@angular/core';
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

  /* Interactive Form */
  showKidsForm = false;

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
  }

  createForm(){
    this.familyForm = this.fb.group({
      personas_hogar: this.registerService.user.personas_hogar,
      tiene_hijos: this.registerService.user.tiene_hijos,
      nombre_hijo: '',
      apellido_hijo: '',
      genero_hijo: null,
      fecha_de_nacimiento_hijo: null,
    });
  }

  showAddKidForm(){
    this.showKidsForm = true;
  }

  hideAddKidForm(){
    this.showKidsForm = false;
  }

  validateAddKidForm(){
    // TODO: Append kid to kid's array
    /*this.hijos.push({
      primer_nombre: 'xd',
      primer_apellido: 'xd',
      genero: 'xd',
      fecha_de_nacimiento: 'xd'
    })*/
    this.hideAddKidForm();
  }

  onSubmit(){
    this.registerService.user.personas_hogar = this.familyForm.value.personas_hogar;
    this.registerService.user.tiene_hijos = this.familyForm.value.tiene_hijos;
    this.registerService.user.hijos = this.hijos;

    //console.log(this.registerService.user);

    this.nextPage();
  }

  previousPage(): void {
    this.router.navigate(['register/contact'])
  }

  nextPage(): void {
    this.router.navigate(['register/status']);
  }

}
