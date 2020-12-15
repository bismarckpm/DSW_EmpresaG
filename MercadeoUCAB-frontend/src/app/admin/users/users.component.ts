import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { Table } from 'primeng/table';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';
import { Person } from '../../core/classes/profile/person';
import { UserService } from '../../core/services/admin/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
  providers: [ConfirmationService, MessageService]

})
export class UsersComponent implements OnInit {

  usuarios: Person[];
  personErrorMessage: string;
  loading = false;
  @ViewChild('dt') table: Table;

  constructor(
    private router: Router,
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private primeNgConfig: PrimeNGConfig) {

     }

  ngOnInit(): void {

    this.loading = true;
    this.userService.getPersons().subscribe((person) => {
      this.usuarios = person;
      console.log(person);
      this.loading = false;
    },
    errorMessage => {
      this.loading = false;
      this.personErrorMessage = errorMessage;
    });
  }



  deleteUser(person){
    this.confirmationService.confirm({
      message: 'El siguiente usuario: <code>' + person.email + '</code> está apunto de ser eliminado, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.userService.deleteUser(person).subscribe((q) => {

            let index = this.usuarios.indexOf(person);
            if (index > -1) {
              this.usuarios.splice(index, 1);
            }

            this.messageService.add({severity: 'success', summary: 'Éxito', detail: 'Usuario eliminado con éxito'});

          }, errorMessage => {
            this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
          });
      },
      reject: () => {
          //
      }
  });
  }

  convertStatus(status){

    if (status == 0) {
      return 'Inactivo';
    }
    else if (status == 1) {
      return 'Activo';
 }
    else {
      return 'N/A';
 }
  }

  showEditUser(userid){
    this.router.navigate(['/users/edit/'], { queryParams: { pid: userid } });
  }

}
