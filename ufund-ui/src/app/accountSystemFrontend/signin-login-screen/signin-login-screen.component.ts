import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { MessageService } from '../../services/message.service';
import { UserService } from '../../services/user.service';
import { DonationRewardService } from '../../services/donation-reward.service';
import { User } from '../../dataClasses/user';
@Component({
  selector: 'app-signin-login-screen',
  templateUrl: './signin-login-screen.component.html',
  styleUrls: ['./signin-login-screen.component.css']
})

//Neither the signIn or logIn functions are working.
export class SigninLoginScreenComponent implements OnInit{
  constructor(
    private userService: UserService,
    private donationRewardService: DonationRewardService,
    private messanger: MessageService
  ) {}
  //Used so this component can communicate with those that use it.
  //Passes a reference of a user to the parent.
  @Output() person : EventEmitter<User> = new EventEmitter<User>();
  //The input for the sign in name
  @Input() signInName?:string = undefined;
  //The input for the log in name.
  @Input() logInName?:string = undefined;
  //The user's data.
  user? : User = undefined;
  signInErrorMessage : string = "";
  logInErrorMessage : string = "";
  userIdentifier: string = "userData";

  ngOnInit(){
    let tokenizedUser : string | null = sessionStorage.getItem(this.userIdentifier);
    if(tokenizedUser != null){
      this.user = JSON.parse(tokenizedUser);
      this.person.emit(this.user);
    }
  }
  //Create a new account in the database.
  signIn(): void {
    this.signInErrorMessage = "";
    if(this.signInName != undefined){
      this.signInName = this.signInName.trim();
      let donationRewards : string[] = [];
      this.donationRewardService.getDonationRewards().subscribe((rewards) => 
      {
        for(let i = 0; i < rewards.length;i++){
          donationRewards.push(rewards[i].name);
        }
        //Async call inside of an async call
        this.userService.addData({name : this.signInName, isManager : false, fundingBasket : [], availableRewards: donationRewards, totalDonations: 0} as User)
      .subscribe( (user) => 
        {
          user == undefined ? this.signInErrorMessage = "Issue with getting in name." :
          this.signInErrorMessage = "" ;
          this.signInName = undefined;
        });
      });

    }
  }
  //Access some account in the database.
  logIn(): void {
    this.logInErrorMessage = "";
    if (this.logInName != undefined) {
      this.logInName = this.logInName.trim();
      this.userService.getData(this.logInName)
        .subscribe((user) => {
          this.user = user;
          this.person.emit(user);
          if(user != undefined){
            sessionStorage.setItem(this.userIdentifier, JSON.stringify(user));
            //add key value pair for session storage
            //Generate token
            //Attach user data to token
            //Send bundled data to server via SessionDataServiceService
            this.messanger.add("Found account:" + user.name);
          }else{
            this.messanger.add("Invalid account!");
            this.logInErrorMessage = "Account does not exist.";
          }
          this.logInName = undefined;
        });
    }

  }
  //Log out of the account
  logOut(): void{
  //remove some id if it existed
    if(this.user == undefined){
      return;
    }
    
    this.userService.updateData(this.user).subscribe(()=>{
      sessionStorage.removeItem(this.userIdentifier);
      this.person.emit(undefined);
      this.user = undefined;
    });
    //When logged out, data is no longer there.
  }
}
