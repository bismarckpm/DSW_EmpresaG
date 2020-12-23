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
import { StudyRequest } from 'src/app/core/classes/study/study_request';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators';
import { NivelAcademicoService } from 'src/app/core/services/profile/nivel-academico.service';
import { GeneroService } from 'src/app/core/services/profile/genero.service';
import { EdocivilService } from 'src/app/core/services/profile/edocivil.service';
import { RequestWithFilter } from 'src/app/core/classes/study/request_with_filter';
import { SocioEconomicStatus } from 'src/app/core/classes/profile/socioeconomic_status';
import { AcademicLevel } from 'src/app/core/classes/profile/academic_level';
import { CivilStatus } from 'src/app/core/classes/profile/civil_status';
import { Gender } from 'src/app/core/classes/profile/gender';
import { Place } from 'src/app/core/classes/profile/place';
import { Category } from 'src/app/core/classes/products/category';
import { Subcategory } from 'src/app/core/classes/products/subcategory';
import { Users } from 'src/app/core/classes/auth/users';
import {SessionService} from '../../core/services/auth/session.service';

@Component({
  selector: 'app-create-request',
  templateUrl: './create-request.component.html',
  styleUrls: ['./create-request.component.scss'],
  providers: [MessageService]
})
export class CreateRequestComponent implements OnInit {
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
    nombre: '',
    categoria: '',
    subcategoria: '',
    edad_minima: '',
    edad_maxima: '',
  };

  validationMessages = {
    nombre: {
      maxlength: 'Nombre no puede ser mayor a 100 caracteres'
    },
    categoria: {
      required: 'Categoría es requerida'
    },
    subcategoria: {
      required: 'Subcategoría es requerida'
    },
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
              private sessionService: SessionService,
              private placeService: PlaceService,
              private academicLevelService: NivelAcademicoService,
              private genderService: GeneroService,
              private civilStatusService: EdocivilService,
              private categoryService: CategoryService,
              private subcategoryService: SubcategoryService,
              private requestsService: RequestsService,
              private messageService: MessageService,
              private spinner: NgxSpinnerService) {
    this.current_user = this.sessionService.getCurrentUser();
  }

  ngOnInit(): void {
    this.loading = true;
    this.spinner.show();
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

    this.createForm();
  }

  createForm() {
    this.studyForm = this.fb.group({
      nombre: '',
      categoria: null,
      subcategoria: null,
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

    this.loading = false;
    this.spinner.hide();

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

  postRequest() {
    this.requestsService.postRequest(this.study_request).subscribe((req) => {
      this.sent_form = false;
      this.router.navigate(['my-requests']);
      // this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Solicitud enviada con éxito' });
    }, errorMessage => {
      this.sent_form = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    });
  }

  onSubmit() {
    this.sent_form = true;
    this.study_request = new RequestWithFilter();
    this.study_request.nombre = this.studyForm.value.nombre;
    this.study_request.fkCategoria = new Category();
    this.study_request.fkCategoria._id = this.studyForm.value.categoria;
    this.study_request.fkSubcategoria = new Subcategory();
    this.study_request.fkSubcategoria._id = this.studyForm.value.subcategoria;
    this.study_request.fkSolicitud = new StudyRequest();
    this.study_request.fkSolicitud.fkUsuario = new Users();
    this.study_request.fkSolicitud.fkUsuario._id = this.current_user;
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
      this.postRequest();
    }

    else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Existen campos inválidos o incompletos en este formulario' });
      this.sent_form = false;
    }
  }

}
