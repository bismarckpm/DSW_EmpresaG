import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table'
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { Subcategory } from '../../core/classes/products/subcategory';
import { SubcategoryService } from '../../core/services/admin/products/subcategory.service';
import { CategoryService } from '../../core/services/admin/products/category.service';
import { replaceKeyWithValue } from '../../core/functions/common_functions';
import { CategorySubcategory } from '../../core/classes/products/category_subcategory';

@Component({
  selector: 'app-subcategories',
  templateUrl: './subcategories.component.html',
  styleUrls: ['./subcategories.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class SubcategoriesComponent implements OnInit {
  loading: boolean = true;
  subcategorias: CategorySubcategory[];
  categorias: MenuItem[];
  subcategoria: Subcategory;
  display_add_subcategory: boolean = false;
  display_edit_subcategory: boolean = false;
  @ViewChild('dt') table: Table;


  subcategoriasErrorMessage: string;
  categoriasErrorMessage: string;

  constructor(private subcategoryService: SubcategoryService,
    private categoryService: CategoryService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) {

  }

  ngOnInit(): void {
    this.subcategoryService.getALLSubcategories().subscribe((subcategories) => {
      this.subcategorias = subcategories;
      this.loading = false;

      this.categoryService.getCategories().subscribe((categorias) => {
        this.categorias = replaceKeyWithValue(categorias);
      }, errorMessage => {
        this.loading = false;
        this.categoriasErrorMessage = errorMessage;
      })
    }, errorMessage => {
      this.loading = false;
      this.subcategoriasErrorMessage = errorMessage;
    })
  }

  deleteSubcategoria(subcategoria){
    this.confirmationService.confirm({
      message: 'La siguiente subcategoría: <code>' + subcategoria.fkSubcategoria.nombre + '</code> de ID <code>' + subcategoria.fkSubcategoria._id + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.subcategoryService.deleteSubcategory(subcategoria).subscribe((s) => {

            let index = this.subcategorias.indexOf(subcategoria)
            if (index > -1)
              this.subcategorias.splice(index, 1);

            this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Subcategoría eliminada con éxito'});

          }, errorMessage => {
            this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
          })
      },
      reject: () => {
          //
      }
  });
}

showAddSubcategoryModal(){
  this.display_add_subcategory = true;
  this.display_edit_subcategory = false;
}

showEditSubcategoryModal(){
  this.display_edit_subcategory = true;
  this.display_add_subcategory = false;
}

hideAddSubcategoryModal(){
  this.display_add_subcategory = false;
}

hideEditSubcategoryModal(){
  this.display_edit_subcategory = false;
  this.subcategoria = null;
}

appendNewSubcategory(event){
  this.subcategorias.push(event);
}

getSelectedSubcategory(subcategory){
  this.subcategoria = subcategory;
}

editSubcategory(event){
  this.subcategorias[this.subcategorias.indexOf(event)] = event;
}

onCategoryChange(event){
  this.table.filter(event.value, 'fkCategoria._id', 'in')
}

}
