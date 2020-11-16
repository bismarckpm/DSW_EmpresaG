import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";
import { StudiesService } from '../../services/studies.service';
import { PlaceService } from '../../services/place.service';
import { Study } from '../../classes/study';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { QuestionService } from '../../services/question.service';
import { CategoryService } from '../../services/category.service';
import { SubcategoryService } from '../../services/subcategory.service';
import { ACADEMICS } from '../../constants/academics';
import { SOCIAL_STATUSES } from '../../constants/social_status';
import { GENDERS } from '../../constants/gender';
import { MenuItem } from 'primeng/api';
import { replaceKeyWithValue } from '../../functions/common_functions';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-study',
  templateUrl: './edit-study.component.html',
  styleUrls: ['./edit-study.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class EditStudyComponent implements OnInit {
  current_study: number;
  estudio: Study;
  display: boolean = false;
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
  subcategoriesErrorMessage: string;

  /* Form */
  studyForm: FormGroup;
  @ViewChild('sform') studyFormDirective;

  constructor(private Activatedroute: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private placeService: PlaceService,
    private categoryService: CategoryService,
    private subcategoryService: SubcategoryService,
    private questionService: QuestionService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private studiesService: StudiesService,
    private spinner: NgxSpinnerService) {
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('sid') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current study */
    else {
      this.spinner.show();
      this.current_study = parseInt(this.Activatedroute.snapshot.queryParamMap.get('sid'));
      this.studiesService.getStudy(this.current_study).subscribe((study) => {
        this.estudio = study;
        if (this.estudio) {
          /* If study is finished it can't be modified */
          if (this.estudio.id_estado == 3) {
            this.router.navigate(['404']);
          }

          console.log(this.estudio)

          subcategoryService.getSubcategories(this.estudio.id_categoria).subscribe((subcategories) => {
            this.subcategorias = replaceKeyWithValue(subcategories);
          })

          this.createForm();
          this.studyForm.patchValue({
            nivel_academico: this.estudio.id_nivel_academico,
            nivel_socioeconomico: this.estudio.id_nivel_socioeconomico
          })

          /* Determine type of location filter */
          if (this.estudio.tipo_filtro_geografico == 'paises') {
            this.studyForm.patchValue({
              pais: this.estudio.id_lugares
            })
          }

          else if (this.estudio.tipo_filtro_geografico == 'estados') {
            this.placeService.getState(this.estudio.id_lugares[0]).subscribe((stateresult) => {
              this.getStates(stateresult.country_id);
              this.studyForm.patchValue({
                pais: stateresult.country_id,
                estado: this.estudio.id_lugares
              })
            })
          }
        }

        else {
          this.router.navigate(['404']);
        }
      });
    }

    placeService.getCountries().subscribe((countries) => {
      this.paises = countries;
    })

    categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKeyWithValue(categories);
    })
  }

  ngOnInit(): void {
  }

  showAddOptions() {
    this.display = true;
  }

  getSubcategories(){
    this.subcategoryService.getSubcategories(this.studyForm.value.categoria).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
    }, errorMessage => {
      this.subcategoriesErrorMessage = errorMessage;
    })
  }

  createForm() {
    this.studyForm = this.fb.group({
      'categoria': this.estudio.id_categoria,
      'subcategoria': this.estudio.id_subcategoria,
      'nivel_academico': null,
      'nivel_socioeconomico': null,
      'genero': this.estudio.id_genero,
      'tipo_de_filtro': this.estudio.tipo_filtro_geografico,
      'pais': null,
      'estado': null,
    });

    this.studyForm.valueChanges
      .subscribe(data => {
        this.studyForm.setValue(data, { emitEvent: false });
      });
  }

  getStates(country_id) {
    this.placeService.getStates(country_id).subscribe((states) => {
      this.estados = states;
    })
  }

  clearPlaces() {
    this.studyForm.patchValue({
      pais: null,
      estado: null,
      ciudad: null
    })
    this.estudio.id_lugares = [];
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


  showOrHideModifyStudyFeatures() {
    this.display_modify_study_features = !this.display_modify_study_features;
    //TODO: clear modify form if false
  }

  onSubmit(){
    this.estudio.id_categoria = this.studyForm.value.categoria
    this.estudio.id_subcategoria = this.studyForm.value.subcategoria
    this.estudio.id_nivel_academico = this.studyForm.value.nivel_academico
    this.estudio.id_nivel_socioeconomico = this.studyForm.value.nivel_socioeconomico
    this.estudio.id_genero = this.studyForm.value.genero 
    this.estudio.tipo_filtro_geografico = this.studyForm.value.tipo_de_filtro

    if (this.estudio.tipo_filtro_geografico == 'paises'){
      this.estudio.id_lugares = this.studyForm.value.pais
    }
    else if (this.estudio.tipo_filtro_geografico == 'estados'){
      this.estudio.id_lugares = this.studyForm.value.estado
    }

    this.studiesService.putStudy(this.estudio).subscribe((study)=>{

    })
  }

}
