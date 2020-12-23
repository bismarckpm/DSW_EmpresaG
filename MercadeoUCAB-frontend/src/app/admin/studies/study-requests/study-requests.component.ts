import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { CategoryService } from '../../../core/services/admin/products/category.service';
import { RequestsService } from '../../../core/services/client/requests.service';
import { replaceKeyWithValue } from '../../../core/functions/common_functions';
import { RequestWithFilter } from 'src/app/core/classes/study/request_with_filter';

@Component({
  selector: 'app-study-requests',
  templateUrl: './study-requests.component.html',
  styleUrls: ['./study-requests.component.scss']
})
export class StudyRequestsComponent implements OnInit {
  solicitudes: RequestWithFilter[];
  solicitudesErrorMessage: string;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  loading: boolean = false;
  @ViewChild('dt') table: Table;
  constructor(private requestsService: RequestsService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    this.requestsService.getRequests().subscribe((requests) => {
      this.solicitudes = requests;
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.solicitudesErrorMessage = errorMessage;
    })

    this.categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKeyWithValue(categories);
    }, errorMessage => {
      this.categoriasErrorMessage = errorMessage;
    })
  }

  onCategoryChange(event){
    this.table.filter(event.value, 'fkCategoria._id', 'in')
  }

}
