import { TestBed } from '@angular/core/testing';

import { RestOfTheTaskByPriorityService } from './rest-of-the-task-by-priority.service';

describe('RestOfTheTaskByPriorityService', () => {
  let service: RestOfTheTaskByPriorityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestOfTheTaskByPriorityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
