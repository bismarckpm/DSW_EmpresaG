import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { StudiesService } from '../../../core/services/admin/studies/studies.service';
import { PlaceService } from '../../../core/services/profile/place.service';
import { Question } from '../../../core/classes/study/question';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { QuestionService } from '../../../core/services/admin/studies/question.service';
import { CategoryService } from '../../../core/services/admin/products/category.service';
import { SubcategoryService } from '../../../core/services/admin/products/subcategory.service';
import { SOCIAL_STATUSES } from '../../../core/constants/social_status';
import { MenuItem } from 'primeng/api';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { StudyWithFilter } from 'src/app/core/classes/study/study_with_filter';
import { QuestionCategorySubcategory } from 'src/app/core/classes/study/question_category_subcategory';
import { NivelAcademicoService } from 'src/app/core/services/profile/nivel-academico.service';
import { GeneroService } from 'src/app/core/services/profile/genero.service';
import { EdocivilService } from 'src/app/core/services/profile/edocivil.service';
import { Gender } from 'src/app/core/classes/profile/gender';
import { Place } from 'src/app/core/classes/profile/place';
import { SocioEconomicStatus } from 'src/app/core/classes/profile/socioeconomic_status';
import { AcademicLevel } from '../../../core/classes/profile/academic_level';
import { Table } from 'primeng/table';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators';
import { CivilStatus } from 'src/app/core/classes/profile/civil_status';

@Component({
  selector: 'app-edit-study',
  templateUrl: './edit-study.component.html',
  styleUrls: ['./edit-study.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class EditStudyComponent implements OnInit {
  estudio: StudyWithFilter;
  preguntas: QuestionCategorySubcategory[];
  categorias: MenuItem[];
  subcategorias: any[];
  generos: MenuItem[];
  niveles_academicos: MenuItem[];
  estados_civiles: MenuItem[];
  paises: MenuItem[];
  estados: MenuItem[];
  niveles_socioeconomicos = SOCIAL_STATUSES;

  @ViewChild('dt') table: Table;

  current_study: number;

  display = false;
  display_modify_study_features = false;
  sent_form = false;

  /* Style of question adding */
  style: string;
  display_new = false;
  display_pool = false;

  /* Object for question appending */
  new_question: Question;

  /* States */
  loading = false;
  preguntaErrorMessage: string;
  studyErrorMessage: string;
  subcategoriesErrorMessage: string;
  categoriesErrorMessage: string;
  academicLevelsErrorMessage: string;
  gendersErrorMessage: string;
  placesErrorMessage: string;
  civilStatusErrorMessage: string;

  /* Form */
  studyForm: FormGroup;
  @ViewChild('sform') studyFormDirective;

  formErrors = {
    edad_minima: '',
    edad_maxima: '',
  };

  validationMessages = {
    edad_minima: {
      pattern: 'Rango debe ser numérico',
      lessThan: 'Rango inicial debe ser menor que rango final'
    },
    edad_maxima: {
      pattern: 'Rango debe ser numérico',
      greaterThan: 'Rango final debe ser mayor que rango inicial'
    }
  };

  constructor(private Activatedroute: ActivatedRoute,
              private router: Router,
              private fb: FormBuilder,
              private academicLevelService: NivelAcademicoService,
              private genderService: GeneroService,
              private civilStatusService: EdocivilService,
              private placeService: PlaceService,
              private categoryService: CategoryService,
              private subcategoryService: SubcategoryService,
              private questionService: QuestionService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService,
              private studiesService: StudiesService,
              private spinner: NgxSpinnerService) {
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('studyId') || 0) === 0) {
      this.router.navigate(['404']);
    }

    /* Get current study */
    else {
      this.loading = true;
      this.spinner.show();
      this.current_study = parseInt(this.Activatedroute.snapshot.queryParamMap.get('studyId'), 10);


      this.studiesService.getStudy(this.current_study).subscribe((study) => {
        this.estudio = study;
        // IF STUDY EXISTS
        if (this.estudio) {
          this.categoryService.getCategories().subscribe((categories) => {
            this.categorias = replaceKeyWithValue(categories);
          }, errorMessage => {
            this.categoriesErrorMessage = errorMessage;
          });

          this.academicLevelService.getNivelesAcademicos().subscribe((levels) => {
            this.niveles_academicos = replaceKeyWithValue(levels);
          }, errorMessage => {
            this.academicLevelsErrorMessage = errorMessage;
          });

          this.genderService.getGeneros().subscribe((genders) => {
            this.generos = replaceKeyWithValue(genders);
          }, errorMessage => {
            this.gendersErrorMessage = errorMessage;
          });

          this.placeService.getCountries().subscribe((countries) => {
            this.paises = replaceKeyWithValue(countries);
          }, errorMessage => {
            this.placesErrorMessage = errorMessage;
          });

          this.civilStatusService.getEdosCiviles().subscribe((statuses) => {
            this.estados_civiles = replaceKeyWithValue(statuses);
          }, errorMessage => {
            this.civilStatusErrorMessage = errorMessage;
          });


          this.studiesService.getStudyQuestions(this.current_study).subscribe((questions) => {
            this.preguntas = questions;
            /* If study is finished it cannot be modified */
            if (this.estudio.fkEstudio.estado === 2) {
              this.router.navigate(['404']);
            }

            /* Get current subcategory */
            this.getSubcategories(this.estudio.fkCategoria._id);

            this.createForm();

            /* Age type filter */
            if (this.estudio.edadMinima && this.estudio.edadMaxima) {
              this.studyForm.patchValue({
                edad_minima: this.estudio.edadMinima,
                edad_maxima: this.estudio.edadMaxima
              });
            }

            /* Academic status filter */
            if (this.estudio.fkNivelAcademico) {
              this.studyForm.patchValue({
                nivel_academico: this.estudio.fkNivelAcademico._id
              });
            }

            /* Socioeconomic status filter */
            if (this.estudio.fkNivelSocioeconomico) {
              this.studyForm.patchValue({
                nivel_socioeconomico: this.estudio.fkNivelSocioeconomico._id
              });
            }

            /* Gender filter */

            if (this.estudio.fkGenero) {
              this.studyForm.patchValue({
                genero: this.estudio.fkGenero._id
              });
            }

            /* Civil status filter */

            if (this.estudio.fkEdoCivil) {
              this.studyForm.patchValue({
                estado_civil: this.estudio.fkEdoCivil._id
              });
            }

            if (this.estudio.tipoFiltroLugar) {
              /* Place type filter */
              if (this.estudio.tipoFiltroLugar === 1) {
                this.studyForm.patchValue({
                  tipo_de_filtro: this.estudio.tipoFiltroLugar,
                  pais: this.estudio.fkLugar._id
                });
              }

              else if (this.estudio.tipoFiltroLugar === 2) {
                this.placeService.getStates(this.estudio.fkLugar.fkLugar._id).subscribe((states) => {
                  this.estados = replaceKeyWithValue(states);
                });

                this.studyForm.patchValue({
                  tipo_de_filtro: this.estudio.tipoFiltroLugar,
                  pais: this.estudio.fkLugar.fkLugar._id,
                  estado: this.estudio.fkLugar._id
                });
              }
            }
            this.spinner.hide();
            this.loading = false;
          });
        } // END IF STUDY EXISTS

        else {
        }
      }, errorMessage => {
        this.router.navigate(['404']);
      });
    }
  }

  ngOnInit(): void {
  }

  showAddOptions() {
    this.display = true;
  }

  getSubcategories(category_id) {
    this.subcategoryService.getSubcategories(category_id).subscribe((subcategories) => {
      this.subcategorias = [];
      for (let i = 0; i < subcategories.length; i++) {
        this.subcategorias.push({
          _id: subcategories[i].fkSubcategoria._id,
          nombre: subcategories[i].fkSubcategoria.nombre
        });
      }

      this.subcategorias = replaceKeyWithValue(this.subcategorias);
    });
  }

  createForm() {
    this.studyForm = this.fb.group({
      categoria: this.estudio.fkCategoria._id,
      subcategoria: this.estudio.fkSubcategoria._id,
      nivel_academico: null,
      nivel_socioeconomico: null,
      estado_civil: null,
      genero: null,
      tipo_de_filtro: null,
      pais: null,
      estado: null,
      edad_minima: [
        null,
        [
          Validators.pattern('^[0-9]*$'),
          RxwebValidators.lessThan({ fieldName: 'edad_maxima' })
        ]
      ],
      edad_maxima: [
        null,
        [
          Validators.pattern('^[0-9]*$'),
          RxwebValidators.greaterThan({ fieldName: 'edad_minima' })
        ]
      ]
    });

    this.studyForm.valueChanges
      .subscribe(data => {
        this.studyForm.setValue(data, { emitEvent: false });
        this.onValueChange(data);
      });
  }

  getStates(country_id) {
    this.placeService.getStates(country_id).subscribe((states) => {
      this.estados = replaceKeyWithValue(states);
    }, errorMessage => {
      this.placesErrorMessage = errorMessage;
    });
  }

  clearPlaces() {
    this.studyForm.patchValue({
      pais: null,
      estado: null
    });
  }

  deleteQuestion(question) {
    this.confirmationService.confirm({
      message: 'La siguiente pregunta: <code>' + question.fkPregunta.pregunta + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.questionService.deleteQuestion(question).subscribe((q) => {

          const index = this.preguntas.indexOf(question);
          if (index > -1) {
            this.preguntas.splice(index, 1);
          }

          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Pregunta eliminada con éxito' });

        }, errorMessage => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        });
      },
      reject: () => {
        //
      }
    });
  }

  onValueChange(data?: any) {
    /* If form hasn't been created */
    if (!this.studyForm) {
      return;
    }

    const form = this.studyForm;
    for (const field in this.formErrors) {
      if (this.formErrors.hasOwnProperty(field)) {
        // clear previous error message if any
        this.formErrors[field] = '';
        const control = form.get(field);

        // if field is modified by user
        if (control && control.dirty && !control.valid) {
          const messages = this.validationMessages[field];

          // check if i'm adding the error message to the field
          for (const key in control.errors) {
            if (control.errors.hasOwnProperty(key)) {
              this.formErrors[field] += messages[key] + ' ';
            }
          }
        }
      }
    }
  }


  showOrHideModifyStudyFeatures() {
    this.display_modify_study_features = !this.display_modify_study_features;
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
      this.sent_form = false;
      this.display_new = false;
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Información modificada con éxito' });
    }, errorMessage => {
      this.display_modify_study_features = false;
      this.sent_form = false;
      this.display_pool = false;
      this.display_new = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    });
  }

  linkPoolQuestion(question) {
    this.studiesService.linkCreatedQuestionToStudy(this.estudio.fkEstudio._id, question.fkPregunta._id).subscribe((question) => {
      this.display_modify_study_features = false;
      this.sent_form = false;
      this.display_pool = false;
      this.display_new = false;
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Pregunta añadida con éxito' });
    });
  }

  linkCreatedQuestion(question) {
    this.studiesService.linkCreatedQuestionToStudy(this.estudio.fkEstudio._id, question.fkPregunta._id).subscribe((question) => {
      this.display_modify_study_features = false;
      this.sent_form = false;
      this.display_pool = false;
      this.display_new = false;
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Pregunta añadida con éxito' });
    });
  }

  getSelectedQuestion(question) {
    if (!this.preguntas) {
      this.preguntas = [];
      this.preguntas.push(question);
    }

    else {
      this.preguntas.push(question);
    }
  }

  onSubmit() {
    this.sent_form = true;
    this.estudio.fkCategoria._id = this.studyForm.value.categoria;
    this.estudio.fkSubcategoria._id = this.studyForm.value.subcategoria;

    if (this.studyForm.value.nivel_academico) {
      this.estudio.fkNivelAcademico = new AcademicLevel();
      this.estudio.fkNivelAcademico._id = this.studyForm.value.nivel_academico;
    }

    if (this.studyForm.value.nivel_socioeconomico) {
      this.estudio.fkNivelSocioeconomico = new SocioEconomicStatus();
      this.estudio.fkNivelSocioeconomico._id = this.studyForm.value.nivel_socioeconomico;
    }

    if (this.studyForm.value.estado_civil) {
      this.estudio.fkEdoCivil = new CivilStatus();
      this.estudio.fkEdoCivil._id = this.studyForm.value.estado_civil;
    }

    if (this.studyForm.value.genero) {
      this.estudio.fkGenero = new Gender();
      this.estudio.fkGenero._id = this.studyForm.value.genero;
    }

    if (this.studyForm.value.tipo_de_filtro) {
      this.estudio.tipoFiltroLugar = this.studyForm.value.tipo_de_filtro;
    }

    if (this.studyForm.value.edad_minima && this.studyForm.value.edad_maxima) {
      this.estudio.edadMinima = parseInt(this.studyForm.value.edad_minima, 10);
      this.estudio.edadMaxima = parseInt(this.studyForm.value.edad_maxima, 10);
    }

    if (this.studyForm.value.tipo_de_filtro) {
      this.estudio.tipoFiltroLugar = this.studyForm.value.tipo_de_filtro;

      if (this.estudio.tipoFiltroLugar == 1) {
        this.estudio.fkLugar = new Place();
        this.estudio.fkLugar._id = this.studyForm.value.pais;
      }
      else if (this.estudio.tipoFiltroLugar == 2) {
        this.estudio.fkLugar = new Place();
        this.estudio.fkLugar._id = this.studyForm.value.estado;
      }
    }

    console.log(this.estudio);
    if (this.studyForm.valid) {
      this.putStudy();
    }

    else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Existen campos inválidos en este formulario' });
      this.sent_form = false;
    }
  }

}
