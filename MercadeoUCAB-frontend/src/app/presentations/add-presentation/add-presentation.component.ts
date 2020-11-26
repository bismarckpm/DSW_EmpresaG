import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api'
import { Presentation } from '../../classes/presentation';
import { TypesService } from '../../services/types.service';
import { PresentationService } from 'src/app/services/presentation.service';
import { replaceKeyWithValue } from '../../functions/common_functions';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-presentation',
  templateUrl: './add-presentation.component.html',
  styleUrls: ['./add-presentation.component.scss'],
  providers: [MessageService]
})
export class AddPresentationComponent implements OnInit {
  @Input() display: boolean;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onPresentationAdded = new EventEmitter<any>();
  tipos: MenuItem[];
  presentation: Presentation;
  sent_form: boolean = false;

  /* Form */
  presentationForm: FormGroup;
  @ViewChild('pform') presentationFormDirective;

  constructor(private fb: FormBuilder,
    private presentationService: PresentationService,
    private messageService: MessageService,
    private typesService: TypesService) { }

  formErrors = {
    'nombre': '',
    'descripcion': ''
  };

  validationMessages = {
    'nombre': {
      'required': 'Nombre de presentación es requerido',
      'maxlength': 'Nombre de presentación no puede exceder los 90 caracteres'
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
    this.presentationForm = this.fb.group({
      'tipo': null,
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
    

    this.presentationForm.valueChanges
    .subscribe(data => {
      this.onValueChange(data);
    });
  }

  onValueChange(data?: any){
    /* If form hasn't been created */
    if (!this.presentationForm){
      return;
    }

    const form = this.presentationForm;
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

  postPresentation(){
    this.presentationService.postPresentation(this.presentation).subscribe((p)=>{
      this.presentation = p;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Presentación añadida con éxito'});
      this.sent_form = false;
      this.appendPresentation();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  appendPresentation(){
    this.onPresentationAdded.emit(this.presentation);
  }

  onSubmit(){
    this.sent_form = true;
    if (!this.presentationForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.presentation = new Presentation();
      this.presentation.nombre = this.presentationForm.value.nombre;
      this.presentation.id_tipo = this.presentationForm.value.tipo;
      this.presentation.descripcion = this.presentationForm.value.descripcion;

      this.postPresentation();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }

}
