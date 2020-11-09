import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItem } from 'primeng/api';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {
  items: MenuItem[];

  constructor() { 

  }

  ngOnInit(): void {
    this.items = [
      {
        label: 'Cuenta',
      },
      {
        label: 'Contacto'
      },
      {
        label: 'Familia'
      },
      {
        label: 'Estatus'
      },
      {
        label: 'Disponibilidad'
      }
    ];
  }

}
