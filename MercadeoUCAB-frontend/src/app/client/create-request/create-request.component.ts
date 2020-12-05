import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";
import { PlaceService } from '../../services/place.service';
import { MessageService } from 'primeng/api';
import { CategoryService } from '../../services/category.service';
import { SubcategoryService } from '../../services/subcategory.service';
import { ACADEMICS } from '../../constants/academics';
import { SOCIAL_STATUSES } from '../../constants/social_status';
import { GENDERS } from '../../constants/gender';
import { MenuItem } from 'primeng/api';
import { replaceKeyWithValue } from '../../functions/common_functions';
import { RequestsService } from 'src/app/services/requests.service';
import { Request } from 'src/app/classes/request';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators'

@Component({
  selector: 'app-create-request',
  templateUrl: './create-request.component.html',
  styleUrls: ['./create-request.component.scss'],
  providers: [MessageService]
})
export class CreateRequestComponent implements OnInit {
  current_request: number;
  study_request: Request;
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

  /* States */
  loading: boolean = false;
  studyErrorMessage: string;
  subcategoriesErrorMessage: string;
  categoriesErrorMessage: string;
  placesErrorMessage: string;
  sent_form: boolean = false;

  /* Form */
  studyForm: FormGroup;
  @ViewChild('sform') studyFormDirective;

  formErrors = {
    'categoria': '',
    'subcategoria': '',
    'edad_minima': '',
    'edad_maxima': '',
  };

  validationMessages = {
    'categoria': {
      'required': 'Categoría es requerida'
    },
    'subcategoria': {
      'required': 'Subcategoría es requerida'
    },
    'edad_minima': {
      'pattern': 'Rango debe ser numérico',
      'lessThan': 'Rango inicial debe ser menor que rango final'
    },
    'edad_maxima': {
      'pattern': 'Rango debe ser numérico',
      'greaterThan': 'Rango final debe ser mayor que rango inicial'
    }
  }
  constructor(private Activatedroute: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private placeService: PlaceService,
    private categoryService: CategoryService,
    private subcategoryService: SubcategoryService,
    private requestsService: RequestsService,
    private messageService: MessageService,
    private spinner: NgxSpinnerService) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.spinner.show();
    
    this.placeService.getCountries().subscribe((countries) => {
      this.paises = replaceKeyWithValue(countries);
      this.loading = false;
      this.spinner.hide();
    }, errorMessage => {
      this.loading = false;
      this.spinner.hide();
      this.placesErrorMessage = errorMessage;
    })

    this.categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKeyWithValue(categories);
      this.loading = false;
      this.spinner.hide();
    }, errorMessage => {
      this.loading = false;
      this.spinner.hide();
      this.categoriesErrorMessage = errorMessage;
    })
    this.createForm();
  }

  createForm() {
    this.studyForm = this.fb.group({
      'categoria': [
        null,
        [
          Validators.required
        ]
      ],
      'subcategoria': [
        null,
        [
          Validators.required
        ]
      ],
      'nivel_academico': null,
      'nivel_socioeconomico': null,
      'genero': null,
      'tipo_de_filtro': null,
      'pais': null,
      'estado': null,
      'edad_minima': [
        '',
        [
          Validators.pattern('^[0-9]*$'),
          RxwebValidators.lessThan({ fieldName: 'edad_maxima' })
        ]
      ],
      'edad_maxima': [
        '',
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
    })
  }

  clearPlaces() {
    this.studyForm.patchValue({
      pais: null,
      estado: null,
      ciudad: null
    })
    this.study_request.id_lugares = [];
  }

  getSubcategories() {
    this.subcategoryService.getSubcategories(this.studyForm.value.categoria).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
    }, errorMessage => {
      this.subcategoriesErrorMessage = errorMessage;
    })
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
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Solicitud enviada con éxito' });
    }, errorMessage => {
      this.sent_form = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    })
  }

  onSubmit() {
    this.sent_form = true;
    this.study_request = new Request();
    this.study_request.id_categoria = this.studyForm.value.categoria
    this.study_request.id_subcategoria = this.studyForm.value.subcategoria
    this.study_request.id_nivel_academico = this.studyForm.value.nivel_academico
    this.study_request.id_nivel_socioeconomico = this.studyForm.value.nivel_socioeconomico
    this.study_request.id_genero = this.studyForm.value.genero
    this.study_request.tipo_filtro_geografico = this.studyForm.value.tipo_de_filtro
    this.study_request.edad_minima = parseInt(this.studyForm.value.edad_minima)
    this.study_request.edad_maxima = parseInt(this.studyForm.value.edad_maxima)
    this.study_request.id_estado = 1;

    if (this.study_request.tipo_filtro_geografico == 'paises') {
      this.study_request.id_lugares = this.studyForm.value.pais
    }
    else if (this.study_request.tipo_filtro_geografico == 'estados') {
      this.study_request.id_lugares = this.studyForm.value.estado
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
