import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";
import { StudiesService } from '../../services/studies.service';
import { Study } from '../../classes/study';

@Component({
  selector: 'app-edit-study',
  templateUrl: './edit-study.component.html',
  styleUrls: ['./edit-study.component.scss']
})
export class EditStudyComponent implements OnInit {
  current_study: number;
  estudio: Study;
  display: boolean = false;

  constructor(private Activatedroute:ActivatedRoute,
    private router:Router,
    private studiesService: StudiesService,
    private spinner: NgxSpinnerService) {
      /* If query is empty return 404 */
      if ((this.Activatedroute.snapshot.queryParamMap.get('sid')||0) == 0){
        this.router.navigate(['404']);
      }

      /* Get current study */
      else {
        this.spinner.show();
        this.current_study = parseInt(this.Activatedroute.snapshot.queryParamMap.get('sid'));
        this.studiesService.getStudy(this.current_study).subscribe((study) => {
          this.estudio = study;
          if (this.estudio){
            /* If study is finished it can't be modified */
            if (this.estudio.id_estado == 3) {
              this.router.navigate(['404']);
            }
            //console.log(this.estudio);
            //this.createForm();

            /* Only add to form array if there are options
            if (this.pregunta.opciones && this.pregunta.opciones.length>0)
              this.setFormArray();


            this.getSubcategories(); // Get subcategories according to the category selected
            */
          }

          else {
            this.router.navigate(['404']);
          }
        });
      }
  }

  ngOnInit(): void {
  }

  showAddOptions(){
    this.display = true;
  }

}
