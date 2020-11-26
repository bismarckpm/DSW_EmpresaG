import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table'
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { Brand } from '../classes/brand';
import { BrandService } from '../services/brand.service';
import { SubcategoryService } from '../services/subcategory.service';
import { CategoryService } from '../services/category.service';
import { replaceKeyWithValue } from '../functions/common_functions';
import { ProductType } from '../classes/productType';
import { TypesService } from '../services/types.service';

@Component({
  selector: 'app-product-types',
  templateUrl: './product-types.component.html',
  styleUrls: ['./product-types.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class ProductTypesComponent implements OnInit {
  loading: boolean = true;
  subcategorias: MenuItem[];
  categorias: MenuItem[];
  marcas: MenuItem[];
  backup_types: ProductType[];
  tipos: ProductType[];
  tipo: ProductType;
  display_add_type: boolean = false;
  display_edit_type: boolean = false;
  @ViewChild('dt') table: Table;

  tiposErrorMessage: string;
  marcasErrorMessage: string;
  subcategoriasErrorMessage: string;
  categoriasErrorMessage: string;
  constructor(
    private typesService: TypesService,
    private brandService: BrandService,
    private subcategoryService: SubcategoryService,
    private categoryService: CategoryService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.typesService.getALLTypes().subscribe((types) => {
      this.tipos = types;
      this.backup_types = types;
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

  deleteType(tipo) {
    this.confirmationService.confirm({
      message: 'El siguiente tipo de producto: <code>' + tipo.nombre + '</code> de marca <code>' + tipo.marca + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.typesService.deleteType(tipo).subscribe((t) => {

          let index = this.tipos.indexOf(tipo)
          if (index > -1)
            this.tipos.splice(index, 1);

          this.messageService.add({ severity: 'success', summary: 'Éxito', detail: 'Tipo de producto eliminado con éxito' });

        }, errorMessage => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMessage });
        })
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

  getSubcategories(category_id){
    this.subcategoryService.getSubcategories(category_id).subscribe((subcategories) => {
      this.subcategorias = replaceKeyWithValue(subcategories);
    })
  }

  getBrands(subcategory_id){
    this.brandService.getBrands(subcategory_id).subscribe((brands) => {
      this.marcas = replaceKeyWithValue(brands);
    })
  }
  
  onCategoryChange(event){
    this.tipos = this.backup_types;
    this.tipos = this.tipos.filter(tipo => tipo.id_categoria == event.value)
    this.getSubcategories(event.value)
  }

  onSubcategoryChange(event){
    this.tipos = this.backup_types;
    this.tipos = this.tipos.filter(tipo => tipo.id_subcategoria == event.value)
    this.getBrands(event.value)
  }

  onBrandChange(event){
    this.table.filter(event.value, 'id_marca', 'in')
  }

}
