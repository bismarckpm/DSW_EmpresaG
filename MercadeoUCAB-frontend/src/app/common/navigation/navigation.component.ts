import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class NavigationComponent implements OnInit {
  items: MenuItem[];
  items_guest: MenuItem[];

  constructor() { }

  ngOnInit(): void {
    this.items = [
      {
        label: 'Usuarios',
        icon: 'pi pi-fw pi-users'
      },
      {
        label: 'Productos',
        icon: 'pi pi-fw pi-briefcase',
        items: [
          {
            label: 'Categorías',
            icon: 'pi pi-fw pi-list'
          },
          {
            label: 'Subcategorías',
            icon: 'pi pi-fw pi-sort-amount-down'
          },
          {
            label: 'Tipos',
            icon: 'pi pi-fw pi-briefcase'
          },
          {
            label: 'Marcas',
            icon: 'pi pi-fw pi-microsoft'
          },
          {
            label: 'Presentaciones',
            icon: 'pi pi-fw pi-eye'
          }
        ]
      },
      {
        label: 'Estudios',
        icon: 'pi pi-fw pi-chart-bar'
      },
      {
        label: 'Solicitudes (5)',
        icon: 'pi pi-fw pi-clock'
      },
      {
        label: 'Perfil',
        icon: 'pi pi-fw pi-user',
      },
    ];

    
    this.items_guest = [
      {
        label: 'Login',
        icon: 'pi pi-fw pi-user'
      },
     
      {
        label: 'Registro',
        icon: 'pi pi-fw pi-user-plus'
      },
    ];

  }


}
