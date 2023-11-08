import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';
import { User } from '../dataClasses/user';


describe('UserService', () => {
  let service: UserService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    service = TestBed.inject(UserService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should empty the funding basket for the specified user', () => {
    const mockUser: User = {
      name: "Test User",
      fundingBasket: [{ name: 'item1', type: 'type1', cost: 10, quantity: 1 },
      { name: 'item2', type: 'type2', cost: 20, quantity: 2 },
      { name: 'item3', type: 'type3', cost: 30, quantity: 3 }
      ],
      isManager: false,
      totalDonations : 0,
      availableRewards : []
    };

    service.emptyFundingBasket(mockUser.name).subscribe(response => {
      expect(response.fundingBasket.length).toEqual(0);
    });

    const req = httpTestingController.expectOne(`${service.getUserUrl()}/${mockUser.name}/emptyFundingBasket`);
    expect(req.request.method).toEqual('PUT');
    req.flush(mockUser);
  });
});