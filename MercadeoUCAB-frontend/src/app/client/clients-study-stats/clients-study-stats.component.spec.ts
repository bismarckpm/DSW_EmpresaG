import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientsStudyStatsComponent } from './clients-study-stats.component';

describe('ClientsStudyStatsComponent', () => {
  let component: ClientsStudyStatsComponent;
  let fixture: ComponentFixture<ClientsStudyStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientsStudyStatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientsStudyStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
