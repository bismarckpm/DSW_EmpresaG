import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table'
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { Brand } from '../classes/brand';
import { BrandService } from '../services/brand.service';
import { SubcategoryService } from '../services/subcategory.service';
import { CategoryService } from '../services/category.service';
import { replaceKeyWithValue } from '../functions/common_functions';
import { SubcategoryBrand } from '../classes/subcategory_brand';

@Component({
  selector: 'app-brands',
  templateUrl: './brands.component.html',
  styleUrls: ['./brands.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class BrandsComponent implements OnInit {
  loading: boolean = true;
  marcas: SubcategoryBrand[];
  backup_brands: SubcategoryBrand[];
  subcategorias: MenuItem[];
  categorias: MenuItem[];
  marca: SubcategoryBrand;
  display_add_brand: boolean = false;
  display_edit_brand: boolean = false;
  @ViewChild('dt') table: Table;

  marcasErrorMessage: string;
  subcategoriasErrorMessage: string;
  categoriasErrorMessage: string;
  constructor(
    private brandService: BrandService,
    private subcategoryService: SubcategoryService,
    private categoryService: CategoryService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.brandService.getALLBrands().subscribe((brands) => {
      this.marcas = brands;
      this.backup_brands = brands;
      this.categoryService.getCategories().subscribe((categorias) => {
        this.categorias = replaceKeyWithValue(categorias);
        this.loading = false;
      }, errorMessage => {
        this.loading = false;
        this.categoriasErrorMessage = errorMessage;
      })

    }, errorMessage => {
      this.loading = false;
      this.marcasErrorMessage = errorMessage;
    })
  }

  deleteMarca(marca) {
    this.confirmationService.confirm({
      message: 'La siguiente marca: <code>' + marca.fkMarca.nombre + '</code> de ID <code>' + marca._id + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.brandService.deleteBrand(marca).subscribe((b) => {

          let index = this.marcas.indexOf(marca)
          if (index > -1)
            this.marcas.splice(index, 1);

          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Marca eliminada con éxito' });

        }, errorMessage => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        })
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
    })
  }
  
  onCategoryChange(event){
    this.marcas = this.backup_brands;
    this.marcas = this.marcas.filter(marca => marca.fkMarca._id == event.value)
    this.getSubcategories(event.value)
  }

  onSubcategoryChange(event){
    this.table.filter(event.value, 'fkSubcategoria.id_subcategoria', 'in')
  }

}
