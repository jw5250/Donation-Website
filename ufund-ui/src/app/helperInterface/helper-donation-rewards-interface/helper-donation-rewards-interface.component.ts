import { Component, Input, OnInit } from '@angular/core';
import { DonationReward } from '../../dataClasses/DonationReward';
import { User } from '../../dataClasses/user';
import { UserService } from '../../services/user.service';
import { DonationRewardService } from '../../services/donation-reward.service';

@Component({
  selector: 'app-helper-donation-rewards-interface',
  templateUrl: './helper-donation-rewards-interface.component.html',
  styleUrls: ['./helper-donation-rewards-interface.component.css']
})
export class HelperDonationRewardsInterfaceComponent implements OnInit{
  @Input() name? : string = undefined;
  user? : User = undefined;
  rewardsAvailable : string[] = [];
  allRewards : DonationReward[] = [];
  constructor(private userService: UserService,
  private donationRewardService : DonationRewardService
  ){}
  
  ngOnInit(){
    this.getUser();
    this.getAllDonationRewards();
  }

  getUser(){
    if(this.name === undefined){
      console.log("Undefined User");
      return;
    }
    this.userService.getData(this.name).subscribe(user => {
    this.user = user;
    //Send a request to display all rewards and if they are claimed or not. Nothing more.
    this.rewardsAvailable = user.availableRewards;
    });
  }
  getAllDonationRewards(){
    this.donationRewardService.getDonationRewards().subscribe(
    (rewards)=>{
      this.allRewards = rewards;
    });
  }

  displayReward(donationReward : DonationReward){
    return "Name: " + donationReward.name + " | Dollars:" + donationReward.requirement;
  }
  displayTotalDonated(){
    if(this.user === undefined){
      return "";
    }else{
      return "Total money donated: " + this.user.totalDonations;
    }

  }
  updateDonation(donationRewardToBeAdded:DonationReward){
    if(this.user === undefined || this.user.availableRewards === undefined || this.user.totalDonations < donationRewardToBeAdded.requirement){
      return;
    }else{
      this.user.availableRewards = this.user.availableRewards.filter(donationRewardName => {return donationRewardToBeAdded.name  !== donationRewardName;});
      this.rewardsAvailable = this.user.availableRewards;
    }
    this.userService.updateData(this.user).subscribe();
    sessionStorage.setItem("userData", JSON.stringify(this.user));
  }
  addDonation(addedDonationRewardName : string){
    this.donationRewardService.getDonationReward(addedDonationRewardName).subscribe( (reward) =>{
      this.updateDonation(reward);
    });
    //When received, automatically check out the item.
  }
}
