import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { ConfirmationService, MessageService } from 'primeng/api';
import { BrandService } from '../../core/services/admin/products/brand.service';
import { TypesService } from '../../core/services/admin/products/types.service';
import { BrandType } from '../../core/classes/products/brand_type';

@Component({
  selector: 'app-product-types',
  templateUrl: './product-types.component.html',
  styleUrls: ['./product-types.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class ProductTypesComponent implements OnInit {
  loading = true;
  marcas: any[];
  backup_types: BrandType[];
  tipos: BrandType[];
  tipo: BrandType;
  display_add_type = false;
  display_edit_type = false;
  @ViewChild('dt') table: Table;

  tiposErrorMessage: string;
  marcasErrorMessage: string;

  constructor(
    private typesService: TypesService,
    private brandService: BrandService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.typesService.getALLTypes().subscribe((types) => {
      this.tipos = types;
      this.backup_types = types;
      this.marcas = [];
      this.brandService.getALLBrands().subscribe((brands) => {
        for (let i = 0; i < brands.length; i++){
          this.marcas.push({
            value: brands[i].fkMarca._id,
            label: brands[i].fkMarca.nombre
          });
        }
        this.loading = false;
      }, errorMessage => {
        this.loading = false;
        this.marcasErrorMessage = errorMessage;
      });

    }, errorMessage => {
      this.loading = false;
      this.tiposErrorMessage = errorMessage;
    });
  }

  deleteType(tipo) {
    this.confirmationService.confirm({
      message: 'El siguiente tipo de producto: <code>' + tipo.fkTipo.nombre + '</code> de marca <code>' + tipo.fkMarca.nombre + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.typesService.deleteType(tipo).subscribe((t) => {

          const index = this.tipos.indexOf(tipo);
          if (index > -1) {
            this.tipos.splice(index, 1);
          }

          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Tipo de producto eliminado con éxito' });

        }, errorMessage => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        });
      },
      reject: () => {
        //
      }
    });
  }

  showAddTypeModal(){
    this.display_add_type = true;
    this.display_edit_type = false;
  }

  showEditTypeModal(){
    this.display_edit_type = true;
    this.display_add_type = false;
  }

  hideAddTypeModal(){
    this.display_add_type = false;
  }

  hideEditTypeModal(){
    this.display_edit_type = false;
    this.tipo = null;
  }

  appendNewType(event){
    this.tipos.push(event);
  }

  getSelectedType(brand){
    this.tipo = brand;
  }

  editType(event){
    this.tipos[this.tipos.indexOf(event)] = event;
  }

  onBrandChange(event){
    this.table.filter(event.value, 'fkMarca._id', 'in');
  }

}
