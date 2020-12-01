import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableSurveysComponent } from './available-surveys.component';

describe('AvailableSurveysComponent', () => {
  let component: AvailableSurveysComponent;
  let fixture: ComponentFixture<AvailableSurveysComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailableSurveysComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableSurveysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
