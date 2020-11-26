import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPresentationComponent } from './edit-presentation.component';

describe('EditPresentationComponent', () => {
  let component: EditPresentationComponent;
  let fixture: ComponentFixture<EditPresentationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditPresentationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPresentationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
