import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { CategoryService } from '../../services/category.service';
import { StudiesService } from '../../services/studies.service';
import { replaceKey } from '../../functions/common_functions';
import { STUDY_STATES } from '../../constants/study_states'
import { Study } from 'src/app/classes/study';

@Component({
  selector: 'app-analysis-requests',
  templateUrl: './analysis-requests.component.html',
  styleUrls: ['./analysis-requests.component.scss']
})
export class AnalysisRequestsComponent implements OnInit {
  estudios: Study[];
  estudiosErrorMessage: string;
  categorias: MenuItem[];
  estados: MenuItem[] = STUDY_STATES;
  categoriasErrorMessage: string;
  loading: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(private studiesService: StudiesService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    this.studiesService.getStudies().subscribe((studies) => {
      //this.estudios = studies;
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

  onStateChange(event){
    this.table.filter(event.value, 'estado', 'in')
  }
}
