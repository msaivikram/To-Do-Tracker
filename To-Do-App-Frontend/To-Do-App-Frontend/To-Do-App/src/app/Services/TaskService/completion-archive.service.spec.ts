import { TestBed } from '@angular/core/testing';

import { CompletionArchiveService } from './completion-archive.service';

describe('CompletionArchiveService', () => {
  let service: CompletionArchiveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompletionArchiveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
