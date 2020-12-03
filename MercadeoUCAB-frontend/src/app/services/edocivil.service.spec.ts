import { TestBed } from '@angular/core/testing';

import { EdocivilService } from './edocivil.service';

describe('EdocivilService', () => {
  let service: EdocivilService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EdocivilService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
