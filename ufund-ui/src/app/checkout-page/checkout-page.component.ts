import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { UserService } from '.././user.service';
import { User } from '../user';
import { Need } from '../need';
@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.css']
})
export class CheckoutPageComponent implements OnInit/*, OnChanges*/{
  constructor(private userService: UserService){  
  }
  @Input() name? : string;
  user? : User;
  emptied : string = "Emptied Basket!";
  emptiedDisplay : string = '';
  ngOnInit(){
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
    if(this.name === undefined){
      console.log("Undefined");
      return;
    }
    this.userService.getData(this.name).subscribe(user => {this.user = user});
  }
    //Add item to the user's funding basket.
  removeFromBasket(needRemoved : Need):void{
    if(this.user === undefined){
      return;
    }
    //If need does not exist
    for(let i = 0; i < this.user.fundingBasket.length;i++){
      if( (this.user.fundingBasket[i].name === needRemoved.name) && (this.user.fundingBasket[i].quantity > 1)){
        this.user.fundingBasket[i].quantity--;
        break;
      }else{
        this.user.fundingBasket = this.user.fundingBasket.filter((need)=>{return need.name != needRemoved.name});
      }
    }
    this.userService.updateData(this.user).subscribe();
    sessionStorage.setItem("userData", JSON.stringify(this.user));
  }
  checkout(){
    if(this.user === undefined){
      console.log("Undefined user.");
      return;
    }else if(this.user.fundingBasket.length === 0){
      this.emptiedDisplay = this.emptied;
      return;
    }
    this.emptiedDisplay = '';
    this.user.fundingBasket = [];
    
    if(this.user != undefined){
        this.userService.updateData(this.user).subscribe();
        sessionStorage.setItem("userData", JSON.stringify(this.user));
        console.log(this.user);
      }
    }
}
