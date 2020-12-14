import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Table } from 'primeng/table';
import { persondata } from 'src/app/core/classes/profile/persondata';
import { Study } from 'src/app/core/classes/study/study';
import { AnalystService } from 'src/app/core/services/analytics/analyst.service';

@Component({
  selector: 'app-available-population',
  templateUrl: './available-population.component.html',
  styleUrls: ['./available-population.component.scss']
})
export class AvailablePopulationComponent implements OnInit {
  people: persondata[];
  estudio: Study;
  current_study: number;
  loading: boolean = false;
  peopleErrorMessage: boolean = false;
  @ViewChild('dt') table: Table;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private analystService: AnalystService,
    private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
    this.loading = true;
    this.spinner.show();
    if ((this.activatedRoute.snapshot.queryParamMap.get('studyId') || 0) == 0) {
      this.router.navigate(['404']);
    }

    else {
      this.current_study = parseInt(this.activatedRoute.snapshot.queryParamMap.get('studyId'));

      this.analystService.getAvailablePopulation(this.current_study).subscribe((people) => {
        if (people){
          this.people = people;
          this.loading = false;
          this.spinner.hide();
        }

      }, errorMessage => {
        this.router.navigate(['404']);
        this.spinner.hide();
        this.loading = false;
        this.peopleErrorMessage = errorMessage;
      })
    }
  }

}
