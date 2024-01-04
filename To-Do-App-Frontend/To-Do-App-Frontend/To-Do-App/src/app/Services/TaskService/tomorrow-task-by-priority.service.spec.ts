import { TestBed } from '@angular/core/testing';

import { TomorrowTaskByPriorityService } from './tomorrow-task-by-priority.service';

describe('TomorrowTaskByPriorityService', () => {
  let service: TomorrowTaskByPriorityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TomorrowTaskByPriorityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
