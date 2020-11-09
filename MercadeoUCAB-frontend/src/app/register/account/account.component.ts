import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { GENDERS } from '../../constants/gender';
import { CIVIL_STATUSES } from '../../constants/civil_status';
import { Router } from '@angular/router'

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  generos: SelectItem[];
  estados_civiles: SelectItem[];

  /* Form */
  accountForm: FormGroup;

  constructor(private router: Router,
    private fb: FormBuilder,
    private registerService: RegisterService) { 
    this.generos = GENDERS;
    this.estados_civiles = CIVIL_STATUSES;
    this.createForm();
  }

  ngOnInit(): void {

  }

  createForm(){
    this.accountForm = this.fb.group({
      correo_electronico: this.registerService.user.correo_electronico,
      contraseña: this.registerService.user.contraseña,
      confirmar_contraseña: this.registerService.user.confirmar_contraseña,
      primer_nombre: this.registerService.user.primer_nombre,
      primer_apellido: this.registerService.user.primer_apellido,
      documento_de_identificacion: this.registerService.user.documento_de_identificacion,
      genero: this.registerService.user.genero,
      estado_civil: this.registerService.user.estado_civil,
      fecha_de_nacimiento: this.registerService.user.fecha_de_nacimiento
    });
  }

  onSubmit(){
    this.registerService.user.correo_electronico = this.accountForm.value.correo_electronico;
    this.registerService.user.contraseña = this.accountForm.value.contraseña;
    this.registerService.user.confirmar_contraseña = this.accountForm.value.confirmar_contraseña;
    this.registerService.user.primer_nombre = this.accountForm.value.primer_nombre;
    this.registerService.user.primer_apellido = this.accountForm.value.primer_apellido;
    this.registerService.user.documento_de_identificacion = this.accountForm.value.documento_de_identificacion;
    this.registerService.user.genero = this.accountForm.value.genero;
    this.registerService.user.estado_civil = this.accountForm.value.estado_civil;
    this.registerService.user.fecha_de_nacimiento = this.accountForm.value.fecha_de_nacimiento;

    this.nextPage();
  }

  nextPage(): void {
    this.router.navigate(['register/contact']);
  }

}
