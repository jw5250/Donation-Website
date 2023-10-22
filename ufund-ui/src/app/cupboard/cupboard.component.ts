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
export class CupboardComponent {
  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location
  ) {}

  needs: Need[] = [];

  addNeed(name: String, type: String, cost: Number, quantity: Number ): void{
    name = name.trim();
    type = type.trim();
    if (!name || !type) { return; }
    const need  = <Need>({name: name, type: type, cost: cost, quantity: quantity});
    this.needService.addNeed(need)
      .subscribe(need => this.needs.push(need));
    
  }
}
