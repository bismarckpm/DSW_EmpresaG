import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUsersSummaryComponent } from './view-users-summary.component';

describe('ViewUsersSummaryComponent', () => {
  let component: ViewUsersSummaryComponent;
  let fixture: ComponentFixture<ViewUsersSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewUsersSummaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewUsersSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
