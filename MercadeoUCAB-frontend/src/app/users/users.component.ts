import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { Table } from 'primeng/table';
import { MenuItem, MessageService, PrimeNGConfig } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';
import { Person } from '../classes/person';
import { UserService } from '../services/user.service';
import { replaceKey } from '../functions/common_functions';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProcessHttpMessageService } from '../services/process-http-message.service';




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
    this.userService.getPersons().subscribe((person) => {
    this.usuarios = person; 
    this.loading = false;
    },
    errorMessage => {
      this.loading = false;
      this.personErrorMessage = errorMessage;
    })
  }

  deleteUser(person){
    this.confirmationService.confirm({
      message: 'El siguiente usuario: <code>' + person.correo_electronico + '</code> está apunto de ser eliminado, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.userService.deleteUser(person).subscribe((q) => {

            let index = this.usuarios.indexOf(person)
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
