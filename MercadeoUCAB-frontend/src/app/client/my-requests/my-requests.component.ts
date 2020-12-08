import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { StudyRequest } from '../../classes/study_request';
import { CategoryService } from '../../services/category.service';
import { RequestsService } from '../../services/requests.service';
import { replaceKey } from '../../functions/common_functions';
import { REQUEST_STATES } from 'src/app/constants/request_status';
import { Study } from 'src/app/classes/study';
import { StudiesService } from 'src/app/services/studies.service';

//TODO: Filter by current user
@Component({
  selector: 'app-my-requests',
  templateUrl: './my-requests.component.html',
  styleUrls: ['./my-requests.component.scss']
})
export class MyRequestsComponent implements OnInit {
  estudios: Study[];
  estudiosErrorMessage: string;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  request_states = REQUEST_STATES;
  current_state: number = 1;
  show_stats: boolean = false;
  loading: boolean = false;
  @ViewChild('dt') table: Table;
  constructor(private requestsService: RequestsService,
    private studiesService: StudiesService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    this.getRequests();

    this.categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKey(categories);
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.categoriasErrorMessage = errorMessage;
    })
  }

  onCategoryChange(event) {
    this.table.filter(event.value, 'categoria', 'in')
  }

  getRequests(){
    this.requestsService.getRequests().subscribe((studies) => {
      this.loading = false;
      //this.estudios = studies;
    }, errorMessage => {
      this.loading = false;
      this.estudiosErrorMessage = errorMessage;
    })
  }

  getInProgressStudies(){
    this.studiesService.getInProgressStudies().subscribe((studies) => {
      //this.estudios = studies;
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.estudiosErrorMessage = errorMessage;
    })
  }

  getFinishedStudies(){
    this.studiesService.getFinishedStudies().subscribe((studies) => {
      this.estudios = studies;
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.estudiosErrorMessage = errorMessage;
    })
  }

  filterStudies(event){
    this.loading = true;
    /* In progress */
    if (event.value == 1){
      this.show_stats = false;
      this.getRequests();
    }
    else if (event.value == 2){
      this.show_stats = false;
      this.getInProgressStudies();
    }
    else if (event.value == 3){
      this.show_stats = true;
      this.getFinishedStudies();
    }
  }

}
