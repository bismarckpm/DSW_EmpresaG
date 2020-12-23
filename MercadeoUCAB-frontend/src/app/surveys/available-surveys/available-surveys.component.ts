import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { CategoryService } from '../../core/services/admin/products/category.service';
import { replaceKeyWithValue } from '../../core/functions/common_functions';
import { STUDY_STATES } from '../../core/constants/study_states';
import { UserSurveyService } from 'src/app/core/services/surveys/user-survey.service';
import { StudyWithFilter } from 'src/app/core/classes/study/study_with_filter';
import {SessionService} from '../../core/services/auth/session.service';

@Component({
  selector: 'app-available-surveys',
  templateUrl: './available-surveys.component.html',
  styleUrls: ['./available-surveys.component.scss']
})
export class AvailableSurveysComponent implements OnInit {
  current_user: number;
  estudios: StudyWithFilter[];
  estudiosErrorMessage: string;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  estados: MenuItem[] = STUDY_STATES;
  loading = false;
  @ViewChild('dt') table: Table;

  constructor(private surveyService: UserSurveyService,
              private sessionService: SessionService,
              private categoryService: CategoryService) {
    this.current_user = this.sessionService.getCurrentUser();
  }

  ngOnInit(): void {
    this.loading = true;
    this.surveyService.getAvailableSurveys(this.current_user).subscribe((studies) => {
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
}
