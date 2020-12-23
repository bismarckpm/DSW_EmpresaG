import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit {
  items: MenuItem[];

  constructor() {}

  ngOnInit(): void {
    this.items = [
      {
        label: 'Cuenta',
        routerLink: 'personal'
      },
      {
        label: 'Contacto',
        routerLink: 'contact'
      },
      {
        label: 'Familia',
        routerLink: 'family'
      },
      {
        label: 'Estatus',
        routerLink: 'status'
      }
    ];
  }

}
