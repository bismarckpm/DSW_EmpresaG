import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api'
import { Subcategory } from '../../../core/classes/products/subcategory';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { CategoryService } from 'src/app/core/services/admin/products/category.service';
import { SubcategoryService } from 'src/app/core/services/admin/products/subcategory.service';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategorySubcategory } from 'src/app/core/classes/products/category_subcategory';
import { Category } from 'src/app/core/classes/products/category';

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
  categorias: any[];
  subcategory: CategorySubcategory;
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
      this.subcategory = new CategorySubcategory();
      this.subcategory.fkSubcategoria = new Subcategory();
      this.subcategory.fkSubcategoria.nombre = this.subcategoryForm.value.nombre;
      this.subcategory.fkSubcategoria.descripcion = this.subcategoryForm.value.descripcion;
      this.subcategory.fkCategoria = new Category();
      this.subcategory.fkCategoria._id = this.subcategoryForm.value.categoria;
      this.subcategory.fkCategoria.nombre = this.categorias.find(o => o.value == this.subcategory.fkCategoria._id).label;
      this.postSubcategory();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }

}
