import { TestBed } from '@angular/core/testing';

import { CompletionServiceService } from './completion-service.service';

describe('CompletionServiceService', () => {
  let service: CompletionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompletionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
