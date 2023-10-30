import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { NEEDS } from '../dummy-needs';

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
export class CupboardComponent {

  needs: Need[] = [];
  selectedNeed: Need = {name: "", type: "", cost: 0, quantity: 0};

  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location
  ) {
    this.needs = NEEDS;
  }

  // TODO: Add a getNeeds function to facilitate getting all
  // stored needs on startup

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
    this.needService.addNeed(need)
      .subscribe(need => this.needs.push(need));
  }

  /**
   * deleteNeed: deletes the given need from data storage
   * @param delNeed : need to delete
   */
  deleteNeed(delNeed: Need): void {
    this.needs = this.needs.filter(need => need.name !== delNeed.name);
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
    name = name.trim();
    type = type.trim();
    if (!name || !type) { return; }
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
  }
}
