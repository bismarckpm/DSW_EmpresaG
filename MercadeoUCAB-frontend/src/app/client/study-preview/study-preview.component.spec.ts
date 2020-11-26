import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyPreviewComponent } from './study-preview.component';

describe('StudyPreviewComponent', () => {
  let component: StudyPreviewComponent;
  let fixture: ComponentFixture<StudyPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudyPreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
