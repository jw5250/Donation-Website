import { Component, Input, OnInit } from '@angular/core';
import { Need } from '../need';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService } from '../message.service';
import { UserService } from '../user.service';
import { User } from 'app/user';


@Component({
    selector: 'app-fundingBasket',
    templateUrl: './fundingBasket.component.html',
    styleUrls: [ './fundingBasket.component.css' ]
  })
  export class FundingBasketComponent implements OnInit {

    @Input() user?: User;
    constructor(private loc :Location, private r : ActivatedRoute, private userService: UserService){}
  
    ngOnInit(): void {
      this.getFundingBasket();
    }
    
    getFundingBasket(): void {
      this.userService.getDataArray();
    }
    
    addFundingBasket(need: Need): void {
      this.user?.fundingBasket.push(need);
      this.save();
    }

    deleteFundingBasket(need: Need): void {
        this.userService.deleteData(need.name).subscribe();
        this.save();
    }

    emptyFundingBasket(): void {
      if (this.user) {
        this.userService.emptyFundingBasket(this.user.name).subscribe();
    }
    this.save(); // Save the updated funding basket
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