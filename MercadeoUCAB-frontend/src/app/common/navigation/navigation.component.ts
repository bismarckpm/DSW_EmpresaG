import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class NavigationComponent implements OnInit {
  items: MenuItem[];
  items_user: MenuItem[];
  items_analyst: MenuItem[];
  items_client: MenuItem[];

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.items = [
      {
        label: 'Usuarios',
        icon: 'pi pi-fw pi-users',
        command: u => this.router.navigate(["users"])
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
            icon: 'pi pi-fw pi-eye',
            command: p => this.router.navigate(["presentations"])
          }
        ]
      },
      {
        label: 'Estudios',
        icon: 'pi pi-fw pi-chart-bar',
        items: [
          {
            label: 'Estudios existentes',
            icon: 'pi pi-fw pi-chart-bar',
            command: e => this.router.navigate(["studies/existing"]) 
          },
          {
            label: 'Preguntas',
            icon: 'pi pi-fw pi-question',
            command: q => this.router.navigate(["questions"]) 
          }
        ],
      },
      {
        label: 'Solicitudes (5)',
        icon: 'pi pi-fw pi-clock',
        command: r => this.router.navigate(["studies/requests"])
      },
      {
        label: 'Perfil',
        icon: 'pi pi-fw pi-user',
        command: p => this.router.navigate(["profile"])
      },
    ];

    
    this.items_user = [
      {
        label: 'Encuestas',
        icon: 'pi pi-fw pi-chart-bar',
        command: s => this.router.navigate(["available-surveys"])
      }
    ];

    this.items_analyst = [
      {
        label: 'Encuestas (5)',
        icon: 'pi pi-fw pi-chart-bar',
        command: s => this.router.navigate(["analysis-requests"])
      }
    ];

    this.items_client = [
      {
        label: 'Solicitudes',
        icon: 'pi pi-fw pi-clock',
        command: s => this.router.navigate(["my-requests"])
      },
      {
        label: 'Solicitar Estudio',
        icon: 'pi pi-fw pi-file',
        command: r => this.router.navigate(["make-request"])
      }
    ];
  }


}
