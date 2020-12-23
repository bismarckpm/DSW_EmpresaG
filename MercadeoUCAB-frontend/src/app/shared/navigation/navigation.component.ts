import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { SessionService } from '../../core/services/auth/session.service';
import { LoginService } from '../../core/services/auth/login.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss'],
  providers: [MessageService],
  encapsulation: ViewEncapsulation.None
})
export class NavigationComponent implements OnInit {
  items: MenuItem[];
  items_admin: MenuItem[];
  items_user: MenuItem[];
  items_analyst: MenuItem[];
  items_client: MenuItem[];
  token: string;

  constructor(private router: Router,
              private sessionService: SessionService,
              private loginService: LoginService,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.items_admin = [
      {
        label: 'Usuarios',
        icon: 'pi pi-fw pi-users',
        command: u => this.router.navigate(['admin/users'])
      },
      {
        label: 'Productos',
        icon: 'pi pi-fw pi-briefcase',
        items: [
          {
            label: 'Categorías',
            icon: 'pi pi-fw pi-list',
            command: c => this.router.navigate(['admin/categories'])
          },
          {
            label: 'Subcategorías',
            icon: 'pi pi-fw pi-sort-amount-down',
            command: s => this.router.navigate(['admin/subcategories'])
          },
          {
            label: 'Tipos',
            icon: 'pi pi-fw pi-briefcase',
            command: t => this.router.navigate(['admin/types'])
          },
          {
            label: 'Marcas',
            icon: 'pi pi-fw pi-microsoft',
            command: b => this.router.navigate(['admin/brands'])
          },
          {
            label: 'Presentaciones',
            icon: 'pi pi-fw pi-eye',
            command: p => this.router.navigate(['admin/presentations'])
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
            command: e => this.router.navigate(['admin/studies/existing'])
          },
          {
            label: 'Preguntas',
            icon: 'pi pi-fw pi-question',
            command: q => this.router.navigate(['admin/questions'])
          }
        ],
      },
      {
        label: 'Solicitudes',
        icon: 'pi pi-fw pi-clock',
        command: r => this.router.navigate(['admin/studies/requests'])
      },
      {
        label: 'Perfil',
        icon: 'pi pi-fw pi-user',
        command: p => this.router.navigate(['profile'])
      },
    ];


    this.items_user = [
      {
        label: 'Encuestas',
        icon: 'pi pi-fw pi-chart-bar',
        command: s => this.router.navigate(['surveys/available'])
      }
    ];

    this.items_analyst = [
      {
        label: 'Encuestas',
        icon: 'pi pi-fw pi-chart-bar',
        command: s => this.router.navigate(['analytics/requests'])
      }
    ];

    this.items_client = [
      {
        label: 'Solicitudes',
        icon: 'pi pi-fw pi-clock',
        command: s => this.router.navigate(['client/my-requests'])
      },
      {
        label: 'Solicitar Estudio',
        icon: 'pi pi-fw pi-file',
        command: r => this.router.navigate(['client/make-request'])
      }
    ];

    if (this.sessionService.isAdmin()){
      this.items = this.items_admin;
    }
    else if (this.sessionService.isAnalyst()){
      this.items = this.items_analyst;
    }
    else if (this.sessionService.isClient()){
      this.items = this.items_client;
    }
    else if (this.sessionService.isPolled()){
      this.items = this.items_user;
    }
  }

  logout(): void {
    this.token = this.sessionService.getCurrentToken();

    this.loginService.logout(this.token)
      .subscribe(person => {
        this.sessionService.logout();
      },
      errorMessage => {
        this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
      });
  }
}
