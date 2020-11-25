import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api'
import { Subcategory } from '../../classes/subcategory';
import { replaceKeyWithValue } from '../../functions/common_functions';
import { CategoryService } from 'src/app/services/category.service';
import { SubcategoryService } from 'src/app/services/subcategory.service';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-subcategory',
  templateUrl: './add-subcategory.component.html',
  styleUrls: ['./add-subcategory.component.scss'],
  providers: [MessageService]
})
export class AddSubcategoryComponent implements OnInit {

  @Input() display: boolean;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onSubcategoryAdded = new EventEmitter<any>();
  tipos: MenuItem[];
  categorias: MenuItem[];
  subcategory: Subcategory;
  sent_form: boolean = false;

  /* Form */
  subcategoryForm: FormGroup;
  @ViewChild('sform') subcategoryFormDirective;

  constructor(private fb: FormBuilder,
    private messageService: MessageService,
    private categoryService: CategoryService,
    private subcategoryService: SubcategoryService) { }

  formErrors = {
    'categoria': '',
    'nombre': '',
    'descripcion': ''
  };

  validationMessages = {
    'categoria': {
      'required': 'Categoría es requerida'
    },
    'nombre': {
      'required': 'Nombre de subcategoría es requerido',
      'maxlength': 'Nombre de subcategoría no puede exceder los 90 caracteres'
    },
    'descripcion': {
      'maxlength': 'Descripción no puede exceder los 500 caracteres'
    }
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((category) => {
      this.categorias = replaceKeyWithValue(category)
    })
    this.createForm();
  }

  createForm(){
    this.subcategoryForm = this.fb.group({
      'categoria': [
        null,
        [
          Validators.required
        ]
      ],
      'nombre': [
        '',
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      'descripcion': [
        '',
        [
          Validators.maxLength(500)
        ]
      ]
    });
    

    this.subcategoryForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.subcategoryForm){
      return;
    }

    const form = this.subcategoryForm;
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

  postSubcategory(){
    this.subcategoryService.postSubcategory(this.subcategory).subscribe((s)=>{
      this.subcategory = s;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Subcategoría añadida con éxito'});
      this.sent_form = false;
      this.appendSubcategory();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  appendSubcategory(){
    this.onSubcategoryAdded.emit(this.subcategory);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.subcategoryForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.subcategory = new Subcategory();
      this.subcategory.nombre = this.subcategoryForm.value.nombre;
      this.subcategory.id_categoria = this.subcategoryForm.value.categoria;
      this.subcategory.descripcion = this.subcategoryForm.value.descripcion;

      this.postSubcategory();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }

}