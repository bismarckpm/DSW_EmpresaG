import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  items: MenuItem[];
  display: boolean = false;

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
        label: 'FrankHesse',
        icon: 'pi pi-fw pi-user',
        items: [
          {
            label: 'Perfil',
            icon: 'pi pi-fw pi-user'
          },
          {
            label: 'Salir',
            icon: 'pi pi-fw pi-power-off'
          }
        ]
      }
    ];
  }


}
