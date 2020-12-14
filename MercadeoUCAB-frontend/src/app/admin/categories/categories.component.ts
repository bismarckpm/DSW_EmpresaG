import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table'
import { ConfirmationService, MessageService } from 'primeng/api';
import { Category } from '../../core/classes/products/category';
import { CategoryService } from '../../core/services/admin/products/category.service';
// import { removeUnderscore } from '../functions/common_functions';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
  providers: [MessageService, ConfirmationService]
})

export class CategoriesComponent implements OnInit {
  loading: boolean = true;
  categories: Category[];
  category: Category;
  categoriesErrorMessage: string;
  addCategoryView: boolean = false;
  editCategoryView: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(private categoryService: CategoryService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((Ecategories) => {
      this.categories = Ecategories;
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.categoriesErrorMessage = errorMessage;
    })
  }

  deleteCategory(category){
    this.confirmationService.confirm({
      message: 'La siguiente categoría: <code>' + category.nombre + '</code> está apunto de ser eliminada, ¿Desea continuar?',
      header: 'Confirmación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.categoryService.deleteCategory(category).subscribe((p) => {

            let index = this.categories.indexOf(category)
            if (index > -1)
              this.categories.splice(index, 1);

            this.messageService.add({severity:'success', summary: 'Éxito', detail: 'Categoría eliminada con éxito'});

          }, errorMessage => {
            this.messageService.add({severity:'error', summary: 'Error', detail: errorMessage});
          })
      },
      reject: () => {
        //
      }
    });
  }

  showAddCategoryView(){
    this.addCategoryView = true;
    this.editCategoryView = false;
  }

  showEditCategoryView(){
    this.editCategoryView = true;
    this.addCategoryView = false;
  }

  hideAddCategoryView(){
    this.addCategoryView = false;
  }

  hideEditCategoryView(){
    this.editCategoryView = false;
    this.category = null;
  }

  appendNewCategory(event){
    this.categories.push(event);
  }

  getSelectedCategory(category){
    this.category = category;
  }

  editCategory(event){
    this.categories[this.categories.indexOf(event)] = event;
    this.category = null;
  }
}
