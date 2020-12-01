import { TestBed } from '@angular/core/testing';

import { UserSurveyService } from './user-survey.service';

describe('UserSurveyService', () => {
  let service: UserSurveyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserSurveyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
