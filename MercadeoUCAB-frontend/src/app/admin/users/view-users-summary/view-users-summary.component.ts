import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../../../core/services/admin/user.service';
import { NgxSpinnerService } from "ngx-spinner";
import { Person } from 'src/app/core/classes/profile/person';
import { SelectItem } from 'primeng/api';


@Component({
  selector: 'app-view-users-summary',
  templateUrl: './view-users-summary.component.html',
  styleUrls: ['./view-users-summary.component.scss']
})
export class ViewUsersSummaryComponent implements OnInit {

  usuario: Person;
  loading: boolean = true;
  current_user: number;
  userErrorMessage: string;

  constructor(private Activatedroute: ActivatedRoute,
    private userService: UserService,
    private spinner: NgxSpinnerService,
    private router: Router) {
    /* If query is empty return 404 */
  //   if ((this.Activatedroute.snapshot.queryParamMap.get('qid') || 0) == 0) {
  //     this.router.navigate(['404']);
  //   }

  //   /* Get current person */
  //   else {
  //     this.spinner.show();
  //     this.current_user = parseInt(this.Activatedroute.snapshot.queryParamMap.get('qid'));
  //     this.userService.getPerson(this.current_user).subscribe((person) => {
  //       this.usuario = person;
  //       if (this.usuario) {
  //         this.loading = false;
  //         this.spinner.hide();
  //       }

  //       else {
  //         this.router.navigate(['404']);
  //       }
  //     });
  //   }
  }

  ngOnInit(): void {
  }

}
