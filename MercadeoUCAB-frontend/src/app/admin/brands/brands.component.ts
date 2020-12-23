import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table';
import { ConfirmationService, MessageService } from 'primeng/api';
import { BrandService } from '../../core/services/admin/products/brand.service';
import { SubcategoryService } from '../../core/services/admin/products/subcategory.service';
import { replaceKeyWithValue } from '../../core/functions/common_functions';
import { SubcategoryBrand } from '../../core/classes/products/subcategory_brand';

@Component({
  selector: 'app-brands',
  templateUrl: './brands.component.html',
  styleUrls: ['./brands.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class BrandsComponent implements OnInit {
  loading = true;
  marcas: SubcategoryBrand[];
  backup_brands: SubcategoryBrand[];
  subcategorias: any[];
  marca: SubcategoryBrand;
  display_add_brand = false;
  display_edit_brand = false;
  @ViewChild('dt') table: Table;

  marcasErrorMessage: string;
  subcategoriasErrorMessage: string;

  constructor(
    private brandService: BrandService,
    private subcategoryService: SubcategoryService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.brandService.getALLBrands().subscribe((brands) => {
      this.marcas = brands;
      this.backup_brands = brands;
      this.subcategorias = [];
      this.subcategoryService.getALLSubcategories().subscribe((subcategories) => {
        this.loading = false;
        for (let i = 0; i < subcategories.length; i++){
          this.subcategorias.push({
            value: subcategories[i].fkSubcategoria._id,
            label: subcategories[i].fkSubcategoria.nombre
          });
        }
      }, errorMessage => {
        this.loading = false;
        this.subcategoriasErrorMessage = errorMessage;
      });

    }, errorMessage => {
      this.loading = false;
      this.marcasErrorMessage = errorMessage;
    });
  }

  deleteMarca(marca) {
    this.confirmationService.confirm({
      message: 'La siguiente marca: <code>' + marca.fkMarca.nombre + '</code> de ID <code>' + marca._id + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.brandService.deleteBrand(marca).subscribe((b) => {

          const index = this.marcas.indexOf(marca);
          if (index > -1) {
            this.marcas.splice(index, 1);
          }

          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Marca eliminada con éxito' });

        }, errorMessage => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        });
      },
      reject: () => {
        //
      }
    });
  }

  showAddBrandModal(){
    this.display_add_brand = true;
    this.display_edit_brand = false;
  }

  showEditBrandModal(){
    this.display_edit_brand = true;
    this.display_add_brand = false;
  }

  hideAddBrandModal(){
    this.display_add_brand = false;
  }

  hideEditBrandModal(){
    this.display_edit_brand = false;
    this.marca = null;
  }

  appendNewBrand(event){
    this.marcas.push(event);
  }

  getSelectedBrand(brand){
    this.marca = brand;
  }

  editBrand(event){
    this.marcas[this.marcas.indexOf(event)] = event;
  }

  getSubcategories(category_id){
    this.subcategoryService.getSubcategories(category_id).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
    });
  }

  onSubcategoryChange(event){
    this.table.filter(event.value, 'fkSubcategoria._id', 'in');
  }

}
