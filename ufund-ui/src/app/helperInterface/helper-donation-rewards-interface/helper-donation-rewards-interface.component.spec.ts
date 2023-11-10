import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelperDonationRewardsInterfaceComponent } from './helper-donation-rewards-interface.component';

describe('HelperDonationRewardsInterfaceComponent', () => {
  let component: HelperDonationRewardsInterfaceComponent;
  let fixture: ComponentFixture<HelperDonationRewardsInterfaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HelperDonationRewardsInterfaceComponent]
    });
    fixture = TestBed.createComponent(HelperDonationRewardsInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
