import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExistingStudiesComponent } from './existing-studies.component';

describe('ExistingStudiesComponent', () => {
  let component: ExistingStudiesComponent;
  let fixture: ComponentFixture<ExistingStudiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExistingStudiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExistingStudiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
