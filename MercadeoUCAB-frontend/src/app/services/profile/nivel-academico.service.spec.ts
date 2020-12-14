import { TestBed } from '@angular/core/testing';

import { NivelAcademicoService } from './nivel-academico.service';

describe('NivelAcademicoService', () => {
  let service: NivelAcademicoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NivelAcademicoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
