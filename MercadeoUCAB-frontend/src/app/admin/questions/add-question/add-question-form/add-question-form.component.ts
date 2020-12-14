import { Component, OnInit, ViewChild, Output, EventEmitter, Input } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { QUESTION_TYPES } from '../../../../core/constants/question_types';
import { CategoryService } from '../../../../core/services/admin/products/category.service';
import { SubcategoryService } from '../../../../core/services/admin/products/subcategory.service';
import { QuestionService } from '../../../../core/services/admin/studies/question.service';
import { replaceKeyWithValue } from '../../../../core/functions/common_functions';
import { Question } from '../../../../core/classes/study/question';
import { Option } from '../../../../core/classes/study/options';
import { NgxSpinnerService } from "ngx-spinner";

/* Form */
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators'
import { Router } from '@angular/router';
import { QuestionCategorySubcategory } from 'src/app/core/classes/study/question_category_subcategory';
import { Category } from 'src/app/core/classes/products/category';
import { Subcategory } from 'src/app/core/classes/products/subcategory';
import { QuestionType } from 'src/app/core/classes/study/question_type';

@Component({
  selector: 'app-add-question-form',
  templateUrl: './add-question-form.component.html',
  styleUrls: ['./add-question-form.component.scss'],
  providers: [MessageService]
})
export class AddQuestionFormComponent implements OnInit {
  tipos_pregunta = QUESTION_TYPES;
  categorias: MenuItem[];
  subcategorias: any[];
  pregunta: QuestionCategorySubcategory;
  categoriesErrorMessage: string;
  subcategoriesErrorMessage: string;
  sent_form: boolean = false;
  loading: boolean = true;

  /* States */
  disable_category_select: boolean = false;
  show_close_button: boolean = false;

  /* Form */
  questionForm: FormGroup;
  @ViewChild('qform') questionFormDirective;

  /* Emitter */
  @Output() onQuestionCreate = new EventEmitter<any>();
  @Output() onViewClose = new EventEmitter<any>();
  @Input() category_id: number;
  @Input() subcategory_id: number;

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
      'pattern': 'Rango debe ser numérico'
    },
    'rango_final': {
      'pattern': 'Rango debe ser numérico',
      'greaterThan': 'Rango final debe ser mayor que rango inicial'
    }
  }
  constructor(
    private router: Router,
    private categoryService: CategoryService,
    private messageService: MessageService,
    private subcategoryService: SubcategoryService,
    private questionService: QuestionService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService) {
      this.createForm();
      this.spinner.show();
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

    /* If this component is called from edit study */
    if (this.category_id && this.subcategory_id){
      this.subcategoryService.getSubcategories(this.category_id).subscribe((subcategory) => {
        this.subcategorias = replaceKeyWithValue(subcategory);

        this.questionForm.patchValue({
          categoria: this.category_id,
          subcategoria: this.subcategory_id
        })

        this.disable_category_select = true;
        this.show_close_button = true;
      })
    }
  }

  createForm(){
    this.questionForm = this.fb.group({
      categoria: [
        null,
        [
          Validators.required
        ]
      ],
      subcategoria: null,
      pregunta: [
        '',
        [
          Validators.required,
          Validators.maxLength(1000),
          Validators.minLength(4)
        ]
      ],
      tipo_de_pregunta: [
        null,
        [
          Validators.required,
        ]
      ],
      opciones: this.fb.array([
        this.fb.control('')
      ]),
      rango_inicial:
      [
        '',
        [
          Validators.pattern('^[0-9]*$')
        ]
      ],
      rango_final: [
        '',
        [
          Validators.pattern('^[0-9]*$'),
          RxwebValidators.greaterThan({fieldName: 'rango_inicial'})
        ]
      ],
    });


    this.questionForm.valueChanges
    .subscribe(data => {
      this.questionForm.setValue(data, { emitEvent: false });
      this.onValueChange(data);
    });
  }

  getSubcategories(){
    this.subcategoryService.getSubcategories(this.questionForm.value.categoria).subscribe((subcategories) => {
      this.subcategorias = [];
      for (var i = 0; i<subcategories.length; i++){
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

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.questionForm){
      return;
    }

    const form = this.questionForm;
    for (const field in this.formErrors){
      if (this.formErrors.hasOwnProperty(field)){
        // clear previous error message if any
        this.formErrors[field] = '';
        const control = form.get(field);

        // if field is modified by user
        if (control && control.dirty && !control.valid){
          const messages = this.validationMessages[field];

          // check if i'm adding the error message to the field
          for (const key in control.errors){
            if (control.errors.hasOwnProperty(key)){
              this.formErrors[field] += messages[key] + ' ';
            }
          }
        }
      }
    }
  }

  clearFormArray(){
    this.opciones.clear();
  }

  addItem(){
    this.opciones.push(this.fb.control(''));
  }

  deleteItem(index){
    this.opciones.removeAt(index);
  }

  postQuestion(type_of_option){
    this.pregunta = new QuestionCategorySubcategory();
    this.pregunta.fkCategoria = new Category();
    this.pregunta.fkCategoria._id = this.questionForm.value.categoria;

    if (this.questionForm.value.subcategoria){
      this.pregunta.fkSubcategoria = new Subcategory();
      this.pregunta.fkSubcategoria._id = this.questionForm.value.subcategoria;
    }

    this.pregunta.fkPregunta = new Question();
    this.pregunta.fkPregunta.pregunta = this.questionForm.value.pregunta;
    this.pregunta.fkPregunta.fkTipoPregunta = new QuestionType();
    this.pregunta.fkPregunta.fkTipoPregunta._id = this.questionForm.value.tipo_de_pregunta;
    this.pregunta.fkPregunta.fkTipoPregunta.nombre = this.tipos_pregunta.find(o => o.value == this.pregunta.fkPregunta.fkTipoPregunta._id).label;


    if (type_of_option == 1){
      this.pregunta.fkPregunta.listOpciones = [];
      for (var i=0; i < this.questionForm.value.opciones.length; i++){
        this.pregunta.fkPregunta.listOpciones.push({
          valor: this.questionForm.value.opciones[i]
        });
      }
    }

    else if (type_of_option == 2){
      let qoption: Option[] = [];
      qoption.push({
        rangoInicial: parseInt(this.questionForm.value.rango_inicial),
        rangoFinal: parseInt(this.questionForm.value.rango_final)
      });
      this.pregunta.fkPregunta.listOpciones = qoption;
    }


    this.questionService.postQuestion(this.pregunta).subscribe((res)=>{
      this.pregunta._id = res._id
      this.pregunta.fkPregunta._id = res.fkPregunta._id
      this.nextForm()
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    });

  }

  nextForm(){
    this.onQuestionCreate.emit(this.pregunta);
  }

  closeView(){
    this.onViewClose.emit(1);
  }

  onSubmit(){
    this.sent_form = true;
    /* CASOS VALIDOS */
    if (this.questionForm.value.tipo_de_pregunta == 5 && this.questionForm.value.rango_inicial && this.questionForm.value.rango_final && this.questionForm.valid){
      this.postQuestion(2)
    }

    else if ((this.questionForm.value.tipo_de_pregunta == 2 || this.questionForm.value.tipo_de_pregunta == 3) && this.questionForm.value.opciones.length > 1 && this.questionForm.valid){
      this.postQuestion(1);
    }

    else if (this.questionForm.value.tipo_de_pregunta != 5 && this.questionForm.value.tipo_de_pregunta != 2
      && this.questionForm.value.tipo_de_pregunta != 3 && this.questionForm.valid){
      this.postQuestion(3);
    }

    /* CASOS INVALIDOS */

    else if (this.questionForm.valid && (!this.questionForm.value.rango_inicial || !this.questionForm.value.rango_final) && this.questionForm.value.tipo_de_pregunta == 5){
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe completar ambos campos de rango con datos válidos'});
      this.sent_form = false;
    }

    else if (this.questionForm.valid && this.questionForm.value.opciones.length < 2 && (this.questionForm.value.tipo_de_pregunta == 3 || this.questionForm.value.tipo_de_pregunta == 2 )){
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe añadir al menos dos opciones'});
      this.sent_form = false;
    }

    else {
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe completar los campos requeridos con datos validos'});
      this.sent_form = false;
    }
  }

}
