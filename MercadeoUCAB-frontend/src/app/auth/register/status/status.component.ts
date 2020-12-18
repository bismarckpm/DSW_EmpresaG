import { Component, OnInit } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { ACADEMICS } from '../../../core/constants/academics';
import { SOCIAL_STATUSES } from '../../../core/constants/social_status';
import { DEVICES } from '../../../core/constants/device';
import { SCHEDULES } from '../../../core/constants/schedule';
import { Router } from '@angular/router';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from '../../../core/services/auth/register.service';
import { OcupacionService } from 'src/app/core/services/profile/ocupacion.service';
import { replaceDateWithValue, replaceKeyWithValue } from 'src/app/core/functions/common_functions';
import { NivelAcademicoService } from 'src/app/core/services/profile/nivel-academico.service';
import { DeviceService } from 'src/app/core/services/profile/device.service';
import { GENDERS } from 'src/app/core/constants/gender';
import { DisponibilidadService } from 'src/app/core/services/profile/disponibilidad.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss'],
  providers: [MessageService]
})
export class StatusComponent implements OnInit {
  ocupaciones: SelectItem[];
  niveles_academicos: SelectItem[];
  // niveles_socioeconomicos: SelectItem[];
  dispositivos: SelectItem[];
  horario_inicial: SelectItem[];
  horario_final: SelectItem[];
  sent_form: boolean = false;

  /* Form */
  statusForm: FormGroup;

  constructor(private router: Router,
    private registerService: RegisterService,
    private ocupacionService: OcupacionService,
    private nivelAcademicoService: NivelAcademicoService,
    private deviceService: DeviceService,
    private disponibilidadService: DisponibilidadService,
    private fb: FormBuilder,
    private messageService: MessageService) {

    this.ocupacionService.getOcupaciones().subscribe((ocupations) => {
      this.ocupaciones = replaceKeyWithValue(ocupations);
    });
    this.nivelAcademicoService.getNivelesAcademicos().subscribe((niveles) => {
      this.niveles_academicos = replaceKeyWithValue(niveles);
    });
    this.deviceService.getDevices().subscribe((devices) => {
      this.dispositivos = replaceKeyWithValue(devices);
    });
    this.disponibilidadService.getDisponibilidades().subscribe((disponibilidades) => {
      console.log(replaceDateWithValue(disponibilidades));
      this.horario_inicial = replaceDateWithValue(disponibilidades);
      this.horario_final = replaceDateWithValue(disponibilidades);
    });
    // this.ocupaciones = GENDERS;
    // this.niveles_academicos = ACADEMICS;
    // this.niveles_socioeconomicos = SOCIAL_STATUSES;
    // this.dispositivos = DEVICES;
    // this.horario_inicial = SCHEDULES;
    // this.horario_final = SCHEDULES;
    this.createForm();
  }

  ngOnInit(): void {

  }

  createForm(){
    this.statusForm = this.fb.group({
      ocupacion: this.registerService.user.fkPersona.ocupacion,
      nivel_academico: this.registerService.user.fkPersona.id_nivel_academico,
      // nivel_socioeconomico: this.registerService.user.fkPersona.id_nivel_socioeconomico,
      dispositivos: this.registerService.user.fkPersona.dispositivos,
      horario_inicial: this.registerService.user.fkPersona.id_horario_inicial,
      horario_final: this.registerService.user.fkPersona.id_horario_final,
    });
  }

  onSubmit(){
    this.sent_form = true;
    this.registerService.user.fkPersona.ocupacion = this.statusForm.value.ocupacion;
    this.registerService.user.fkPersona.id_nivel_academico = this.statusForm.value.nivel_academico;
    // this.registerService.user.fkPersona.id_nivel_socioeconomico = this.statusForm.value.nivel_socioeconomico;
    this.registerService.user.fkPersona.dispositivos = this.statusForm.value.dispositivos;
    this.registerService.user.fkPersona.id_horario_inicial._id = this.statusForm.value.horario_inicial;
    this.registerService.user.fkPersona.id_horario_final._id = this.statusForm.value.horario_final;
    this.registerService.user.estado = 1;

    if (this.registerService.user.email && this.registerService.user.password
      && this.registerService.user.confirmar_clave && this.registerService.user.fkPersona.primerNombre
      && this.registerService.user.fkPersona.primerApellido && this.registerService.user.fkPersona.documentoIdentidad
      && this.registerService.user.fkPersona.fechaNacimiento){

      /* SUBMIT FORM */
      this.registerService.postRegister(this.registerService.user)
        .subscribe(person => {
          console.log(person);
          // console.log("Bien");
          this.router.navigate(['/login']);
          this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Registro completado satisfactoriamente.'});
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
