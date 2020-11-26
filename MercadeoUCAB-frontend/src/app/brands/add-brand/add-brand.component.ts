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
  selector: 'app-add-brand',
  templateUrl: './add-brand.component.html',
  styleUrls: ['./add-brand.component.scss'],
  providers: [MessageService]
})
export class AddBrandComponent implements OnInit {
  @Input() display: boolean;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onBrandAdded = new EventEmitter<any>();
  categorias: MenuItem[];
  subcategorias: MenuItem[];
  brand: Brand;
  sent_form: boolean = false;

  /* Form */
  brandForm: FormGroup;
  @ViewChild('sform') brandFormDirective;

  constructor(private fb: FormBuilder,
    private messageService: MessageService,
    private categoryService: CategoryService,
    private subcategoryService: SubcategoryService,
    private brandService: BrandService) { }

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
    this.brandForm = this.fb.group({
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

  getSubcategories(event){
    this.subcategoryService.getSubcategories(event.value).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
    })
  }

  postBrand(){
    this.brandService.postBrand(this.brand).subscribe((b)=>{
      this.brand = b;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Marca añadida con éxito'});
      this.sent_form = false;
      this.appendBrand();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  appendBrand(){
    this.onBrandAdded.emit(this.brand);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.brandForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.brand = new Brand();
      this.brand.nombre = this.brandForm.value.nombre;
      this.brand.id_categoria = this.brandForm.value.categoria;
      this.brand.id_subcategoria = this.brandForm.value.subcategoria;
      this.brand.descripcion = this.brandForm.value.descripcion;

      this.postBrand();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }


}
