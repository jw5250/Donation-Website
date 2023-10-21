import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FundingBasketComponent } from './fundingBasket.component';

describe('FundingBasketComponent', () => {
    let component: FundingBasketComponent;
    let fixture: ComponentFixture<FundingBasketComponent>;
  
    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [FundingBasketComponent]
      });
      fixture = TestBed.createComponent(FundingBasketComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  
    it('should create', () => {
      expect(component).toBeTruthy();
    });
  });