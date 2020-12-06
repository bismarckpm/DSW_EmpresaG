import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { Study } from '../../classes/study';
import { CategoryService } from '../../services/category.service';
import { StudiesService } from '../../services/studies.service';
import { replaceKey } from '../../functions/common_functions';
import { STUDY_STATES } from '../../constants/study_states'

@Component({
  selector: 'app-available-surveys',
  templateUrl: './available-surveys.component.html',
  styleUrls: ['./available-surveys.component.scss']
})
export class AvailableSurveysComponent implements OnInit {
  estudios: Study[];
  estudiosErrorMessage: string;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  estados: MenuItem[] = STUDY_STATES;
  loading: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(private studiesService: StudiesService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    // TODO: Get on progress studies that haven't been taken before
    this.studiesService.getStudies().subscribe((studies) => {
      /*this.estudios = studies.map(
        function (obj){
          return Object.assign(obj, 
            {
              n_preguntas: obj.preguntas.length, 
              tiempo_estimado: obj.preguntas.length + (Math.floor(Math.random()*10)+1)
            })});*/
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
