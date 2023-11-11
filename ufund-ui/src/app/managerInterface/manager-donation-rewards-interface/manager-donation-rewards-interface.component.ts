import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { DonationRewardService } from '../../services/donation-reward.service';
import { UserService } from '../../services/user.service';
import { DonationReward } from '../../dataClasses/DonationReward';
//Test change
@Component({
  selector: 'app-manager-donation-rewards-interface',
  templateUrl: './manager-donation-rewards-interface.component.html',
  styleUrls: ['./manager-donation-rewards-interface.component.css']
})
export class ManagerDonationRewardsInterfaceComponent {
  donationRewards: DonationReward[] = [];
  voidDonationReward: DonationReward = {name: "", requirement: 0};
  selectedDonationReward: DonationReward = this.voidDonationReward;

  constructor(
    private route: ActivatedRoute,
    private donationRewardService: DonationRewardService,
    private location: Location,
    private userService: UserService
  ) {}

  ngOnInit(): void{
    this.getDonationRewards();
    this.selectDonationReward(this.voidDonationReward);
  }

  /**
   * getDonationRewards(): retrieves all stored DonationReward data from the server to display
   */
   //Test this.
  getDonationRewards(): void{
    this.donationRewardService.getDonationRewards()
      .subscribe(DonationRewards => this.donationRewards = DonationRewards.sort(
      (n1, n2)=>{
        if(n1.requirement > n2.requirement){
          return 1;
        }else{
          return -1;
        }
      }
      ));
  }

  /**
   * selectDonationReward(): sets the selected DonationReward based on user input
   * @param DonationReward : DonationReward that will be selected
   */
  selectDonationReward(DonationReward: DonationReward): void{
    this.selectedDonationReward = DonationReward;
  }
  /**
   * getNewDonationRewardData(): takes data from the UI input and then uses it to
   * update the DonationRewards list
   * 
   */
  getNewDonationRewardData(): void{
    const DonationRewardData =document.getElementsByName("donationRewardInput") as NodeListOf<HTMLInputElement>;
    if(DonationRewardData.item(0).value !== "" ){
      this.selectDonationReward(this.voidDonationReward);
      this.editDonationReward(DonationRewardData.item(0).value,
                  parseInt(DonationRewardData.item(1).value));
    }
  }
  /**
   * addDonationReward(): takes a DonationReward object and adds it to data storage
   * @param DonationReward 
   */
  addDonationReward(DonationReward: DonationReward): void{
    this.donationRewardService.addDonationReward(DonationReward)
      .subscribe((reward) => {
        if(reward !== undefined){
          this.donationRewards.push(reward);
        }
        this.userService.getDataArray().subscribe(
        (users)=>{
          for(let i = 0; i < users.length;i++){
            if(users[i].isManager == false){
              users[i].availableRewards.push(reward.name);
              this.userService.updateData(users[i]).subscribe();
            }
          }
        }
        );

        
      }
      );
  }

  /**
   * deleteDonationReward: deletes the given DonationReward from data storage
   * @param delDonationReward : DonationReward to delete
   */
  deleteDonationReward(delDonationReward: string): void {
    this.donationRewards = this.donationRewards.filter(DonationReward => {return DonationReward.name !== delDonationReward;});
    this.selectDonationReward(this.voidDonationReward);
    this.userService.getDataArray().subscribe(
      (users)=>{
      for(let i = 0; i < users.length;i++){
        if(users[i].isManager == false){
          users[i].availableRewards = users[i].availableRewards.filter((rewardName)=>{return rewardName !== delDonationReward});
          this.userService.updateData(users[i]).subscribe();
        }
      }
      this.donationRewards = this.donationRewards.filter((reward)=>{return reward.name !== delDonationReward});
      this.donationRewardService.deleteDonationReward(delDonationReward).subscribe();
    });
  }

  
  /**
   * editDonationReward: forms a donation reward based on the parameters, then adds or updates it
   *  depending on if it exists within the system.
   * @param name : name of donation reward to be updated
   * @param cost : cost of donation reward
   */
  editDonationReward(name: String, cost: Number): void{
    name = name.trim();
    if (!name) { return; }
    const newDonationReward = <DonationReward>({name: name, requirement: cost});
    const filtered: DonationReward[] = this.donationRewards.filter(donationReward => {return newDonationReward.name !== donationReward.name;});    
  

    if(filtered.length !== this.donationRewards.length){
      //Considering string of IDs is used for users. don't need to worry about editting requirements,
      //UI is not updating properly once again.
      this.donationRewardService.updateDonationReward(newDonationReward)
      .subscribe();
    }
    else{
      this.addDonationReward(newDonationReward);
    }
    this.getDonationRewards();
    //Does not update the screen, but it does change the backend data.
    this.selectDonationReward(this.voidDonationReward);
  }
}
