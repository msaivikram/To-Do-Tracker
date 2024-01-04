import { TestBed } from '@angular/core/testing';

import { GetArchivedService } from './get-archived.service';

describe('GetArchivedService', () => {
  let service: GetArchivedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetArchivedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
