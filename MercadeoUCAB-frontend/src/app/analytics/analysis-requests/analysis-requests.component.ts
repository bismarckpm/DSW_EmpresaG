import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { Request } from '../../classes/request';
import { CategoryService } from '../../services/category.service';
import { RequestsService } from '../../services/requests.service';
import { replaceKey } from '../../functions/common_functions';

//TODO: SHOW ONLY IN PROGRESS STUDIES

@Component({
  selector: 'app-analysis-requests',
  templateUrl: './analysis-requests.component.html',
  styleUrls: ['./analysis-requests.component.scss']
})
export class AnalysisRequestsComponent implements OnInit {
  estudios: Request[];
  estudiosErrorMessage: string;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  loading: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(private requestsService: RequestsService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    this.requestsService.getRequests().subscribe((studies) => {
      this.estudios = studies;
    }, errorMessage => {
      this.loading = false;
      this.estudiosErrorMessage = errorMessage;
    })

    this.categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKey(categories);
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.categoriasErrorMessage = errorMessage;
    })
  }

  onCategoryChange(event){
    this.table.filter(event.value, 'categoria', 'in')
  }

}
