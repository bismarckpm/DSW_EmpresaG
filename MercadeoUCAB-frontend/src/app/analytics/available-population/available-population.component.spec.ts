import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailablePopulationComponent } from './available-population.component';

describe('AvailablePopulationComponent', () => {
  let component: AvailablePopulationComponent;
  let fixture: ComponentFixture<AvailablePopulationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailablePopulationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailablePopulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
