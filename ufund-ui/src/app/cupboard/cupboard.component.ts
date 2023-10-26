import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

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
  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location
  ) {}

  needs: Need[] = [];
  
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
    this.needs = this.needs.filter(need => need !== delNeed);
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
