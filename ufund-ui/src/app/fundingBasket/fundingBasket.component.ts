import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { MessageService } from '../message.service';
import { UserService } from '../user.service';

@Component({
    selector: 'app-fundingBasket',
    templateUrl: './fundingBasket.component.html',
    styleUrls: [ './fundingBasket.component.css' ]
  })
  export class DashboardComponent implements OnInit {

    Input() user ?: User;
    constructor(private loc :Location, private r:ActivatedRoute, private userService: UserService){}
  
    ngOnInit(): void {
      this.getFundingBasket();
    }
    
    getFundingBasket(): void {
      this.userService.getDataArray();
        .subscribe(user => this.user = user.slice(1, 5));
    }
    addFundingBasket(need: Need): void {
      this.user = this.user.filter(h => h !== need);
      this.user.addData(need).subscribe();
    }

    deleteFundingBasket(need: Need): void {
        this.user = this.user.filter(h => h !== need);
        this.user.deleteData(need.name).subscribe();
    }
    save():void{
      if(this.user != undefined){
        this.userService.updateData(this.user).subscribe(()=>this.goBack());
      }
    }
    goBack():void{
      this.loc.back();
    }
}