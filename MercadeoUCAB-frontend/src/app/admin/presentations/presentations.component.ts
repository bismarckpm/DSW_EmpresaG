import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table'
import { ConfirmationService, MessageService } from 'primeng/api';
import { PresentationService } from '../../core/services/admin/products/presentation.service';
import { TypePresentation } from '../../core/classes/products/type_presentation';

@Component({
  selector: 'app-presentations',
  templateUrl: './presentations.component.html',
  styleUrls: ['./presentations.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PresentationsComponent implements OnInit {
  loading: boolean = true;
  presentaciones: TypePresentation[];
  presentation: TypePresentation;
  presentationsErrorMessage: string;
  display_add_presentation: boolean = false;
  display_edit_presentation: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(private presentationService: PresentationService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.presentationService.getPresentations().subscribe((presentations) => {
      this.presentaciones = presentations;
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.presentationsErrorMessage = errorMessage;
    })
  }

  deletePresentation(presentation){
    this.confirmationService.confirm({
      message: 'La siguiente presentación: <code>' + presentation.fkPresentacion.nombre + '</code> de tipo <code>' + presentation.fkTipo.nombre + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.presentationService.deletePresentation(presentation).subscribe((p) => {

            let index = this.presentaciones.indexOf(presentation)
            if (index > -1)
              this.presentaciones.splice(index, 1);

            this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Presentación eliminada con éxito'});

          }, errorMessage => {
            this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
          })
      },
      reject: () => {
          //
      }
  });
}

showAddPresentationModal(){
  this.display_add_presentation = true;
  this.display_edit_presentation = false;
}

showEditPresentationModal(){
  this.display_edit_presentation = true;
  this.display_add_presentation = false;
}

hideAddPresentationModal(){
  this.display_add_presentation = false;
}

hideEditPresentationModal(){
  this.display_edit_presentation = false;
  this.presentation = null;
}

appendNewPresentation(event){
  this.presentaciones.push(event);
}

getSelectedPresentation(presentation){
  this.presentation = presentation;
}

editPresentation(event){
  this.presentaciones[this.presentaciones.indexOf(event)] = event;
  this.presentation = null;
}

}
