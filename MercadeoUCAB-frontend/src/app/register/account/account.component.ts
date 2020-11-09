import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';

interface Genero{
  id: number;
  name: string;
}

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  generos: SelectItem[];
  estados_civiles: SelectItem[];

  constructor() { 
    this.generos = [{
      label: 'Género',
      value: null
    }, 
    {
      label: 'Masculino',
      value: 1
    },
    {
      label: 'Femenino',
      value: 2
    },
    {
      label: 'Otro',
      value: 3
    }];

    this.estados_civiles = [{
      label: 'Estado Civil',
      value: null
    },
    {
      label: 'Soltero',
      value: 1
    },
    {
      label: 'Casado',
      value: 2,
    },
    {
      label: 'Divorciado',
      value: 3
    },
    {
      label: 'Unión Libre o Concubinato',
      value: 4
    },
    {
      label: 'Separado',
      value: 5
    },
    {
      label: 'Viudo',
      value: 6
    }]
  }

  ngOnInit(): void {
  }

}
