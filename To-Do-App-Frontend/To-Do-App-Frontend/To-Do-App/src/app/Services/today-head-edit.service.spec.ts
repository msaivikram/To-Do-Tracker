import { TestBed } from '@angular/core/testing';

import { TodayHeadEditService } from './today-head-edit.service';

describe('TodayHeadEditService', () => {
  let service: TodayHeadEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodayHeadEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
