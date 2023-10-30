import { Component, OnInit, Input } from '@angular/core';
import { NeedService } from '../need.service';
import { UserService } from '../user.service';
import { User } from '../user';
import { Need } from '../need';
@Component({
  selector: 'app-helper-choice-interface',
  templateUrl: './helper-choice-interface.component.html',
  styleUrls: ['./helper-choice-interface.component.css']
})
export class HelperChoiceInterfaceComponent implements OnInit{
    needs? : Need[];
    needsDisplayed? : Need[];
    @Input() searchTerm : string = '';
    @Input() name? : string;
    user? : User;
    constructor(
    private needService : NeedService,
    private userService : UserService
    ){
    }
    
    ngOnInit(): void {
      this.getNeeds();
      this.getUser();
    }
    //Get list of needs
    getNeeds(): void {
      this.needService.getCupboard()
        .subscribe(needs => {
          this.needs = needs;
          this.needsDisplayed = this.needs;
        });
    }
    addToFundingBasket(need : Need):void{
      if(this.user === undefined){
        return;
      }
      this.user.fundingBasket.push(need);
      this.userService.updateData(this.user).subscribe();
    }
    //Get user
    getUser():void{
      if(this.name === undefined){
        return;
      }
      this.userService.getData(this.name).subscribe(
      user => this.user = user
      );
    }
    //Filter the needs list.
    filter():void{
      this.needsDisplayed = this.needs;
      if(this.needsDisplayed === undefined || this.searchTerm === ''){
        return;
      }

      //Does not check if the type is undefined or not. Oh well
      this.needsDisplayed = this.needsDisplayed.filter((need) => {return need.type.includes(this.searchTerm) });
      this.searchTerm = '';
    }


}
