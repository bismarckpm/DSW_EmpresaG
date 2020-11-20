import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";
import { StudiesService } from '../../services/studies.service';
import { RequestsService } from '../../services/requests.service';
import { PlaceService } from '../../services/place.service';
import { Study } from '../../classes/study';
import { Question } from '../../classes/question';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { QuestionService } from '../../services/question.service';
import { CategoryService } from '../../services/category.service';
import { SubcategoryService } from '../../services/subcategory.service';
import { ACADEMICS } from '../../constants/academics';
import { SOCIAL_STATUSES } from '../../constants/social_status';
import { GENDERS } from '../../constants/gender';
import { MenuItem } from 'primeng/api';

/* Form */
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-create-study',
  templateUrl: './create-study.component.html',
  styleUrls: ['./create-study.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class CreateStudyComponent implements OnInit {
  current_study: number;
  estudio: Study;
  display: boolean = false;
  has_to_clear_questions: boolean = false // If category changes questions are no longer valid
  display_modify_study_features: boolean = false;
  niveles_academicos: MenuItem[] = ACADEMICS;
  generos: MenuItem[] = GENDERS;
  niveles_socioeconomicos: MenuItem[] = SOCIAL_STATUSES;
  filtro_pais: boolean = false;
  filtro_estado: boolean = false;
  filtro_ciudad: boolean = false;
  paises: MenuItem[];
  estados: MenuItem[];
  categorias: MenuItem[];
  subcategorias: MenuItem[];
  temporal_state_id: number;

  /* Style of question adding */
  style: string;
  display_new: boolean = false;
  display_pool: boolean = false;

  /* Object for question appending */
  new_question: Question;

  /* States */
  loading: boolean = false;
  studyErrorMessage: string;
  requestErrorMessage: string;

  constructor(private Activatedroute: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private placeService: PlaceService,
    private categoryService: CategoryService,
    private subcategoryService: SubcategoryService,
    private questionService: QuestionService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private requestsService: RequestsService,
    private studiesService: StudiesService,
    private spinner: NgxSpinnerService) {
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('requestId') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current study */
    else {
      this.loading = true;
      this.spinner.show();

      this.current_study = parseInt(this.Activatedroute.snapshot.queryParamMap.get('requestId'));

      /* Create study based on request */
      this.requestsService.getRequest(this.current_study).subscribe((request) => {
        /* In progress studies cannot be recreated */
        this.estudio = request;
        if (this.estudio.estado == "En progreso"){
          this.router.navigate(['404']);
        }
        else {
          this.estudio.estado = "En progreso";
          /* Update request's status before turning it into a study */
          this.requestsService.putRequest(this.estudio).subscribe((request) => {
            delete this.estudio['id'];
            this.studiesService.postStudy(this.estudio).subscribe((study) => {
              this.estudio = study;
              this.loading = false;
              this.spinner.hide();
            }, errorMessage => {
              this.loading = false;
              this.spinner.hide();
              this.studyErrorMessage = errorMessage;
            })
          }, errorMessage => {
            this.requestErrorMessage = errorMessage
          })
        }

      }, errorMessage => {
        this.requestErrorMessage = errorMessage;
        this.router.navigate(['404']);
      })
    }
  }

  ngOnInit(): void {
  }

  showAddOptions() {
    this.display = true;
  }

  deleteQuestion(question) {
    this.confirmationService.confirm({
      message: 'La siguiente pregunta: <code>' + question.pregunta + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.questionService.deleteQuestion(question).subscribe((q) => {

          let index = this.estudio.preguntas.indexOf(question)
          if (index > -1)
            this.estudio.preguntas.splice(index, 1);

          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Pregunta eliminada con éxito' });

        }, errorMessage => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        })
      },
      reject: () => {
        //
      }
    });
  }

  addQuestion() {
    if (this.style == 'new') {
      this.display_new = true;
      this.display_pool = false;
    }
    else if (this.style == 'pool') {
      this.display_pool = true;
      this.display_new = false;
    }
    this.display = false;
  }

  putStudy() {
    this.studiesService.putStudy(this.estudio).subscribe((study) => {
      this.display_pool = false;
      this.display_modify_study_features = false;
      this.display_new = false;
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Pregunta añadida con éxito' });
    }, errorMessage => {
      this.display_modify_study_features = false;
      this.display_pool = false;
      this.display_new = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    })
  }

  getSelectedQuestion(question) {
    if (!this.estudio.preguntas) {
      this.estudio.preguntas = [];
      this.estudio.preguntas.push(question);
    }

    else
      this.estudio.preguntas.push(question);

    this.putStudy();
  }
}
