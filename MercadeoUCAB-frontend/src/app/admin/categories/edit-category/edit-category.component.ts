import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api'
import { Category } from '../../../core/classes/products/category';
import { TypesService } from '../../../core/services/admin/products/types.service';
import { CategoryService } from 'src/app/core/services/admin/products/category.service';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.scss'],
  providers: [MessageService]
})
export class EditCategoryComponent implements OnInit {
  @Input() display: boolean;
  @Input() category: Category;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onCategoryEdited = new EventEmitter<any>();
  tipos: MenuItem[];
  sent_form: boolean = false;

  /* Form */
  categoryForm: FormGroup;
  @ViewChild('cform') categoryFormDirective;

  constructor(private fb: FormBuilder,
    private categoryService: CategoryService,
    private messageService: MessageService,
    private typesService: TypesService) { }

  formErrors = {
    'nombre': '',
    'descripcion': ''
  };

  validationMessages = {
    'nombre': {
      'required': 'Nombre de categoría es requerido',
      'maxlength': 'Nombre de categoría no puede exceder los 90 caracteres'
    },
    'descripcion': {
      'maxlength': 'Descripción no puede exceder los 500 caracteres'
    }
  }

  ngOnInit(): void {
    this.typesService.getALLTypes().subscribe((types)=>{
      this.tipos = replaceKeyWithValue(types)
    })
    this.createForm();
  }

  createForm(){
    this.categoryForm = this.fb.group({
      'nombre': [
        this.category.nombre,
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      'descripcion': [
        this.category.descripcion,
        [
          Validators.maxLength(500)
        ]
      ]
    });


    this.categoryForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.categoryForm){
      return;
    }

    const form = this.categoryForm;
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

  putCategory(){
    this.categoryService.putCategory(this.category).subscribe((p)=>{
      this.category = p;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Categoría actualizada con éxito'});
      this.sent_form = false;
      this.editCategory();
      this.closeView();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  editCategory(){
    this.onCategoryEdited.emit(this.category);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.categoryForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.category.nombre = this.categoryForm.value.nombre;
      this.category.descripcion = this.categoryForm.value.descripcion;

      this.putCategory();
    }
  }

  closeView() {
    this.onModalClose.emit(false);
  }
}
