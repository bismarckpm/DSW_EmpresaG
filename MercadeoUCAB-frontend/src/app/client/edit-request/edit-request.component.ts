import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";
import { PlaceService } from '../../services/place.service';
import { Study } from '../../classes/study';
import { MessageService } from 'primeng/api';
import { CategoryService } from '../../services/category.service';
import { SubcategoryService } from '../../services/subcategory.service';
import { ACADEMICS } from '../../constants/academics';
import { SOCIAL_STATUSES } from '../../constants/social_status';
import { GENDERS } from '../../constants/gender';
import { MenuItem } from 'primeng/api';
import { replaceKeyWithValue } from '../../functions/common_functions';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators'
import { RequestsService } from 'src/app/services/requests.service';

@Component({
  selector: 'app-edit-request',
  templateUrl: './edit-request.component.html',
  styleUrls: ['./edit-request.component.scss'],
  providers: [MessageService]
})
export class EditRequestComponent implements OnInit {
  current_request: number;
  study_request: Study;
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
    'edad_minima': '',
    'edad_maxima': '',
  };

  validationMessages = {
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
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('requestId') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current study */
    else {
      this.loading = true;
      this.spinner.show();

      this.current_request = parseInt(this.Activatedroute.snapshot.queryParamMap.get('requestId'));
      this.requestsService.getRequest(this.current_request).subscribe((req) => {
        this.study_request = req;
        // IF REQUEST EXISTS
        if (this.study_request) {
          /* If request is being processed it can't be modified */
          if (this.study_request.id_estado == 2) {
            this.router.navigate(['404']);
          }

          subcategoryService.getSubcategories(this.study_request.id_categoria).subscribe((subcategories) => {
            this.subcategorias = replaceKeyWithValue(subcategories);
          }, errorMessage => {
            this.subcategoriesErrorMessage = errorMessage;
          })

          this.createForm();
          this.studyForm.patchValue({
            nivel_academico: this.study_request.id_nivel_academico,
            nivel_socioeconomico: this.study_request.id_nivel_socioeconomico
          })

          /* Determine type of location filter */
          if (this.study_request.tipo_filtro_geografico == 'paises') {
            this.studyForm.patchValue({
              pais: this.study_request.id_lugares
            })
          }

          else if (this.study_request.tipo_filtro_geografico == 'estados') {
            this.placeService.getState(this.study_request.id_lugares[0]).subscribe((stateresult) => {
              this.getStates(stateresult.country_id);
              this.studyForm.patchValue({
                pais: stateresult.country_id,
                estado: this.study_request.id_lugares
              })
            }, errorMessage => {
              this.placesErrorMessage = errorMessage;
            })
          }

          this.spinner.hide();
          this.loading = false;
        } // END IF REQUEST EXISTS

        else {
          this.router.navigate(['404']);
        }
      }, errorMessage => {
        this.loading = false;
        this.spinner.hide();
        this.studyErrorMessage = errorMessage;
      });
    }

    placeService.getCountries().subscribe((countries) => {
      this.paises = countries;
    }, errorMessage => {
      this.placesErrorMessage = errorMessage;
    })

    categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKeyWithValue(categories);
    }, errorMessage => {
      this.categoriesErrorMessage = errorMessage;
    })
  }

  ngOnInit(): void {
  }

  getSubcategories() {
    this.subcategoryService.getSubcategories(this.studyForm.value.categoria).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
    }, errorMessage => {
      this.subcategoriesErrorMessage = errorMessage;
    })
  }

  createForm() {
    this.studyForm = this.fb.group({
      'categoria': this.study_request.id_categoria,
      'subcategoria': this.study_request.id_subcategoria,
      'nivel_academico': null,
      'nivel_socioeconomico': null,
      'genero': this.study_request.id_genero,
      'tipo_de_filtro': this.study_request.tipo_filtro_geografico,
      'pais': null,
      'estado': null,
      'edad_minima': [
        this.study_request.edad_minima,
        [
          Validators.pattern('^[0-9]*$'),
          RxwebValidators.lessThan({ fieldName: 'edad_maxima' })
        ]
      ],
      'edad_maxima': [
        this.study_request.edad_maxima,
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
      this.estados = states;
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
      this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Información modificada con éxito' });
    }, errorMessage => {
      this.sent_form = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
    })
  }

  onSubmit() {
    this.sent_form = true;
    this.study_request.id_categoria = this.studyForm.value.categoria
    this.study_request.id_subcategoria = this.studyForm.value.subcategoria
    this.study_request.id_nivel_academico = this.studyForm.value.nivel_academico
    this.study_request.id_nivel_socioeconomico = this.studyForm.value.nivel_socioeconomico
    this.study_request.id_genero = this.studyForm.value.genero
    this.study_request.tipo_filtro_geografico = this.studyForm.value.tipo_de_filtro
    this.study_request.edad_minima = parseInt(this.studyForm.value.edad_minima)
    this.study_request.edad_maxima = parseInt(this.studyForm.value.edad_maxima)

    if (this.study_request.tipo_filtro_geografico == 'paises') {
      this.study_request.id_lugares = this.studyForm.value.pais
    }
    else if (this.study_request.tipo_filtro_geografico == 'estados') {
      this.study_request.id_lugares = this.studyForm.value.estado
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
