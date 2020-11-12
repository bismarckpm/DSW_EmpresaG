import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api'

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  items: MenuItem[];
  constructor() { }

  ngOnInit(): void {
    this.items = [
      {
        label: 'Perfil', 
        icon: 'pi pi-fw pi-user'
      },
      {
        label: 'Estudios existentes', 
        icon: 'pi pi-fw pi-chart-bar'
      },
      {
        label: 'Solicitudes (5)', 
        icon: 'pi pi-fw pi-clock'
      },
      {
        label: 'Preguntas', 
        icon: 'pi pi-fw pi-question-circle'
      },
      {
        label: 'Usuarios', 
        icon: 'pi pi-fw pi-users'
      },
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
  ];
  }

}
