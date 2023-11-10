import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerDonationRewardsInterfaceComponent } from './manager-donation-rewards-interface.component';

describe('ManagerDonationRewardsInterfaceComponent', () => {
  let component: ManagerDonationRewardsInterfaceComponent;
  let fixture: ComponentFixture<ManagerDonationRewardsInterfaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManagerDonationRewardsInterfaceComponent]
    });
    fixture = TestBed.createComponent(ManagerDonationRewardsInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
