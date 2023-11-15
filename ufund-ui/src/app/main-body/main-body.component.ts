import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from './../dataClasses/user';
import { UserService } from './../services/user.service';
@Component({
  selector: 'app-main-body',
  templateUrl: './main-body.component.html',
  styleUrls: ['./main-body.component.css']
})
export class MainBodyComponent implements OnInit{
  constructor(private router : Router,
    private r: ActivatedRoute,
    private userService : UserService
  ){}
  manager? : Boolean;
  ngOnInit(){
    this.getIsManager();
  }
  getWelcome(){
    let name : string | null = this.r.snapshot.paramMap.get('name');
    if(name !== null){
      return "Welcome " + name +  "!";
    }else{
      return "";
    }
  }
  getLogin(){
    return this.getLink("login");
  }
  getCheckout(){
    return this.getLink("checkout");
  }
  getReward(){
    return this.getLink("reward");
  }
  getChoices(){
    return this.getLink("choices");
  }
  getCupboard(){
    return this.getLink("cupboard");
  }
  getEventDisplay(){
    return this.getLink("eventDisplay");
  }
  getDonationManagement(){
    return this.getLink("donationManagement");
  }
  getEventManagement(){
    return this.getLink("eventManagement");
  }

  getLink(path:string){
    let name  : string | null = this.r.snapshot.paramMap.get('name');
    //console.log("main-body param map:" + this.r.snapshot.paramMap.get('name'));
    //console.log(this.r.toString());
    if(name === null){
      return '';
    }else{
      // adding a '/' in the front will cause the routerLink to replace instead of add a string.
      return path;
    }
  }

  getIsManager(){
    let name : string | null = this.r.snapshot.paramMap.get('name');
    if(name === null){
      this.manager = undefined;
      return;
    }
    this.userService.getData(name).subscribe(
    (userData)=>{
      if(userData === undefined){
        this.manager = undefined;
        return;
      }
      this.manager = userData.isManager;
    }
    );
  }
}
