import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectExistingComponent } from './select-existing.component';

describe('SelectExistingComponent', () => {
  let component: SelectExistingComponent;
  let fixture: ComponentFixture<SelectExistingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectExistingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectExistingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
