import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import {CardModule} from 'primeng/card';
import { Person } from '../classes/person';
import { ProfileService } from '../services/profile.service';
import { GENDERS } from '../constants/gender';
import { CIVIL_STATUSES } from '../constants/civil_status';

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

  constructor(
    private profileService: ProfileService) 
    { 
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
    this.loading = true;
    this.profileService.getPerson(1).subscribe((p) => {
      this.persona = p; 
      this.loading = false;
      this.selectedGenreValue = Number.parseInt(p.genero);
      this.selectedEdoCivilValue = Number.parseInt(p.estado_civil);
      this.fecha_nacimiento = new Date(p.fecha_de_nacimiento);
      this.hasKids = p.tiene_hijos;
    
    }, errorMessage => {
      this.loading = false;
      this.personErrorMessage = errorMessage;
    })
  }

}
