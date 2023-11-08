import { TestBed } from '@angular/core/testing';

import { DonationRewardService } from './donation-reward.service';

describe('DonationRewardService', () => {
  let service: DonationRewardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DonationRewardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
