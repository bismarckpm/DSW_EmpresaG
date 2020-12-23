import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { CategoryService } from 'src/app/core/services/admin/products/category.service';
import { SubcategoryService } from 'src/app/core/services/admin/products/subcategory.service';
import { BrandService } from 'src/app/core/services/admin/products/brand.service';
import { Product_type } from 'src/app/core/classes/products/product_type';
import { TypesService } from 'src/app/core/services/admin/products/types.service';
import { BrandType } from 'src/app/core/classes/products/brand_type';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Brand } from 'src/app/core/classes/products/brand';


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
  marcas: any[];
  product_type: BrandType;
  sent_form = false;

  marcasErrorMessage: string;


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
    categoria: '',
    subcategoria: '',
    marca: '',
    nombre: '',
    descripcion: ''
  };

  validationMessages = {
    categoria: {
      required: 'Categoría es requerida'
    },
    subcategoria: {
      required: 'Subcategoría es requerida'
    },
    marca: {
      required: 'Marca es requerida'
    },
    nombre: {
      required: 'Nombre de marca es requerido',
      maxlength: 'Nombre de marca no puede exceder los 90 caracteres'
    },
    descripcion: {
      maxlength: 'Descripción no puede exceder los 500 caracteres'
    }
  };

  ngOnInit(): void {
    this.brandService.getALLBrands().subscribe((brands) => {
      this.marcas = [];
      for (let i = 0; i < brands.length; i++){
        this.marcas.push({
          value: brands[i].fkMarca._id,
          label: brands[i].fkMarca.nombre
        });
      }
    }, errorMessage => {
      this.marcasErrorMessage = errorMessage;
    });
    this.createForm();
  }

  createForm(){
    this.typeForm = this.fb.group({
      marca: [
        null,
        [
          Validators.required
        ]
      ],
      nombre: [
        '',
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      descripcion: [
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

  postType(){
    this.typeService.postType(this.product_type).subscribe((t) => {
      this.product_type = t;
      this.messageService.add({severity: 'success', summary: 'Éxito', detail: 'Marca añadida con éxito'});
      this.sent_form = false;
      this.appendType();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    });
  }

  appendType(){
    this.onTypeAdded.emit(this.product_type);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.typeForm.valid){
      this.sent_form = false;
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.product_type = new BrandType();
      this.product_type.fkTipo = new Product_type();
      this.product_type.fkTipo.nombre =  this.typeForm.value.nombre;
      this.product_type.fkTipo.descripcion = this.typeForm.value.descripcion;
      this.product_type.fkMarca = new Brand();
      this.product_type.fkMarca._id = this.typeForm.value.marca;
      this.product_type.fkMarca.nombre = this.marcas.find(o => o.value == this.product_type.fkMarca._id).label;
      this.postType();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }


}
