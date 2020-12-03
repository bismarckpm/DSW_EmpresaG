import { Component, OnInit, ViewChild } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { CardModule } from 'primeng/card';
import { Person } from '../classes/person';
import { ProfileService } from '../services/profile.service';
import { GENDERS } from '../constants/gender';
import { CIVIL_STATUSES } from '../constants/civil_status';
import { NgxSpinnerService } from "ngx-spinner";

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  generos: SelectItem[];
  edos_civil: SelectItem[];
  tieneHijos: SelectItem[];
  fecha_nacimiento: Date;
  persona: Person;
  dataPersona: boolean = false;
  loading: boolean = false;
  personErrorMessage: string;
  selectedGenreValue: number;
  selectedEdoCivilValue: number;
  hasKids: boolean;
  es: any;

  /* Form */
  profileForm: FormGroup;
  @ViewChild('pform') profileFormDirective;

  constructor(private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private profileService: ProfileService) {
    this.generos = GENDERS;
    this.edos_civil = CIVIL_STATUSES;
    this.tieneHijos = [
      {
        label: 'Si',
        value: true
      },
      {
        label: 'No',
        value: false
      }];
  }

  ngOnInit(): void {
    this.spinner.show();
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
      dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
      dayNamesMin: ["D", "L", "M", "X", "J", "V", "S"],
      monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
      monthNamesShort: ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"],
      today: 'Hoy',
      clear: 'Borrar'
    }

    this.loading = true;
    this.profileService.getPerson(1).subscribe((p) => {
      this.persona = p;
      this.loading = false;
      this.selectedGenreValue = p.fkPersona.fkGenero.value;
      this.selectedEdoCivilValue = p.fkPersona.fkEdoCivil.value;
      this.fecha_nacimiento = new Date(p.fkPersona.fechaNacimiento);
      this.hasKids = p.fkPersona.tiene_hijos;

      this.createForm();
      this.spinner.hide();
    }, errorMessage => {
      this.loading = false;
      this.spinner.hide();
      this.personErrorMessage = errorMessage;
    })
  }

  createForm() {
    this.profileForm = this.fb.group({
      correo_electronico: [
        this.persona.email,
      ],
    });
  }

}
