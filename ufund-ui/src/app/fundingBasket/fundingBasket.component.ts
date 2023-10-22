import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { MessageService } from '../message.service';

@Component({
    selector: 'app-fundingBasket',
    templateUrl: './fundingBasket.component.html',
    styleUrls: [ './fundingBasket.component.css' ]
  })
  export class DashboardComponent implements OnInit {
    needs: Need[] = [];
  
    constructor(private needService: NeedService) { }
  
    ngOnInit(): void {
      this.getNeeds();
    }
  
    getNeeds(): void {
      this.needService.getCupboard();
        .subscribe(needs => this.needs = needs.slice(1, 5));
    }
    add(need: Need): void {
      this.needs = this.needs.filter(h => h !== need);
      this.needService.addNeed(need).subscribe();
    }

    delete(need: Need): void {
        this.needs = this.needs.filter(h => h !== need);
        this.needService.deleteNeed(need.name).subscribe();
    }
}