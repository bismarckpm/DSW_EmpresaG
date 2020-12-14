import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { StudiesService } from '../../../core/services/admin/studies/studies.service';
import { RequestsService } from '../../../core/services/client/requests.service';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { CategoryService } from '../../../core/services/admin/products/category.service';
import { SubcategoryService } from '../../../core/services/admin/products/subcategory.service';
import { MenuItem } from 'primeng/api';
import { STUDY_STATES } from '../../../core/constants/study_states';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { Table } from 'primeng/table';
import { RequestWithFilter } from 'src/app/core/classes/study/request_with_filter';
import { StudyWithFilter } from 'src/app/core/classes/study/study_with_filter';

@Component({
  selector: 'app-select-existing',
  templateUrl: './select-existing.component.html',
  styleUrls: ['./select-existing.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class SelectExistingComponent implements OnInit {
  current_request: number;
  solicitud: RequestWithFilter;
  estudio: StudyWithFilter;
  estudios: StudyWithFilter[];
  request: Request;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  estados: MenuItem[] = STUDY_STATES;

  /* States */
  selected_study = false;
  loading = true;
  studyErrorMessage: string;
  requestErrorMessage: string;

  /* Table */
  @ViewChild('dt') table: Table;

  constructor(private Activatedroute: ActivatedRoute,
              private router: Router,
              private categoryService: CategoryService,
              private subcategoryService: SubcategoryService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService,
              private requestsService: RequestsService,
              private studiesService: StudiesService) {

    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('requestId') || 0) === 0) {
      this.router.navigate(['404']);
    }

    /* Get current study */
    else {

      this.categoryService.getCategories().subscribe((categories) => {
        this.categorias = replaceKeyWithValue(categories);
      }, errorMessage => {
        this.categoriasErrorMessage = errorMessage;
      });

      // tslint:disable-next-line:radix
      this.current_request = parseInt(this.Activatedroute.snapshot.queryParamMap.get('requestId'));

      /* Create study based on request */
      this.requestsService.getRequest(this.current_request).subscribe((request) => {
        this.solicitud = request;

        if (this.solicitud.fkSolicitud.estado === 1) {
          this.router.navigate(['404']);
        }

        else {
          /* Get similar studies */
          this.studiesService.getSimilarStudies(this.solicitud.fkCategoria._id).subscribe((studies) => {
            this.estudios = studies;
            this.loading = false;

          }, errorMessage => {
            this.studyErrorMessage = errorMessage;
          });
        }
      }, errorMessage => {
        this.requestErrorMessage = errorMessage;
        this.router.navigate(['404']);
      });
    }
  }

  // tslint:disable-next-line:typedef
  assignStudy(study: StudyWithFilter) {
    this.selected_study = true;
    this.confirmationService.confirm({
      message: 'El estudio acerca de: <code>' + study.fkCategoria.nombre + '</code> será asignado a la solicitud ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.studiesService.cloneStudy(study.fkEstudio._id, this.current_request).subscribe((s) => {
          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Estudio asignado con éxito' });
          this.router.navigate(['studies/existing']);

        }, errorMessage => {
          this.selected_study = false;
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        });
      },
      reject: () => {
        this.selected_study = false;
      }
    }
    );
  }

  ngOnInit(): void {
  }

  onCategoryChange(event) {
    this.table.filter(event.value, 'fkCategoria._id', 'in');
  }

  onStateChange(event) {
    this.table.filter(event.value, 'fkEstudio.estado', 'in');
  }
}
