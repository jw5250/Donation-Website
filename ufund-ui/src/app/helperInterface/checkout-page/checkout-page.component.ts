import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';
import { NeedService } from '../../services/need.service';
import { User } from '../../dataClasses/user';
import { Need } from '../../dataClasses/need';
@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.css']
})
export class CheckoutPageComponent implements OnInit/*, OnChanges*/{
  constructor(private userService: UserService,
  private needService : NeedService,
  private r: ActivatedRoute){  
  }
  @Input() name? : string | null;
  user? : User;
  emptied : string = "Nothing here! To buy things, click on the \"Needs\" tab.";
  emptiedDisplay : string = '';
  total : number = 0;
  getTotalBasketValue(){
    if(this.user === undefined){
      return;
    }
    for(let i = 0; i < this.user.fundingBasket.length;i++){
      this.total += this.user.fundingBasket[i].cost;
    }
  }
  ngOnInit(){
    if(this.r.parent === null){
      return;
    }
    this.name = this.r.parent.snapshot.paramMap.get('name');
    this.getUser();
  }
  getFundingBasket(){
    if(this.user === undefined){
      return [];
    }else{
      return this.user.fundingBasket;
    }
  }
  getUser(){
    if(this.name === undefined || this.name === null){
      console.log("Undefined");
      return;
    }
    this.userService.getData(this.name).subscribe(user => {
      this.user = user;
      this.getTotalBasketValue();
    });
  }
    //Add item to the user's funding basket.
  removeFromBasket(needRemoved : Need):void{
    if(this.user === undefined){
      return;
    }
    for(let i = 0; i < this.user.fundingBasket.length;i++){
      if(needRemoved.name === this.user.fundingBasket[i].name){
        this.needService.getNeed(needRemoved.name).subscribe((need)=>
        {
          need.quantity++;
          this.total -= need.cost;
          this.needService.updateNeed(need).subscribe();
        });
        this.user.fundingBasket.splice(i, 1);
        break;
      }
    }
    this.userService.updateData(this.user).subscribe();
    sessionStorage.setItem("userData", JSON.stringify(this.user));
  }
  checkout(){
    if(this.user === undefined){
      return;
    }else if(this.user.fundingBasket.length === 0){
      this.emptiedDisplay = this.emptied;
      return;
    }
    for(let i = 0; i < this.user.fundingBasket.length;i++){
      this.user.totalDonations += this.user.fundingBasket[i].cost;
    }
    //alert("You got a new reward! Make sure to check it out!");
    this.emptiedDisplay = '';
    this.user.fundingBasket = [];
    
    if(this.user != undefined){
        this.userService.updateData(this.user).subscribe();
        sessionStorage.setItem("userData", JSON.stringify(this.user));
      }
    }
}
