import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api'
import { Brand } from '../../classes/brand';
import { replaceKeyWithValue } from '../../functions/common_functions';
import { CategoryService } from 'src/app/services/category.service';
import { SubcategoryService } from 'src/app/services/subcategory.service';
import { BrandService } from 'src/app/services/brand.service';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-brand',
  templateUrl: './edit-brand.component.html',
  styleUrls: ['./edit-brand.component.scss'],
  providers: [MessageService]
})
export class EditBrandComponent implements OnInit {
  @Input() display: boolean;
  @Input() brand: Brand;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onBrandEdited = new EventEmitter<any>();
  tipos: MenuItem[];
  categorias: MenuItem[];
  subcategorias: MenuItem[];
  sent_form: boolean = false;

  /* Form */
  brandForm: FormGroup;
  @ViewChild('sform') brandFormDirective;

  formErrors = {
    'categoria': '',
    'subcategoria': '',
    'nombre': '',
    'descripcion': ''
  };

  validationMessages = {
    'categoria': {
      'required': 'Categoría es requerida'
    },
    'subcategoria': {
      'required': 'Subcategoría es requerida'
    },
    'nombre': {
      'required': 'Nombre de subcategoría es requerido',
      'maxlength': 'Nombre de subcategoría no puede exceder los 90 caracteres'
    },
    'descripcion': {
      'maxlength': 'Descripción no puede exceder los 500 caracteres'
    }
  }


  constructor(private fb: FormBuilder,
    private messageService: MessageService,
    private categoryService: CategoryService,
    private subcategoryService: SubcategoryService,
    private brandService: BrandService) { }
  
  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((category) => {
      this.categorias = replaceKeyWithValue(category);
      this.getSubcategories(this.brand.id_categoria);
    })
    this.createForm();
    this.brandForm.patchValue({
      'subcategoria': this.brand.id_subcategoria
    })
  }

  createForm(){
    this.brandForm = this.fb.group({
      'categoria': [
        this.brand.id_categoria,
        [
          Validators.required
        ]
      ],
      'subcategoria': [
        this.brand.id_subcategoria,
        [
          Validators.required
        ]
      ],
      'nombre': [
        this.brand.nombre,
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      'descripcion': [
        this.brand.descripcion,
        [
          Validators.maxLength(500)
        ]
      ]
    });
    

    this.brandForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.brandForm){
      return;
    }

    const form = this.brandForm;
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

  getSubcategories(category_id){
    this.subcategoryService.getSubcategories(category_id).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
    })
  }

  putBrand(){
    this.brandService.putBrand(this.brand).subscribe((b)=>{
      this.brand = b;
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
    this.onBrandEdited.emit(this.brand);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.brandForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.brand.nombre = this.brandForm.value.nombre;
      this.brand.id_categoria = this.brandForm.value.categoria;
      this.brand.id_subcategoria = this.brandForm.value.subcategoria;
      this.brand.descripcion = this.brandForm.value.descripcion;

      this.putBrand();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }


}
