import { TestBed } from '@angular/core/testing';

import { SurveyGuard } from './survey.guard';

describe('SurveyGuard', () => {
  let guard: SurveyGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(SurveyGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
