import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { BrandService } from 'src/app/core/services/admin/products/brand.service';
import { TypesService } from 'src/app/core/services/admin/products/types.service';
import { BrandType } from 'src/app/core/classes/products/brand_type';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-product-type',
  templateUrl: './edit-product-type.component.html',
  styleUrls: ['./edit-product-type.component.scss'],
  providers: [MessageService]
})
export class EditProductTypeComponent implements OnInit {
  @Input() display: boolean;
  @Input() product_type: BrandType;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onTypeEdited = new EventEmitter<any>();
  marcas: any[] = [];
  sent_form = false;

  marcasErrorMessage: string;

  /* Form */
  typeForm: FormGroup;
  @ViewChild('sform') typeFormDirective;

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
      required: 'Nombre de subcategoría es requerido',
      maxlength: 'Nombre de subcategoría no puede exceder los 90 caracteres'
    },
    descripcion: {
      maxlength: 'Descripción no puede exceder los 500 caracteres'
    }
  };


  constructor(private fb: FormBuilder,
              private messageService: MessageService,
              private brandService: BrandService,
              private typeService: TypesService) { }

  ngOnInit(): void {
    this.brandService.getALLBrands().subscribe((brands) => {
      for (let i = 0; i < brands.length; i++){
        this.marcas.push({
          value: brands[i].fkMarca._id,
          label: brands[i].fkMarca.nombre
        });
      }

      this.marcas = replaceKeyWithValue(this.marcas);
    }, errorMessage => {
      this.marcasErrorMessage = errorMessage;
    });
    this.createForm();
  }

  createForm(){
    this.typeForm = this.fb.group({
      marca: [
        this.product_type.fkMarca._id,
        [
          Validators.required
        ]
      ],
      nombre: [
        this.product_type.fkTipo.nombre,
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      descripcion: [
        this.product_type.fkTipo.descripcion,
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

  putType(){
    this.typeService.putType(this.product_type).subscribe((t) => {
      this.product_type = t;
      this.messageService.add({severity: 'success', summary: 'Éxito', detail: 'Subcategoría modificada con éxito'});
      this.sent_form = false;
      this.editType();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity: 'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    });
  }

  editType(){
    this.onTypeEdited.emit(this.product_type);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.typeForm.valid){
      this.sent_form = false;
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.product_type.fkTipo.nombre = this.typeForm.value.nombre;
      this.product_type.fkTipo.descripcion = this.typeForm.value.descripcion;
      this.product_type.fkMarca._id = this.typeForm.value.marca;
      this.product_type.fkMarca.nombre = this.marcas.find(o => o.value == this.product_type.fkMarca._id).label;
      this.putType();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }

}
