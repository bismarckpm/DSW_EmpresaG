import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewStudyComponent } from './view-study.component';

describe('ViewStudyComponent', () => {
  let component: ViewStudyComponent;
  let fixture: ComponentFixture<ViewStudyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewStudyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
