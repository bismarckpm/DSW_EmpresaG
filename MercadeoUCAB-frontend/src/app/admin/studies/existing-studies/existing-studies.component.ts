import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { CategoryService } from '../../../services/admin/products/category.service';
import { StudiesService } from '../../../services/admin/studies/studies.service';
import { STUDY_STATES } from '../../../constants/study_states';
import { StudyWithFilter } from 'src/app/classes/study_with_filter';
import {replaceKeyWithValue} from '../../../functions/common_functions';

@Component({
  selector: 'app-existing-studies',
  templateUrl: './existing-studies.component.html',
  styleUrls: ['./existing-studies.component.scss']
})
export class ExistingStudiesComponent implements OnInit {
  estudios: StudyWithFilter[];
  estudiosErrorMessage: string;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  estados = STUDY_STATES;
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
