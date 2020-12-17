import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { CategoryService } from 'src/app/core/services/admin/products/category.service';
import { SubcategoryService } from 'src/app/core/services/admin/products/subcategory.service';
import { BrandService } from 'src/app/core/services/admin/products/brand.service';
import { SubcategoryBrand } from 'src/app/core/classes/products/subcategory_brand';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Brand } from 'src/app/core/classes/products/brand';
import { Subcategory } from 'src/app/core/classes/products/subcategory';

@Component({
  selector: 'app-add-brand',
  templateUrl: './add-brand.component.html',
  styleUrls: ['./add-brand.component.scss'],
  providers: [MessageService]
})
export class AddBrandComponent implements OnInit {
  @Input() display: boolean;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onBrandAdded = new EventEmitter<any>();
  subcategorias: any[];
  brand: SubcategoryBrand;
  sent_form = false;

  subcategoriasErrorMessage: string;

  /* Form */
  brandForm: FormGroup;
  @ViewChild('sform') brandFormDirective;

  constructor(private fb: FormBuilder,
              private messageService: MessageService,
              private categoryService: CategoryService,
              private subcategoryService: SubcategoryService,
              private brandService: BrandService) { }

  formErrors = {
    subcategoria: '',
    nombre: '',
    descripcion: ''
  };

  validationMessages = {
    subcategoria: {
      required: 'Subcategoría es requerida'
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
    this.subcategoryService.getALLSubcategories().subscribe((subcategories) => {
      this.subcategorias = [];
      for (let i = 0; i < subcategories.length; i++){
        this.subcategorias.push({
          value: subcategories[i].fkSubcategoria._id,
          label: subcategories[i].fkSubcategoria.nombre
        });
      }
    }, errorMessage => {
      this.subcategoriasErrorMessage = errorMessage;
    });
    this.createForm();
  }

  createForm(){
    this.brandForm = this.fb.group({
      subcategoria: [
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

  getSubcategories(){
    this.subcategorias = null;
    this.subcategorias = [];
    this.subcategoryService.getALLSubcategories().subscribe((subcategories) => {
      for (let i = 0; i < subcategories.length; i++){
        this.subcategorias.push({
          value: subcategories[i].fkSubcategoria._id,
          label: subcategories[i].fkSubcategoria.nombre
        });
      }
    });
  }

  postBrand(){
    this.brandService.postBrand(this.brand).subscribe((b) => {
      this.brand = b;
      this.messageService.add({severity: 'success', summary: 'Éxito', detail: 'Marca añadida con éxito'});
      this.sent_form = false;
      this.appendBrand();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    });
  }

  appendBrand(){
    this.onBrandAdded.emit(this.brand);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.brandForm.valid){
      this.sent_form = false;
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.brand = new SubcategoryBrand();
      this.brand.fkMarca = new Brand();
      this.brand.fkMarca.nombre = this.brandForm.value.nombre;
      this.brand.fkMarca.descripcion = this.brandForm.value.descripcion;
      this.brand.fkSubcategoria = new Subcategory();
      this.brand.fkSubcategoria._id = this.brandForm.value.subcategoria;
      this.brand.fkSubcategoria.nombre = this.subcategorias.find(o => o.value == this.brand.fkSubcategoria._id).label;

      this.postBrand();
    }

  }

  closeModal() {
    this.onModalClose.emit(false);
  }


}
