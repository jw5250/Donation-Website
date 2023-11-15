import { Component, OnInit } from '@angular/core';
import { Need } from '../../dataClasses/need';
import { NeedService } from '../../services/need.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
//import { NEEDS } from '../dummy-needs';

@Component({
  selector: 'app-cupboard',
  templateUrl: './cupboard.component.html',
  styleUrls: ['./cupboard.component.css']
})

/**
 * CupboardComponent
 * Represents the Ufund Manager view of the program
 * Allows a Ufund Manager to view a list of needs
 * They can add, edit, and delete needs
 * 
 * @Author Daniel Arcega
 */
export class CupboardComponent implements OnInit {
  MAXLEN: number = 30;
  cupboardErrorMessage : string[] = [];
  needs: Need[] = [];
  voidNeed: Need = {name: "", type: "", cost: 0, quantity: 0};
  selectedNeed: Need = this.voidNeed;

  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location
  ) {}

  ngOnInit(): void{
    this.getNeeds();
    this.selectNeed(this.voidNeed);
  }

  /**
   * getNeeds(): retrieves all stored need data from the server to display
   */
  getNeeds(): void{
    this.needService.getCupboard()
      .subscribe(needs => this.needs = needs);
  }

  /**
   * selectNeed(): sets the selected need based on user input
   * @param need : need that will be selected
   */
  selectNeed(need: Need): void{
    this.selectedNeed = need;
  }
  /**
   * getNewNeedData(): takes data from the UI input and then uses it to
   * update the needs list
   * 
   */
  getNewNeedData(): void{
    const needData =document.getElementsByName("needInput") as NodeListOf<HTMLInputElement>;
    if(needData.item(0).value !== "" ){
    this.selectNeed(this.voidNeed);
    this.editNeed(needData.item(0).value,
                  needData.item(1).value,
                  parseInt(needData.item(2).value),
                  parseInt(needData.item(3).value));
    }
  }
  /**
   * addNeed(): takes a need object and adds it to data storage
   * @param need 
   */
  addNeed(need: Need): void{
    this.selectNeed(this.voidNeed);
    this.needService.addNeed(need)
      .subscribe(need => this.needs.push(need));
  }

  /**
   * deleteNeed: deletes the given need from data storage
   * @param delNeed : need to delete
   */
  deleteNeed(delNeed: Need): void {
    this.needs = this.needs.filter(need => need.name !== delNeed.name);
    this.selectNeed(this.voidNeed);
    this.needService.deleteNeed(delNeed.name).subscribe();
  }

  
  /**
   * editNeed: forms a need based on the parameters, then adds or updates it
   *  depending on if it exists within the system.
   * @param name : name of need to be updated
   * @param type : type of need
   * @param cost : cost of need
   * @param quantity : quantity of need
   */
  editNeed(name: String, type: String, cost: Number, quantity: Number ): void{
    this.cupboardErrorMessage = [];
    let foundError : boolean = false;
    if (name === "" || type === ""){
      this.cupboardErrorMessage.push("Name and type must be defined.");
      return;
    }

    name = name.trim();
    type = type.trim();
    if(name.length > this.MAXLEN ){
      this.cupboardErrorMessage.push("Length of name is greater than " + this.MAXLEN + " characters.");
      foundError = true;
    }
    if(type.length > this.MAXLEN){
      this.cupboardErrorMessage.push("Length of type is greater than " + this.MAXLEN + " characters.");  
      foundError = true;
    }
    if(Number.isNaN(cost) || Number.isNaN(quantity)){
      this.cupboardErrorMessage.push("One of the numeric inputs (quantity or cost) are not valid numbers."); 
      foundError = true;
    }
    if(cost.valueOf() < 0){
      this.cupboardErrorMessage.push("Cost can't be less than zero."); 
      foundError = true;
    }
    if(quantity.valueOf() <= 0){
      this.cupboardErrorMessage.push("Cost can't be less than or equal to zero."); 
      foundError = true;
    }
    if (foundError === true) { return; }
    this.selectNeed(this.voidNeed);
    const newNeed  = <Need>({name: name, type: type, cost: cost, quantity: quantity});
    const filtered: Need[] = this.needs.filter(need => need.name !== newNeed.name);
    if(filtered.length === this.needs.length){
      this.addNeed(newNeed);
    }
    else{
      this.needs = filtered;
      this.needService.updateNeed(newNeed)
      .subscribe(()=> this.needs.push(newNeed));
    }
    this.selectNeed(this.voidNeed);
  }
}
