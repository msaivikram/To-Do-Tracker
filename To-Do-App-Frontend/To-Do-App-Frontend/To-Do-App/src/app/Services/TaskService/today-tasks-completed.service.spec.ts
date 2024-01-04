import { TestBed } from '@angular/core/testing';

import { TodayTasksCompletedService } from './today-tasks-completed.service';

describe('TodayTasksCompletedService', () => {
  let service: TodayTasksCompletedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodayTasksCompletedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
