import { Component, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { CategoryService } from '../../services/admin/products/category.service';
import { replaceKeyWithValue } from '../../functions/common_functions';
import { STUDY_STATES } from '../../constants/study_states'
import { UserSurveyService } from 'src/app/services/surveys/user-survey.service';
import { StudyWithFilter } from 'src/app/classes/study_with_filter';

@Component({
  selector: 'app-available-surveys',
  templateUrl: './available-surveys.component.html',
  styleUrls: ['./available-surveys.component.scss']
})
export class AvailableSurveysComponent implements OnInit {
  current_user: number = 70;
  estudios: StudyWithFilter[];
  estudiosErrorMessage: string;
  categorias: MenuItem[];
  categoriasErrorMessage: string;
  estados: MenuItem[] = STUDY_STATES;
  loading: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(private surveyService: UserSurveyService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loading = true;
    // TODO: Get current user
    this.surveyService.getAvailableSurveys(this.current_user).subscribe((studies) => {
      this.estudios = studies;
    }, errorMessage => {
      this.loading = false;
      this.estudiosErrorMessage = errorMessage;
    })

    this.categoryService.getCategories().subscribe((categories) => {
      this.categorias = replaceKeyWithValue(categories);
      this.loading = false;
    }, errorMessage => {
      this.loading = false;
      this.categoriasErrorMessage = errorMessage;
    })
  }


  onCategoryChange(event){
    this.table.filter(event.value, 'fkCategoria._id', 'in')
  }
}
