import { Component, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { CategoryService } from '../../core/services/admin/products/category.service';
import { RequestsService } from '../../core/services/client/requests.service';
import { replaceKeyWithValue } from '../../core/functions/common_functions';
import { REQUEST_STATES } from 'src/app/core/constants/request_status';
import { RequestWithFilter } from 'src/app/core/classes/study/request_with_filter';

//TODO: Filter by current user
@Component({
  selector: 'app-my-requests',
  templateUrl: './my-requests.component.html',
  styleUrls: ['./my-requests.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class MyRequestsComponent implements OnInit {
  // TODO: Get logged user
  current_user: number = 94;
  current_state: number;
  solicitudes: RequestWithFilter[];
  solicitudes_backup: RequestWithFilter[];
  solicitudesErrorMessage: string;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  request_states = REQUEST_STATES;
  show_stats: boolean = false;
  loading: boolean = false;

  @ViewChild('dt') table: Table;
  constructor(private requestsService: RequestsService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    this.getRequests();

    this.categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKeyWithValue(categories);
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.categoriasErrorMessage = errorMessage;
    })
  }

  onCategoryChange(event) {
    this.table.filter(event.value, 'fkCategoria._id', 'in')
  }

  getRequests(){
    this.requestsService.getUserRequests(this.current_user).subscribe((requests) => {
      this.loading = false;
      this.solicitudes = requests;
      this.solicitudes_backup = requests;
      this.solicitudes = this.solicitudes.filter(req => req.fkSolicitud.estado == 0)
    }, errorMessage => {
      this.loading = false;
      this.solicitudesErrorMessage = errorMessage;
    })
  }

  cancelRequest(study: RequestWithFilter){
    this.confirmationService.confirm({
      message: 'La solicitud para el estudio de categoría: <code>' + study.fkCategoria.nombre + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.requestsService.deleteRequest(study.fkSolicitud).subscribe((s) => {

            let index = this.solicitudes.indexOf(study)
            if (index > -1)
              this.solicitudes.splice(index, 1);

            this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Solicitud eliminada con éxito'});

          }, errorMessage => {
            this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
          })
      },
      reject: () => {
        //
      }
    });
  }

  filterStudies(event){
    this.loading = true;
    this.solicitudes = this.solicitudes_backup;
    if (event.value == 1){
      this.solicitudes = this.solicitudes.filter(req => req.fkSolicitud.estado == 0)
    }
    else if (event.value == 2){
      this.solicitudes = this.solicitudes.filter(req => req.fkSolicitud.estado == 1 && req.fkEstudio.estado == 1)
    }
    else if (event.value == 3){
      this.solicitudes = this.solicitudes.filter(req => req.fkSolicitud.estado == 1 && req.fkEstudio.estado == 2)
    }
    this.loading = false;
  }

}
