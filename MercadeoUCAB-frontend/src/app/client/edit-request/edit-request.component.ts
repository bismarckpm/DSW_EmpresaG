import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { PlaceService } from '../../core/services/profile/place.service';
import { MessageService } from 'primeng/api';
import { CategoryService } from '../../core/services/admin/products/category.service';
import { SubcategoryService } from '../../core/services/admin/products/subcategory.service';
import { SOCIAL_STATUSES } from '../../core/constants/social_status';
import { MenuItem } from 'primeng/api';
import { replaceKeyWithValue } from '../../core/functions/common_functions';
import { RequestsService } from 'src/app/core/services/client/requests.service';
import { AcademicLevel } from 'src/app/core/classes/profile/academic_level';
import { CivilStatus } from 'src/app/core/classes/profile/civil_status';
import { Gender } from 'src/app/core/classes/profile/gender';
import { Place } from 'src/app/core/classes/profile/place';
import { SocioEconomicStatus } from 'src/app/core/classes/profile/socioeconomic_status';
import { RequestWithFilter } from 'src/app/core/classes/study/request_with_filter';
import { NivelAcademicoService } from 'src/app/core/services/profile/nivel-academico.service';
import { GeneroService } from 'src/app/core/services/profile/genero.service';
import { EdocivilService } from 'src/app/core/services/profile/edocivil.service';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators';
import {SessionService} from '../../core/services/auth/session.service';


@Component({
  selector: 'app-edit-request',
  templateUrl: './edit-request.component.html',
  styleUrls: ['./edit-request.component.scss'],
  providers: [MessageService]
})
export class EditRequestComponent implements OnInit {
  current_request: number;
  current_user: number;
  study_request: RequestWithFilter;
  categorias: MenuItem[];
  subcategorias: any[];
  generos: MenuItem[];
  niveles_academicos: MenuItem[];
  estados_civiles: MenuItem[];
  paises: MenuItem[];
  estados: MenuItem[];
  niveles_socioeconomicos = SOCIAL_STATUSES;

  /* States */
  loading = false;
  subcategoriesErrorMessage: string;
  categoriesErrorMessage: string;
  academicLevelsErrorMessage: string;
  gendersErrorMessage: string;
  placesErrorMessage: string;
  civilStatusErrorMessage: string;
  sent_form = false;

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
              private placeService: PlaceService,
              private academicLevelService: NivelAcademicoService,
              private genderService: GeneroService,
              private civilStatusService: EdocivilService,
              private categoryService: CategoryService,
              private sessionService: SessionService,
              private subcategoryService: SubcategoryService,
              private requestsService: RequestsService,
              private messageService: MessageService,
              private spinner: NgxSpinnerService) {
    this.current_user = this.sessionService.getCurrentUser();
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('requestId') || 0) === 0) {
      this.router.navigate(['404']);
    }

    else {

      this.loading = true;
      this.spinner.show();
      this.current_request = parseInt(this.Activatedroute.snapshot.queryParamMap.get('requestId'), 10);


      this.requestsService.getUserRequest(this.current_user, this.current_request).subscribe((study) => {
        this.study_request = study;
        // IF REQUEST EXISTS
        if (this.study_request) {
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


          /* If study is finished it cannot be modified */
          if (this.study_request.fkSolicitud.estado === 1 || (this.study_request.fkEstudio)) {
            this.router.navigate(['404']);
          }

          /* Get current subcategory */
          this.getSubcategories(this.study_request.fkCategoria._id);

          this.createForm();

          /* Age type filter */
          if (this.study_request.edadMinima && this.study_request.edadMaxima) {
            this.studyForm.patchValue({
              edad_minima: this.study_request.edadMinima,
              edad_maxima: this.study_request.edadMaxima
            });
          }

          /* Academic status filter */
          if (this.study_request.fkNivelAcademico) {
            this.studyForm.patchValue({
              nivel_academico: this.study_request.fkNivelAcademico._id
            });
          }

          /* Socioeconomic status filter */
          if (this.study_request.fkNivelSocioeconomico) {
            this.studyForm.patchValue({
              nivel_socioeconomico: this.study_request.fkNivelSocioeconomico._id
            });
          }

          /* Gender filter */

          if (this.study_request.fkGenero) {
            this.studyForm.patchValue({
              genero: this.study_request.fkGenero._id
            });
          }

          /* Civil status filter */

          if (this.study_request.fkEdoCivil) {
            this.studyForm.patchValue({
              estado_civil: this.study_request.fkEdoCivil._id
            });
          }

          if (this.study_request.tipoFiltroLugar) {
            /* Place type filter */
            if (this.study_request.tipoFiltroLugar === 1) {
              this.studyForm.patchValue({
                tipo_de_filtro: this.study_request.tipoFiltroLugar,
                pais: this.study_request.fkLugar._id
              });
            }

            else if (this.study_request.tipoFiltroLugar === 2) {
              this.placeService.getStates(this.study_request.fkLugar.fkLugar._id).subscribe((states) => {
                this.estados = replaceKeyWithValue(states);
              });

              this.studyForm.patchValue({
                tipo_de_filtro: this.study_request.tipoFiltroLugar,
                pais: this.study_request.fkLugar.fkLugar._id,
                estado: this.study_request.fkLugar._id
              });
            }
          }
          this.spinner.hide();
          this.loading = false;
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
      categoria: this.study_request.fkCategoria._id,
      subcategoria: this.study_request.fkSubcategoria._id,
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

  putRequest() {
    this.requestsService.putRequest(this.study_request).subscribe((req) => {
      this.sent_form = false;
      this.router.navigate(['my-requests']);
    }, errorMessage => {
      this.sent_form = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    });
  }

  onSubmit() {
    this.sent_form = true;
    this.study_request.fkCategoria._id = this.studyForm.value.categoria;
    this.study_request.fkSubcategoria._id = this.studyForm.value.subcategoria;
    if (this.studyForm.value.nivel_academico) {
      this.study_request.fkNivelAcademico = new AcademicLevel();
      this.study_request.fkNivelAcademico._id = this.studyForm.value.nivel_academico;
    }

    if (this.studyForm.value.nivel_socioeconomico) {
      this.study_request.fkNivelSocioeconomico = new SocioEconomicStatus();
      this.study_request.fkNivelSocioeconomico._id = this.studyForm.value.nivel_socioeconomico;
    }

    if (this.studyForm.value.estado_civil) {
      this.study_request.fkEdoCivil = new CivilStatus();
      this.study_request.fkEdoCivil._id = this.studyForm.value.estado_civil;
    }

    if (this.studyForm.value.genero) {
      this.study_request.fkGenero = new Gender();
      this.study_request.fkGenero._id = this.studyForm.value.genero;
    }

    if (this.studyForm.value.tipo_de_filtro) {
      this.study_request.tipoFiltroLugar = this.studyForm.value.tipo_de_filtro;
    }

    if (this.studyForm.value.edad_minima && this.studyForm.value.edad_maxima) {
      this.study_request.edadMinima = parseInt(this.studyForm.value.edad_minima, 10);
      this.study_request.edadMaxima = parseInt(this.studyForm.value.edad_maxima, 10);
    }

    if (this.studyForm.value.tipo_de_filtro) {
      this.study_request.tipoFiltroLugar = this.studyForm.value.tipo_de_filtro;

      if (this.study_request.tipoFiltroLugar === 1) {
        this.study_request.fkLugar = new Place();
        this.study_request.fkLugar._id = this.studyForm.value.pais;
      }
      else if (this.study_request.tipoFiltroLugar === 2) {
        this.study_request.fkLugar = new Place();
        this.study_request.fkLugar._id = this.studyForm.value.estado;
      }
    }
    if (this.studyForm.valid) {
      this.putRequest();
    }

    else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Existen campos inválidos en este formulario' });
      this.sent_form = false;
    }
  }


}
