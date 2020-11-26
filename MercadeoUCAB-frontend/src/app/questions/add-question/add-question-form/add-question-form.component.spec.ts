import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddQuestionFormComponent } from './add-question-form.component';

describe('AddQuestionFormComponent', () => {
  let component: AddQuestionFormComponent;
  let fixture: ComponentFixture<AddQuestionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddQuestionFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddQuestionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
