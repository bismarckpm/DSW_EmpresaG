import { TestBed } from '@angular/core/testing';

import { AnalystGuard } from './analyst.guard';

describe('AnalystGuard', () => {
  let guard: AnalystGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AnalystGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
