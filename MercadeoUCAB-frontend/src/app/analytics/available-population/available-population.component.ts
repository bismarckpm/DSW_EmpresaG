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
  i: number;
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

      this.analystService.getAvailablePopulation(this.current_study).subscribe((res) => {
        if (res.codigo == 0){
          if (res.objeto){
            this.i = 0
            for (this.i = 0; this.i < res.objeto.length; this.i++){
              this.people[this.i] = res.objeto[this.i][0] as persondata;
              this.people[this.i].telefono = res.objeto[this.i][1];
              this.people[this.i].ocupacion = res.objeto[this.i][2];
            }
            this.loading = false;
            this.spinner.hide();
          }
        }
        else{
          this.router.navigate(['404']);
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
