import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalysisRequestsComponent } from './analysis-requests.component';

describe('AnalysisRequestsComponent', () => {
  let component: AnalysisRequestsComponent;
  let fixture: ComponentFixture<AnalysisRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalysisRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalysisRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
