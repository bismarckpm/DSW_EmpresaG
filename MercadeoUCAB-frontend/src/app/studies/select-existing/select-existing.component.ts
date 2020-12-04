import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { StudiesService } from '../../services/studies.service';
import { RequestsService } from '../../services/requests.service';
import { Study } from '../../classes/study';
import { Request } from '../../classes/request';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { CategoryService } from '../../services/category.service';
import { SubcategoryService } from '../../services/subcategory.service';
import { MenuItem } from 'primeng/api';
import { STUDY_STATES } from '../../constants/study_states';
import { replaceKey } from '../../functions/common_functions';
import { Table } from 'primeng/table';

@Component({
  selector: 'app-select-existing',
  templateUrl: './select-existing.component.html',
  styleUrls: ['./select-existing.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class SelectExistingComponent implements OnInit {
  current_study: number;
  estudios: Study[];
  estudio_generado: Study;
  request: Request;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  estados: MenuItem[] = STUDY_STATES;

  /* States */
  selected_study = false;
  loading: boolean = true;
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
    if ((this.Activatedroute.snapshot.queryParamMap.get('requestId') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current study */
    else {
      this.current_study = parseInt(this.Activatedroute.snapshot.queryParamMap.get('requestId'));

      this.requestsService.getRequest(this.current_study).subscribe((request) => {
        /* In progress studies cannot be recreated */
        this.request = request;
        if (this.request.estado == "En progreso") {
          this.router.navigate(['404']);
        }
        else {
          /* Get studies table */
          //TODO: Change this request to get similar studies ONLY
          this.studiesService.getStudies().subscribe((studies) => {
            this.loading = false;
            //this.estudios = studies;
          }, errorMessage => {
            this.loading = false;
            this.studyErrorMessage = errorMessage;
          })

          this.categoryService.getCategories().subscribe((categories) => {
            this.categorias = replaceKey(categories);
            this.loading = false;
          }, errorMessage => {
            this.loading = false;
            this.categoriasErrorMessage = errorMessage;
          })

        }

      }, errorMessage => {
        this.requestErrorMessage = errorMessage;
        this.router.navigate(['404']);
      })
    }
  }

  assignStudy(study) {
    this.selected_study = true;
    console.log(study)
    this.confirmationService.confirm({
      message: 'El estudio acerca de: <code>' + study.categoria + '</code> será asignado a la solicitud ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.request.estado = "En progreso";
        /* Update request's status before turning it into a study 
        this.requestsService.putRequest(this.request).subscribe((request) => {
          this.estudio_generado = this.request as Study;
          this.estudio_generado.preguntas = study.preguntas;
          console.log(this.estudio_generado)
          delete this.estudio_generado['id'];

          this.studiesService.postStudy(this.estudio_generado).subscribe((s) => {
            this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Estudio asignado con éxito' });
            //this.router.navigate(['studies/existing'])

          }, errorMessage => {
            this.selected_study = false;
            this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
          })
        }, errorMessage => {
          this.selected_study = false;
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        })*/
      },
      reject: () => {
        //
      }
    }
    )
  };

  ngOnInit(): void {
  }

  onCategoryChange(event) {
    this.table.filter(event.value, 'categoria', 'in')
  }

  onStateChange(event) {
    this.table.filter(event.value, 'estado', 'in')
  }
}
