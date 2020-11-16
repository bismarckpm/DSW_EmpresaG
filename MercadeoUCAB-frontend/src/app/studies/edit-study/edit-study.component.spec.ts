import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditStudyComponent } from './edit-study.component';

describe('EditStudyComponent', () => {
  let component: EditStudyComponent;
  let fixture: ComponentFixture<EditStudyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditStudyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
