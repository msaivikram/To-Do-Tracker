import { TestBed } from '@angular/core/testing';

import { GetRestOfTheTasksService } from './get-rest-of-the-tasks.service';

describe('GetRestOfTheTasksService', () => {
  let service: GetRestOfTheTasksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetRestOfTheTasksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
