import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { EventService } from '../services/event.service';
import { Event } from '../dataClasses/event';
@Component({
  selector: 'app-event-display',
  templateUrl: './event-display.component.html',
  styleUrls: ['./event-display.component.css']
})
export class EventDisplayComponent {
  events: Event[] = [];
  constructor(
    private eventService: EventService,
  ) {}

  ngOnInit(): void{
    this.getEvents();
  }
  getEvents(): void{
    this.eventService.getWholeEvent()
      .subscribe(events => this.events = events);
  }
}
