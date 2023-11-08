import { Component, Input, OnInit } from '@angular/core';
import { Need } from '../dataClasses/need';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService } from '../services/message.service';
import { UserService } from '../services/user.service';
import { User } from 'app/dataClasses/user';

@Component({
    selector: 'app-fundingBasket',
    templateUrl: './fundingBasket.component.html',
    styleUrls: [ './fundingBasket.component.css' ]
  })
  export class FundingBasketComponent implements OnInit {

    @Input() user?: User;
    basketRef? : Need[];
    constructor(private loc :Location, private r:ActivatedRoute, private userService: UserService){}
  
    ngOnInit(): void {
      this.getFundingBasket();
    }
    
    getFundingBasket(): void {
      if(this.user === undefined){
        this.basketRef = undefined;
      }else{
        this.basketRef = this.user.fundingBasket;
      }
    }
    
    addFundingBasket(need: Need): void {
      this.user?.fundingBasket.push(need);
      this.save();
    }

    deleteFundingBasket(need: Need): void {
        this.userService.deleteData(need.name).subscribe();
        this.save();
    }
    save():void{
      if(this.user != undefined){
        this.userService.updateData(this.user).subscribe(()=>this.goBack());
        sessionStorage.setItem("userData", JSON.stringify(this.user));
      }
    }
    goBack():void{
      this.loc.back();
    }
}