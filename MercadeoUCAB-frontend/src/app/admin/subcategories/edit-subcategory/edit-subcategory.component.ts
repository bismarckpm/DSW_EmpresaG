import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api'
import { CategorySubcategory } from 'src/app/core/classes/products/category_subcategory';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { CategoryService } from 'src/app/core/services/admin/products/category.service';
import { SubcategoryService } from 'src/app/core/services/admin/products/subcategory.service';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subcategory } from 'src/app/core/classes/products/subcategory';
import { Category } from 'src/app/core/classes/products/category';

@Component({
  selector: 'app-edit-subcategory',
  templateUrl: './edit-subcategory.component.html',
  styleUrls: ['./edit-subcategory.component.scss'],
  providers: [MessageService]
})
export class EditSubcategoryComponent implements OnInit {
  @Input() display: boolean;
  @Input() subcategory: CategorySubcategory;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onSubcategoryEdited = new EventEmitter<any>();
  categorias: any[];
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
        this.subcategory.fkCategoria._id,
        [
          Validators.required
        ]
      ],
      'nombre': [
        this.subcategory.fkSubcategoria.nombre,
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      'descripcion': [
        this.subcategory.fkSubcategoria.descripcion,
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

  putSubcategory(){
    this.subcategoryService.putSubcategory(this.subcategory).subscribe((s)=>{
      this.subcategory = s;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Subcategoría modificada con éxito'});
      this.sent_form = false;
      this.editSubcategory();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  editSubcategory(){
    this.onSubcategoryEdited.emit(this.subcategory);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.subcategoryForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.subcategory.fkSubcategoria.nombre = this.subcategoryForm.value.nombre;
      this.subcategory.fkSubcategoria.descripcion = this.subcategoryForm.value.descripcion;
      this.subcategory.fkCategoria = new Category();
      this.subcategory.fkCategoria._id = this.subcategoryForm.value.categoria;
      this.subcategory.fkCategoria.nombre = this.categorias.find(o => o.value == this.subcategory.fkCategoria._id).label;

      this.putSubcategory();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }

}
