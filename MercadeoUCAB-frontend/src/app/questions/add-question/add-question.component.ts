import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.scss']
})
export class AddQuestionComponent implements OnInit {
  constructor(
    private router: Router
  ) { 
  }

  ngOnInit(): void {
  }

  backToQuestions(event){
    this.router.navigate(["/questions"]);
  }
}
