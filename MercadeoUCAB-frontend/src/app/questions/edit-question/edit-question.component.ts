import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.scss']
})
export class EditQuestionComponent implements OnInit {

  constructor(private Activatedroute:ActivatedRoute,
    private router:Router) { }

  ngOnInit(): void {
    if ((this.Activatedroute.snapshot.queryParamMap.get('qid')||0) == 0){
      this.router.navigate(['404']);
    }
  }

}
