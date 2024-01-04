import { TestBed } from '@angular/core/testing';

import { GetTodayTasksService } from './get-today-tasks.service';

describe('GetTodayTasksService', () => {
  let service: GetTodayTasksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetTodayTasksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
