import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, MenuItem } from 'primeng/api'
import { Presentation } from '../../classes/presentation';
import { TypesService } from '../../services/types.service';
import { PresentationService } from 'src/app/services/presentation.service';
import { TypePresentation } from 'src/app/classes/type_presentation';
import { replaceKeyWithValue } from '../../functions/common_functions';

/* Form */
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-presentation',
  templateUrl: './edit-presentation.component.html',
  styleUrls: ['./edit-presentation.component.scss'],
  providers: [MessageService]
})
export class EditPresentationComponent implements OnInit {
  @Input() display: boolean;
  @Input() presentation: TypePresentation;
  @Output() onModalClose = new EventEmitter<any>();
  @Output() onPresentationEdited = new EventEmitter<any>();
  tipos: any[];
  sent_form: boolean = false;

  tiposErrorMessage: string;

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
    this.typesService.getALLTypes().subscribe((types) => {
      this.tipos = [];
      for (var i = 0; i<types.length; i++){
        this.tipos.push({
          value: types[i].fkTipo._id,
          label: types[i].fkTipo.nombre
        })
      }

      this.tipos = replaceKeyWithValue(this.tipos);

    }, errorMessage => {
      this.tiposErrorMessage = errorMessage;
    })
    this.createForm();
  }

  createForm(){
    this.presentationForm = this.fb.group({
      'tipo': this.presentation.fkTipo._id,
      'nombre': [
        this.presentation.fkPresentacion.nombre,
        [
          Validators.required,
          Validators.maxLength(90)
        ]
      ],
      'descripcion': [
        this.presentation.fkPresentacion.descripcion,
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

  putPresentation(){
    this.presentationService.putPresentation(this.presentation).subscribe((p)=>{
      this.presentation = p;
      this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Presentación actualizada con éxito'});
      this.sent_form = false;
      this.editPresentation();
      this.closeModal();
    }, errorMessage => {
      this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
      this.sent_form = false;
    })
  }

  editPresentation(){
    this.onPresentationEdited.emit(this.presentation);
  }


  onSubmit(){
    this.sent_form = true;
    if (!this.presentationForm.valid){
      this.sent_form = false;
      this.messageService.add({severity:'error', summary: 'Error', detail: 'Debe rellenar los campos requeridos con datos válidos'});
    }
    else {
      this.presentation.fkPresentacion.nombre = this.presentationForm.value.nombre;
      this.presentation.fkPresentacion.descripcion = this.presentationForm.value.descripcion;
      this.presentation.fkTipo._id = this.presentationForm.value.tipo;
      this.presentation.fkTipo.nombre = this.tipos.find(o => o.value == this.presentation.fkTipo._id).label;

      this.putPresentation();
    }
  }

  closeModal() {
    this.onModalClose.emit(false);
  }
}
