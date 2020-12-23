import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ConfirmationService, MenuItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { QUESTION_TYPES } from '../../../core/constants/question_types';
import { CategoryService } from '../../../core/services/admin/products/category.service';
import { SubcategoryService } from '../../../core/services/admin/products/subcategory.service';
import { QuestionService } from '../../../core/services/admin/studies/question.service';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { Option } from '../../../core/classes/study/options';
import { NgxSpinnerService } from "ngx-spinner";

/* Form */
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators'
import { QuestionCategorySubcategory } from 'src/app/core/classes/study/question_category_subcategory';
import { OptionService } from 'src/app/core/services/admin/studies/option.service';

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class EditQuestionComponent implements OnInit {
  tipos_pregunta = QUESTION_TYPES;
  categorias: MenuItem[];
  subcategorias: any[];
  pregunta: QuestionCategorySubcategory;
  categoriesErrorMessage: string;
  subcategoriesErrorMessage: string;
  sent_form: boolean = false;
  loading: boolean = true;
  current_question: number;

  /* Form */
  questionForm: FormGroup;
  //opciones: FormArray;
  @ViewChild('qform') questionFormDirective;

  formErrors = {
    'categoria': '',
    'pregunta': '',
    'tipo_de_pregunta': '',
    'control': '',
    'rango_inicial': '',
    'rango_final': '',
  };

  validationMessages = {
    'categoria': {
      'required': 'Categoría es requerida'
    },
    'pregunta': {
      'required': 'Pregunta es requerida',
      'minlength': 'Pregunta debe tener al menos 4 caracteres',
      'maxlength': 'Pregunta no debe tener más de 1000 caracteres',
    },
    'tipo_de_pregunta': {
      'required': 'Tipo de pregunta es requerido'
    },
    'control': {
      'required': 'Opción es requerida'
    },
    'rango_inicial': {
      'pattern': 'Rango debe ser numérico',
      'lessThan': 'Rango inicial debe ser menor que rango final'
    },
    'rango_final': {
      'pattern': 'Rango debe ser numérico',
      'greaterThan': 'Rango final debe ser mayor que rango inicial'
    }
  }
  constructor(private Activatedroute: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private subcategoryService: SubcategoryService,
    private questionService: QuestionService,
    private optionService: OptionService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService) {
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('questionId') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current question */
    else {
      this.spinner.show();
      this.current_question = parseInt(this.Activatedroute.snapshot.queryParamMap.get('questionId'));
      this.questionService.getQuestion(this.current_question).subscribe((question) => {
        this.pregunta = question;
        if (this.pregunta) {
          this.createForm();

          /* Patch value of subcategory only if it's linked to one */
          if (this.pregunta.fkSubcategoria) {
            this.questionForm.patchValue({
              subcategoria: this.pregunta.fkSubcategoria._id
            })
          }

          // Only add to form array if there are options
          if (this.pregunta.fkPregunta.listOpciones && this.pregunta.fkPregunta.listOpciones.length > 0
            && (this.pregunta.fkPregunta.fkTipoPregunta._id == 2
              || this.pregunta.fkPregunta.fkTipoPregunta._id == 3))

            this.setFormArray();

          // Add range
          if (this.pregunta.fkPregunta.listOpciones && this.pregunta.fkPregunta.listOpciones.length > 0
            && this.pregunta.fkPregunta.fkTipoPregunta._id == 5) {
            this.questionForm.patchValue({
              rango_inicial: this.pregunta.fkPregunta.listOpciones[0].rangoInicial,
              rango_final: this.pregunta.fkPregunta.listOpciones[0].rangoFinal
            })
          }

          this.getSubcategories(); // Get subcategories according to the category selected
        }

        else {
          this.router.navigate(['404']);
        }
      });
    }
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((categories) => {
      this.loading = false;
      this.categorias = replaceKeyWithValue(categories);
      this.spinner.hide();
    }, errorMessage => {
      this.loading = false;
      this.categoriesErrorMessage = errorMessage;
      this.spinner.hide();
    })
  }

  createForm() {
    this.questionForm = this.fb.group({
      categoria: [
        this.pregunta.fkCategoria._id,
        [
          Validators.required
        ]
      ],
      subcategoria: null,
      pregunta: [
        this.pregunta.fkPregunta.pregunta,
        [
          Validators.required,
          Validators.maxLength(1000),
          Validators.minLength(4)
        ]
      ],
      tipo_de_pregunta: [
        this.pregunta.fkPregunta.fkTipoPregunta._id,
        [
          Validators.required,
        ]
      ],
      opciones: this.fb.array([
      ]),
      rango_inicial:
        [
          '',
          [
            Validators.pattern('^[0-9]*$'),
            RxwebValidators.lessThan({ fieldName: 'rango_final' })
          ]
        ],
      rango_final: [
        '',
        [
          Validators.pattern('^[0-9]*$'),
          RxwebValidators.greaterThan({ fieldName: 'rango_inicial' })
        ]
      ],
    });


    this.questionForm.valueChanges
      .subscribe(data => {
        this.questionForm.setValue(data, { emitEvent: false });
        this.onValueChange(data);
      });
  }

  setFormArray() {
    for (var i = 0; i < this.pregunta.fkPregunta.listOpciones.length; i++) {
      this.addExistingItem(this.pregunta.fkPregunta.listOpciones[i].valor);
    }
  }

  getSubcategories() {
    this.subcategoryService.getSubcategories(this.questionForm.value.categoria).subscribe((subcategories) => {
      this.subcategorias = [];
      for (var i = 0; i < subcategories.length; i++) {
        this.subcategorias.push({
          value: subcategories[i].fkSubcategoria._id,
          label: subcategories[i].fkSubcategoria.nombre
        })
      }
    }, errorMessage => {
      this.subcategoriesErrorMessage = errorMessage;
    })
  }

  get opciones() {
    return this.questionForm.get('opciones') as FormArray;
  }

  onValueChange(data?: any) {
    /* If form hasn't been created */
    if (!this.questionForm) {
      return;
    }

    const form = this.questionForm;
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

  clearFormArray() {
    /* Don't delete options if switch between selections */
    if (this.pregunta.fkPregunta.fkTipoPregunta._id == 2 || this.pregunta.fkPregunta.fkTipoPregunta._id == 3){
      if (this.questionForm.value.tipo_de_pregunta == 2 || this.questionForm.value.tipo_de_pregunta == 3){
        return;
      }
    }
    else {
      this.opciones.clear();
      this.pregunta.fkPregunta.listOpciones = [];
    }
  }

  addItem() {
    this.opciones.push(this.fb.control(''));
  }

  addExistingItem(item) {
    this.opciones.push(this.fb.control(item))
  }

  deleteItem(index) {
    /* Delete option from BD */
    if (this.pregunta.fkPregunta.listOpciones[index]) {
      this.confirmationService.confirm({
        message: 'La opción: <code>' + this.pregunta.fkPregunta.listOpciones[index].valor + '</code> está apunto de ser eliminada, ¿Desea continuar? Esta acción eliminará la opción de la base de datos y no podrá deshacerla',
        header: 'Confirmación',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          this.optionService.deleteOption(this.pregunta.fkPregunta.listOpciones[index]).subscribe((op) => {
            this.opciones.removeAt(index);
            this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Opción eliminada con éxito' });
          }, errorMessage => {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
          })
        },
        reject: () => {
          //
        }
      });
    }

    else {
      this.opciones.removeAt(index);
    }
  }

  putQuestion(type_of_option) {
    this.pregunta.fkCategoria._id = this.questionForm.value.categoria;

    if (this.questionForm.value.subcategoria){
      this.pregunta.fkSubcategoria._id = this.questionForm.value.subcategoria
    }

    this.pregunta.fkPregunta.pregunta = this.questionForm.value.pregunta;
    this.pregunta.fkPregunta.fkTipoPregunta._id = this.questionForm.value.tipo_de_pregunta;

    /* Es una pregunta de seleccion simple o multiple */
    if (type_of_option == 1) {
      for (var i = 0; i < this.questionForm.value.opciones.length; i++) {

        /* Si ya existia la opcion se cambia el valor, si es una opcion agregada recientemente se hace push */
        if (this.pregunta.fkPregunta.listOpciones[i]) {
          this.pregunta.fkPregunta.listOpciones[i].valor = this.questionForm.value.opciones[i]
        }
        else {
          this.pregunta.fkPregunta.listOpciones.push({
            valor: this.questionForm.value.opciones[i]
          });
        }
      }
    }

    /* Es una pregunta de rango */
    else if (type_of_option == 2) {
      let qoption: Option[] = [];
      qoption.push({
        rangoInicial: parseInt(this.questionForm.value.rango_inicial),
        rangoFinal: parseInt(this.questionForm.value.rango_final)
      });
      this.pregunta.fkPregunta.listOpciones = qoption;
    }

    else {
      if (this.pregunta.fkPregunta.listOpciones) {
        delete this.pregunta["opciones"];
      }
    }

    /* 3 = No es ninguno de los anteriores */
    this.questionService.putQuestion(this.pregunta).subscribe((res) => {
      let redirect_to = this.Activatedroute.snapshot.queryParamMap.get('origin') || 0
      let study_origin = this.Activatedroute.snapshot.queryParamMap.get('studyId') || 0
      /* If it comes from questions go to questions table */
      if (redirect_to == 'questions' || redirect_to == 0) {
        this.router.navigate(['questions'])
      }

      /* If it comes from a study edit, go back to editing the study */
      if (redirect_to == 'study') {
        this.router.navigate(['studies/edit'], { queryParams: { studyId: study_origin } })
      }
    }, errorMessage => {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
      this.sent_form = false;
    });
  }

  onSubmit() {
    this.sent_form = true;
    /* CASOS VALIDOS */
    if (this.questionForm.value.tipo_de_pregunta == 5 && this.questionForm.value.rango_inicial && this.questionForm.value.rango_final && this.questionForm.valid) {
      this.putQuestion(2)
    }

    else if ((this.questionForm.value.tipo_de_pregunta == 2 || this.questionForm.value.tipo_de_pregunta == 3) && this.questionForm.value.opciones.length > 1 && this.questionForm.valid) {
      this.putQuestion(1);
    }

    else if (this.questionForm.value.tipo_de_pregunta != 5 && this.questionForm.value.tipo_de_pregunta != 2
      && this.questionForm.value.tipo_de_pregunta != 3 && this.questionForm.valid) {
      this.putQuestion(3);
    }

    /* CASOS INVALIDOS */

    else if (this.questionForm.valid && (!this.questionForm.value.rango_inicial || !this.questionForm.value.rango_final) && this.questionForm.value.tipo_de_pregunta == 5) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe completar ambos campos de rango con datos válidos' });
      this.sent_form = false;
    }

    else if (this.questionForm.valid && this.questionForm.value.opciones.length < 2 && (this.questionForm.value.tipo_de_pregunta == 3 || this.questionForm.value.tipo_de_pregunta == 2)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe añadir al menos dos opciones' });
      this.sent_form = false;
    }

    else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe completar los campos requeridos con datos validos' });
      this.sent_form = false;
    }
  }

}
