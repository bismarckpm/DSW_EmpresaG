import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddQuestionFromPoolComponent } from './add-question-from-pool.component';

describe('AddQuestionFromPoolComponent', () => {
  let component: AddQuestionFromPoolComponent;
  let fixture: ComponentFixture<AddQuestionFromPoolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddQuestionFromPoolComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddQuestionFromPoolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
