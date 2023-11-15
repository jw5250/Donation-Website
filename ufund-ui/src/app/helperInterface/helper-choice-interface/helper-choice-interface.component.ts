import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NeedService } from '../../services/need.service';
import { UserService } from '../../services/user.service';
import { User } from '../../dataClasses/user';
import { Need } from '../../dataClasses/need';
@Component({
  selector: 'app-helper-choice-interface',
  templateUrl: './helper-choice-interface.component.html',
  styleUrls: ['./helper-choice-interface.component.css']
})
export class HelperChoiceInterfaceComponent implements OnInit{
    needs : Need[] = [];
    needsDisplayed : Need[] = [];
    searchTerm : string = "";
    choiceErrorMessage : string = "";
    @Input() name? : null | string;
    user? : User;
    constructor(
    private needService : NeedService,
    private userService : UserService,
    private r : ActivatedRoute
    ){
    }
    updateNeedsAvailable(){
      this.needsDisplayed = [];
      for(let i = 0; i < this.needs.length;i++){
        if(this.needs[i].quantity > 0){
          this.needsDisplayed.push(this.needs[i]);
        }
      }
    }
    ngOnInit(): void {
    if(this.r.parent === null){
        return;
      }
      this.name = this.r.parent.snapshot.paramMap.get('name');
      this.getNeeds();
      this.getUser();
    }
    returnUserNeeds(){
      if(this.user !== undefined){
        return this.user.fundingBasket;
      }else{
        return [];
      }
    }
    //Get list of needs
    getNeeds(): void {
      this.needService.getCupboard()
        .subscribe(needs => {
          this.needs = needs;
          this.needsDisplayed = this.needs;
          this.updateNeedsAvailable();
        });
    }
    //Add item to the user's funding basket.
    addToFundingBasket(needAdded : Need):void{
      
      this.needService.getNeed(needAdded.name).subscribe(
      (need)=>{
        if(this.user === undefined){
          return;
        }
        if(need.quantity >= 0){
          this.choiceErrorMessage = "";
          this.user.fundingBasket.push(needAdded);
          needAdded.quantity--;
          this.needService.updateNeed(needAdded).subscribe();
          this.userService.updateData(this.user).subscribe();
          sessionStorage.setItem("userData", JSON.stringify(this.user));
          this.updateNeedsAvailable();          
        }else{
          this.choiceErrorMessage = "Need added is not available.";
        }
      }
      );


    }
    //Get user
    getUser():void{
      if(this.name === undefined || this.name === null){
        return;
      }
      this.userService.getData(this.name).subscribe(
      user => this.user = user
      );
    }
    //Filter the needs list.
    filter():void{
      this.updateNeedsAvailable();
      if(this.needsDisplayed === undefined || this.searchTerm === ""){
        return;
      }
      this.needsDisplayed = this.needsDisplayed.filter((need) => {return need.type.includes(this.searchTerm) });
      this.searchTerm = "";
    }


}
