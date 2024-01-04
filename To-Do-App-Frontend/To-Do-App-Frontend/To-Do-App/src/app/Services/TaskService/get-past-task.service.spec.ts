import { TestBed } from '@angular/core/testing';

import { GetPastTaskService } from './get-past-task.service';

describe('GetPastTaskService', () => {
  let service: GetPastTaskService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetPastTaskService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
