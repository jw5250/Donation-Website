import { TestBed } from '@angular/core/testing';

import { UserDatabaseTestService } from './user-database-test.service';

describe('DatabaseTestService', () => {
  let service: UserDatabaseTestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserDatabaseTestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
