import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { CategoryService } from '../../core/services/admin/products/category.service';
import { StudiesService } from '../../core/services/admin/studies/studies.service';
import { replaceKeyWithValue } from '../../core/functions/common_functions';
import { STUDY_STATES } from '../../core/constants/study_states';
import { StudyWithFilter } from 'src/app/core/classes/study/study_with_filter';

@Component({
  selector: 'app-analysis-requests',
  templateUrl: './analysis-requests.component.html',
  styleUrls: ['./analysis-requests.component.scss']
})
export class AnalysisRequestsComponent implements OnInit {
  estudios: StudyWithFilter[];
  estudiosErrorMessage: string;
  categorias: MenuItem[];
  estados: MenuItem[] = STUDY_STATES;
  categoriasErrorMessage: string;
  loading = false;
  @ViewChild('dt') table: Table;

  constructor(private studiesService: StudiesService,
              private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    this.studiesService.getStudies().subscribe((studies) => {
      this.estudios = studies;
    }, errorMessage => {
      this.loading = false;
      this.estudiosErrorMessage = errorMessage;
    });

    this.categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKeyWithValue(categories);
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.categoriasErrorMessage = errorMessage;
    });

  }

  onCategoryChange(event){
    this.table.filter(event.value, 'fkCategoria._id', 'in');
  }

  onStateChange(event){
    this.table.filter(event.value, 'fkEstudio.estado', 'in');
  }
}
