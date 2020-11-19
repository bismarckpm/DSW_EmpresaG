import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPresentationComponent } from './add-presentation.component';

describe('AddPresentationComponent', () => {
  let component: AddPresentationComponent;
  let fixture: ComponentFixture<AddPresentationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPresentationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPresentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
