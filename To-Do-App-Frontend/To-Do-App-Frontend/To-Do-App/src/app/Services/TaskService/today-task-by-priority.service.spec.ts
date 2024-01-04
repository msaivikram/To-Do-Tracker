import { TestBed } from '@angular/core/testing';

import { TodayTaskByPriorityService } from './today-task-by-priority.service';

describe('TodayTaskByPriorityService', () => {
  let service: TodayTaskByPriorityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodayTaskByPriorityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
