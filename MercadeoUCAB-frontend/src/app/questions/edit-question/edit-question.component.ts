import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { QUESTION_TYPES } from '../../constants/question_types';
import { CategoryService } from '../../services/category.service';
import { SubcategoryService } from '../../services/subcategory.service';
import { QuestionService } from '../../services/question.service';
import { replaceKeyWithValue } from '../../functions/common_functions';
import { Question } from '../../classes/question';
import { Option } from '../../classes/options';
import { NgxSpinnerService } from "ngx-spinner";

/* Form */
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { RxwebValidators } from '@rxweb/reactive-form-validators'

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.scss'],
  providers: [MessageService]
})
export class EditQuestionComponent implements OnInit {
  tipos_pregunta = QUESTION_TYPES;
  categorias: MenuItem[];
  subcategorias: MenuItem[];
  pregunta: Question;
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
      'pattern': 'Rango debe ser numérico'
    },
    'rango_final': {
      'pattern': 'Rango debe ser numérico',
      'greaterThan': 'Rango final debe ser mayor que rango inicial'
    }
  }
  constructor(private Activatedroute:ActivatedRoute,
    private router:Router,
    private categoryService: CategoryService,
    private messageService: MessageService,
    private subcategoryService: SubcategoryService,
    private questionService: QuestionService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService) { 
      /* If query is empty return 404 */
      if ((this.Activatedroute.snapshot.queryParamMap.get('qid')||0) == 0){
        this.router.navigate(['404']);
      }

      /* Get current question */
      else {
        this.current_question = parseInt(this.Activatedroute.snapshot.queryParamMap.get('qid'));
        this.questionService.getQuestion(this.current_question).subscribe((question) => {
          this.pregunta = question;
          this.createForm();

          // Only add to form array if there are options
          if (this.pregunta.opciones && this.pregunta.opciones.length>0)
            this.setFormArray();


          this.getSubcategories(); // Get subcategories according to the category selected
          this.spinner.show();
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

  createForm(){
    this.questionForm = this.fb.group({
      categoria: [
        this.pregunta.id_categoria,
        [
          Validators.required
        ]
      ],
      subcategoria: this.pregunta.id_subcategoria,
      pregunta: [
        this.pregunta.pregunta,
        [
          Validators.required,
          Validators.maxLength(1000),
          Validators.minLength(4)
        ]
      ],
      tipo_de_pregunta: [
        this.pregunta.id_tipo,
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

  setFormArray(){
    for (var i =0; i < this.pregunta.opciones.length; i++){
      this.addExistingItem(this.pregunta.opciones[i].valor);
    }
  }

  getSubcategories(){
    this.subcategoryService.getSubcategories(this.questionForm.value.categoria).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
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

  addExistingItem(item){
    this.opciones.push(this.fb.control(item))
  }

  deleteItem(index){
    this.opciones.removeAt(index);
  }

  putQuestion(type_of_option){
    this.pregunta.id_categoria = this.questionForm.value.categoria;
    this.pregunta.id_subcategoria = this.questionForm.value.subcategoria;
    this.pregunta.pregunta = this.questionForm.value.pregunta;
    this.pregunta.id_tipo = this.questionForm.value.tipo_de_pregunta;
    
    /* Es una pregunta de seleccion simple o multiple */
    if (type_of_option == 1){
      this.pregunta.opciones = [];
      for (var i=0; i < this.questionForm.value.opciones.length; i++){
        this.pregunta.opciones.push({
          valor: this.questionForm.value.opciones[i]
        });
      }
    }

    /* Es una pregunta de rango */
    else if (type_of_option == 2){
      let qoption: Option[];
      qoption[0].rango_inicial = this.questionForm.value.rango_inicial;
      qoption[0].rango_final = this.questionForm.value.rango_final;
      this.pregunta.opciones = qoption;
    }
    
    /* 3 = No es ninguno de los anteriores */
    this.questionService.putQuestion(this.pregunta).subscribe((res)=>{
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    });
  }

  onSubmit(){
    this.sent_form = true;
    /* CASOS VALIDOS */
    if (this.questionForm.value.tipo_de_pregunta == 5 && this.questionForm.value.rango_inicial && this.questionForm.value.rango_final && this.questionForm.valid){
      this.putQuestion(2)
    }

    else if ((this.questionForm.value.tipo_de_pregunta == 2 || this.questionForm.value.tipo_de_pregunta == 3) && this.questionForm.value.opciones.length > 1 && this.questionForm.valid){
      this.putQuestion(1);
    }
    
    else if (this.questionForm.value.tipo_de_pregunta != 5 && this.questionForm.value.tipo_de_pregunta != 2
      && this.questionForm.value.tipo_de_pregunta != 3 && this.questionForm.valid){
      this.putQuestion(3);
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
