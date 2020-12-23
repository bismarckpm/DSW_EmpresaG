import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyRequestsComponent } from './study-requests.component';

describe('StudyRequestsComponent', () => {
  let component: StudyRequestsComponent;
  let fixture: ComponentFixture<StudyRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudyRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
