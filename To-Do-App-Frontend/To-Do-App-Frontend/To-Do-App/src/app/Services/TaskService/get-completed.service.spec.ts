import { TestBed } from '@angular/core/testing';

import { GetCompletedService } from './get-completed.service';

describe('GetCompletedService', () => {
  let service: GetCompletedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetCompletedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
