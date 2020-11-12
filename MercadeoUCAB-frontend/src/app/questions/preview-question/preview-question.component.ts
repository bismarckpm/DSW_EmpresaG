import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-preview-question',
  templateUrl: './preview-question.component.html',
  styleUrls: ['./preview-question.component.scss']
})
export class PreviewQuestionComponent implements OnInit {

  constructor(private Activatedroute:ActivatedRoute,
    private router:Router) { }

  ngOnInit(): void {
    if ((this.Activatedroute.snapshot.queryParamMap.get('qid')||0) == 0){
      this.router.navigate(['404']);
    }
  }

}
