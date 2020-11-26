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
  selector: 'app-add-product-type',
  templateUrl: './add-product-type.component.html',
  styleUrls: ['./add-product-type.component.scss'],
  providers: [MessageService]
})
export class AddProductTypeComponent implements OnInit {
  @Input() display: boolean;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onTypeAdded = new EventEmitter<any>();
  categorias: MenuItem[];
  subcategorias: MenuItem[];
  marcas: MenuItem[];
  product_type: ProductType
  sent_form: boolean = false;

  /* Form */
  typeForm: FormGroup;
  @ViewChild('tform') typeFormDirective;

  constructor(private fb: FormBuilder,
    private messageService: MessageService,
    private categoryService: CategoryService,
    private subcategoryService: SubcategoryService,
    private brandService: BrandService,
    private typeService: TypesService) { }

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
      'required': 'Nombre de marca es requerido',
      'maxlength': 'Nombre de marca no puede exceder los 90 caracteres'
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
    this.typeForm = this.fb.group({
      'categoria': [
        null,
        [
          Validators.required
        ]
      ],
      'subcategoria': [
        null,
        [
          Validators.required
        ]
      ],
      'marca': [
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

  getSubcategories(event){
    this.subcategoryService.getSubcategories(event.value).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
    })
  }

  getBrands(event){
    this.brandService.getBrands(event.value).subscribe((brands) => {
      this.marcas = replaceKeyWithValue(brands);
    })
  }

  postType(){
    console.log(this.product_type);
    this.typeService.postType(this.product_type).subscribe((t)=>{
      this.product_type = t;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Marca añadida con éxito'});
      this.sent_form = false;
      this.appendType();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  appendType(){
    this.onTypeAdded.emit(this.product_type);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.typeForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.product_type = new ProductType();
      this.product_type.nombre = this.typeForm.value.nombre;
      this.product_type.id_categoria = this.typeForm.value.categoria;
      this.product_type.id_subcategoria = this.typeForm.value.subcategoria;
      this.product_type.id_marca = this.typeForm.value.marca;
      this.product_type.descripcion = this.typeForm.value.descripcion;

      this.postType();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }


}
