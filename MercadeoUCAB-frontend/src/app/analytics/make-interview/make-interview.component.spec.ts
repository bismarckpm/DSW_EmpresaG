import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeInterviewComponent } from './make-interview.component';

describe('MakeInterviewComponent', () => {
  let component: MakeInterviewComponent;
  let fixture: ComponentFixture<MakeInterviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeInterviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeInterviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
