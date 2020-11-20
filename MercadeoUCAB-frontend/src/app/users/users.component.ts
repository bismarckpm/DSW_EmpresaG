import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { Table } from 'primeng/table';
import { MenuItem, MessageService, PrimeNGConfig } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';
import { Person } from '../classes/person';
import { replaceKey } from '../functions/common_functions';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
  providers: [ConfirmationService, MessageService]

})
export class UsersComponent implements OnInit {

  usuarios: Person[];
  personErrorMessage: string;
  loading: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private primeNgConfig: PrimeNGConfig) {

     }

  ngOnInit(): void {

    this.loading = true;
    this.userService.getPerson().subscribe((person) => {
    this.usuarios = person; 
    this.loading = false;
    },
    errorMessage => {
      this.loading = false;
      this.personErrorMessage = errorMessage;
    })
  }

  // El doc de identificacion no sirvio
  deleteUser(usuarios){
    this.confirmationService.confirm({
      message: 'El siguiente usuario: <code>' + usuarios.documento_de_identificacion + '</code> está apunto de ser eliminado, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.userService.deleteUser(usuarios).subscribe((q) => {

            let index = this.usuarios.indexOf(usuarios)
            if (index > -1)
              this.usuarios.splice(index, 1);

            this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Usuario eliminado con éxito'});
            
          }, errorMessage => {
            this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
          })
      },
      reject: () => {
          //
      }
  });

  }

}
