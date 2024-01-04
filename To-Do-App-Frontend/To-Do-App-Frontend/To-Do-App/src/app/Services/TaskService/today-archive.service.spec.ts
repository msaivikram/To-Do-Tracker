import { TestBed } from '@angular/core/testing';

import { TodayArchiveService } from './today-archive.service';

describe('TodayArchiveService', () => {
  let service: TodayArchiveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TodayArchiveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
