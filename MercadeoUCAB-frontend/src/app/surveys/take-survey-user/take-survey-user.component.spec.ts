import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TakeSurveyUserComponent } from './take-survey-user.component';

describe('TakeSurveyUserComponent', () => {
  let component: TakeSurveyUserComponent;
  let fixture: ComponentFixture<TakeSurveyUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TakeSurveyUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TakeSurveyUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
