import { Component, OnInit } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { ACADEMICS } from '../../constants/academics';
import { SOCIAL_STATUSES } from '../../constants/social_status';
import { DEVICES } from '../../constants/device';
import { SCHEDULES } from '../../constants/schedule';
import { Router } from '@angular/router';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../services/register.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss'],
  providers: [MessageService]
})
export class StatusComponent implements OnInit {
  niveles_academicos: SelectItem[];
  niveles_socioeconomicos: SelectItem[];
  dispositivos: SelectItem[];
  horario_inicial: SelectItem[];
  horario_final: SelectItem[];
  sent_form: boolean = false;

  /* Form */
  statusForm: FormGroup;
  
  constructor(private router: Router, 
    private registerService: RegisterService, 
    private fb: FormBuilder,
    private messageService: MessageService) {
    this.niveles_academicos = ACADEMICS;
    this.niveles_socioeconomicos = SOCIAL_STATUSES;
    this.dispositivos = DEVICES;
    this.horario_inicial = SCHEDULES;
    this.horario_final = SCHEDULES;
    this.createForm();
  }

  ngOnInit(): void {

  }

  createForm(){
    this.statusForm = this.fb.group({
      ocupacion: this.registerService.user.fkPersona.ocupacion,
      nivel_academico: this.registerService.user.fkPersona.id_nivel_academico,
      nivel_socioeconomico: this.registerService.user.fkPersona.id_nivel_socioeconomico,
      dispositivos: this.registerService.user.fkPersona.dispositivos,
      horario_inicial: this.registerService.user.fkPersona.id_horario_inicial,
      horario_final: this.registerService.user.fkPersona.id_horario_final,
    });
  }

  onSubmit(){
    this.sent_form = true;
    this.registerService.user.fkPersona.ocupacion = this.statusForm.value.ocupacion;
    this.registerService.user.fkPersona.id_nivel_academico = this.statusForm.value.nivel_academico;
    this.registerService.user.fkPersona.id_nivel_socioeconomico = this.statusForm.value.nivel_socioeconomico;
    this.registerService.user.fkPersona.dispositivos = this.statusForm.value.dispositivos;
    this.registerService.user.fkPersona.id_horario_inicial = this.statusForm.value.horario_inicial;
    this.registerService.user.fkPersona.id_horario_final = this.statusForm.value.horario_final;

    if (this.registerService.user.email && this.registerService.user.password
      && this.registerService.user.confirmar_clave && this.registerService.user.fkPersona.primerNombre
      && this.registerService.user.fkPersona.primerApellido && this.registerService.user.fkPersona.documentoIdentidad
      && this.registerService.user.fkPersona.fechaNacimiento){

      /* SUBMIT FORM */
      this.registerService.postRegister(this.registerService.user)
        .subscribe(person => {
          console.log("REGISTERED")
        },
        errorMessage => {
          this.sent_form = false;
          this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
        })
    }
    else {
      this.showError()
      this.sent_form = false;
    }
    //console.log(this.registerService.user);
  }

  showError() {
    this.messageService.add({severity:'error', summary: 'Error', detail: 'Faltan campos requeridos'});
  }

  previousPage(): void {
    this.router.navigate(['register/family'])
  }

}
