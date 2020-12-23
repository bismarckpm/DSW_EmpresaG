import { TestBed } from '@angular/core/testing';

import { AnalystService } from './analyst.service';

describe('AnalystService', () => {
  let service: AnalystService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnalystService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
