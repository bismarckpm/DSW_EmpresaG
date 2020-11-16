import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewQuestionComponent } from './create-new-question.component';

describe('CreateNewQuestionComponent', () => {
  let component: CreateNewQuestionComponent;
  let fixture: ComponentFixture<CreateNewQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateNewQuestionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
