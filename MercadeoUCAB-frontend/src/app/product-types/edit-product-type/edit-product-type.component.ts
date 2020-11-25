import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api'
import { Brand } from '../../classes/brand';
import { replaceKeyWithValue } from '../../functions/common_functions';
import { CategoryService } from 'src/app/services/category.service';
import { SubcategoryService } from 'src/app/services/subcategory.service';
import { BrandService } from 'src/app/services/brand.service';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductType } from 'src/app/classes/productType';
import { TypesService } from 'src/app/services/types.service';

@Component({
  selector: 'app-edit-product-type',
  templateUrl: './edit-product-type.component.html',
  styleUrls: ['./edit-product-type.component.scss'],
  providers: [MessageService]
})
export class EditProductTypeComponent implements OnInit {
  @Input() display: boolean;
  @Input() product_type: ProductType;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onTypeEdited = new EventEmitter<any>();
  marcas: MenuItem[];
  categorias: MenuItem[];
  subcategorias: MenuItem[];
  sent_form: boolean = false;

  /* Form */
  typeForm: FormGroup;
  @ViewChild('sform') typeFormDirective;

  formErrors = {
    'categoria': '',
    'subcategoria': '',
    'marca': '',
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
    'marca': {
      'required': 'Marca es requerida'
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
    private brandService: BrandService,
    private typeService: TypesService) { }
  
  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((category) => {
      this.categorias = replaceKeyWithValue(category);
      this.getSubcategories(this.product_type.id_categoria);
      this.getBrands(this.product_type.id_subcategoria)
    })
    this.createForm();
    this.typeForm.patchValue({
      'subcategoria': this.product_type.id_subcategoria,
      'marca': this.product_type.id_marca
    })
  }

  createForm(){
    this.typeForm = this.fb.group({
      'categoria': [
        this.product_type.id_categoria,
        [
          Validators.required
        ]
      ],
      'subcategoria': [
        this.product_type.id_subcategoria,
        [
          Validators.required
        ]
      ],
      'marca': [
        this.product_type.id_marca,
        [
          Validators.required
        ]
      ],
      'nombre': [
        this.product_type.nombre,
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      'descripcion': [
        this.product_type.descripcion,
        [
          Validators.maxLength(500)
        ]
      ]
    });
    

    this.typeForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.typeForm){
      return;
    }

    const form = this.typeForm;
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

  getBrands(subcategory_id){
    this.brandService.getBrands(subcategory_id).subscribe((brands) => {
      this.marcas = replaceKeyWithValue(brands);
    })
  }

  putType(){
    this.typeService.putType(this.product_type).subscribe((t)=>{
      this.product_type = t;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Subcategoría modificada con éxito'});
      this.sent_form = false;
      this.editType();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  editType(){
    this.onTypeEdited.emit(this.product_type);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.typeForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.product_type.nombre = this.typeForm.value.nombre;
      this.product_type.id_categoria = this.typeForm.value.categoria;
      this.product_type.id_subcategoria = this.typeForm.value.subcategoria;
      this.product_type.id_marca = this.typeForm.value.marca;
      this.product_type.descripcion = this.typeForm.value.descripcion;

      this.putType();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }

}
