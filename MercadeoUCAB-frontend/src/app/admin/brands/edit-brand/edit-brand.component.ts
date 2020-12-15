import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api';
import { Brand } from '../../../core/classes/products/brand';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { SubcategoryService } from 'src/app/core/services/admin/products/subcategory.service';
import { BrandService } from 'src/app/core/services/admin/products/brand.service';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SubcategoryBrand } from 'src/app/core/classes/products/subcategory_brand';
import { Subcategory } from 'src/app/core/classes/products/subcategory';

@Component({
  selector: 'app-edit-brand',
  templateUrl: './edit-brand.component.html',
  styleUrls: ['./edit-brand.component.scss'],
  providers: [MessageService]
})
export class EditBrandComponent implements OnInit {
  @Input() display: boolean;
  @Input() brand: SubcategoryBrand;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onBrandEdited = new EventEmitter<any>();
  subcategorias: any[];
  sent_form = false;

  /* Form */
  brandForm: FormGroup;
  @ViewChild('sform') brandFormDirective;

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
      required: 'Nombre de subcategoría es requerido',
      maxlength: 'Nombre de subcategoría no puede exceder los 90 caracteres'
    },
    descripcion: {
      maxlength: 'Descripción no puede exceder los 500 caracteres'
    }
  };


  constructor(private fb: FormBuilder,
              private messageService: MessageService,
              private subcategoryService: SubcategoryService,
              private brandService: BrandService) { }

  ngOnInit(): void {
    this.getSubcategories();
    this.createForm();
  }

  createForm(){
    this.brandForm = this.fb.group({
      subcategoria: [
        this.brand.fkSubcategoria._id,
        [
          Validators.required
        ]
      ],
      nombre: [
        this.brand.fkMarca.nombre,
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      descripcion: [
        this.brand.fkMarca.descripcion,
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
          _id: subcategories[i].fkSubcategoria._id,
          nombre: subcategories[i].fkSubcategoria.nombre
        });
      }

      this.subcategorias = replaceKeyWithValue(this.subcategorias);
    });
  }

  putBrand(){
    this.brandService.putBrand(this.brand).subscribe((b) => {
      this.messageService.add({severity: 'success', summary: 'Éxito', detail: 'Subcategoría modificada con éxito'});
      this.brand = b;
      this.sent_form = false;
      this.editSubcategory();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    });
  }

  editSubcategory(){
    this.onBrandEdited.emit(this.brand);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.brandForm.valid){
      this.sent_form = false;
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.brand.fkMarca.nombre = this.brandForm.value.nombre;
      this.brand.fkMarca.descripcion = this.brandForm.value.descripcion;
      this.brand.fkSubcategoria._id = this.brandForm.value.subcategoria;
      this.brand.fkSubcategoria.nombre = this.subcategorias.find(o => o.value == this.brand.fkSubcategoria._id).label;
      this.putBrand();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }


}
