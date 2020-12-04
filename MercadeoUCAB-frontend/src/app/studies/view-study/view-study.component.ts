import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";
import { StudiesService } from '../../services/studies.service';
import { Study } from '../../classes/study';
import { Location } from '@angular/common';

@Component({
  selector: 'app-view-study',
  templateUrl: './view-study.component.html',
  styleUrls: ['./view-study.component.scss']
})
export class ViewStudyComponent implements OnInit {
  loading: boolean = true;
  current_study: number;
  estudio: Study;
  studyErrorMessage: string;

  constructor(private Activatedroute: ActivatedRoute,
    private router: Router,
    private location: Location,
    private studiesService: StudiesService,
    private spinner: NgxSpinnerService) {
    /* If query is empty return 404 */
    if ((this.Activatedroute.snapshot.queryParamMap.get('sid') || 0) == 0) {
      this.router.navigate(['404']);
    }

    /* Get current study */
    else {
      this.spinner.show();

      this.current_study = parseInt(this.Activatedroute.snapshot.queryParamMap.get('sid'));
      this.studiesService.getStudy(this.current_study).subscribe((study) => {
        
        if (!study) {
          this.router.navigate(['404']);
        }

        //this.estudio = study;

        this.spinner.hide();
        this.loading = false;
      }, errorMessage => {
        this.loading = false;
        this.spinner.hide();
        this.studyErrorMessage = errorMessage;
      });
    }
  }

  ngOnInit(): void {
  }

  backClicked() {
    this.location.back();
  }

}
