import { TestBed } from '@angular/core/testing';

import { GetTomorrowTasksService } from './get-tomorrow-tasks.service';

describe('GetTomorrowTasksService', () => {
  let service: GetTomorrowTasksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetTomorrowTasksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
