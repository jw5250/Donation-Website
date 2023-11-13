import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../dataClasses/user';
import { Need } from '../../dataClasses/need';
@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.css']
})
export class CheckoutPageComponent implements OnInit/*, OnChanges*/{
  constructor(private userService: UserService,
  private r: ActivatedRoute){  
  }
  @Input() name? : string | null;
  user? : User;
  emptied : string = "Emptied Basket!";
  emptiedDisplay : string = '';
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
    this.userService.getData(this.name).subscribe(user => {this.user = user});
  }
    //Add item to the user's funding basket.
  removeFromBasket(needRemoved : Need):void{
    if(this.user === undefined){
      return;
    }
    //If need does not exist
    /*
    for(let i = 0; i < this.user.fundingBasket.length;i++){
      if( (this.user.fundingBasket[i].name === needRemoved.name) && (this.user.fundingBasket[i].quantity > 1)){
        this.user.fundingBasket[i].quantity--;
        break;
      }else{
        this.user.fundingBasket = this.user.fundingBasket.filter((need)=>{return need.name != needRemoved.name});
      }
    }
    */
    for(let i = 0; i < this.user.fundingBasket.length;i++){
      if(needRemoved.name === this.user.fundingBasket[i].name){
        this.user.fundingBasket.splice(i, 1);
        break;
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
    for(let i = 0; i < this.user.fundingBasket.length;i++){
      this.user.totalDonations += this.user.fundingBasket[i].cost;
    }
    //alert("You got a new reward! Make sure to check it out!");
    this.emptiedDisplay = '';
    this.user.fundingBasket = [];
    
    if(this.user != undefined){
        this.userService.updateData(this.user).subscribe();
        sessionStorage.setItem("userData", JSON.stringify(this.user));
        console.log(this.user);
      }
    }
}
