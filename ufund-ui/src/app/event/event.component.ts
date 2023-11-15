import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { EventService } from '../services/event.service';
import { User } from '../dataClasses/user';
import { Event } from '../dataClasses/event';
@Component({
    selector: 'app-event',
    templateUrl: './event.component.html',
    styleUrls: [ './event.component.css' ]
  })
  export class EventComponent implements OnInit {

  events: Event[] = [];
  voidEvent: Event = {name: "", date: "", time: 0};
  selectedEvent: Event = this.voidEvent;

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private location: Location
  ) {}

  ngOnInit(): void{
    this.getEvents();
    this.selectEvent(this.voidEvent);
  }

  /**
   * getEvents(): retrieves all stored event data from the server to display
   */
  getEvents(): void{
    this.eventService.getWholeEvent()
      .subscribe(events => this.events = events);
  }

  /**
   * selectEvent(): sets the selected event based on user input
   * @param event : event that will be selected
   */
  selectEvent(event: Event): void{
    this.selectedEvent = event;
  }
  /**
   * getNewEventData(): takes data from the UI input and then uses it to
   * update the events list
   * 
   */
  getNewEventData(): void{
    const eventData =document.getElementsByName("eventInput") as NodeListOf<HTMLInputElement>;
    if(eventData.item(0).value !== "" ){
    this.selectEvent(this.voidEvent);
    this.editEvent(eventData.item(0).value,
                  eventData.item(1).value,
                  parseInt(eventData.item(2).value))
    }
  }
  /**
   * addEvent(): takes a event object and adds it to data storage
   * @param event 
   */
  addEvent(event: Event): void{
    this.selectEvent(this.voidEvent);
    this.eventService.addEvent(event)
      .subscribe(event => this.events.push(event));
  }

  /**
   * deleteEvent: deletes the given event from data storage
   * @param delEvent : event to delete
   */
  deleteEvent(delEvent: Event): void {
    this.events = this.events.filter(event => event.name !== delEvent.name);
    this.selectEvent(this.voidEvent);
    this.eventService.deleteEvent(delEvent.name).subscribe();
  }

  
  /**
   * editEvent: forms a event based on the parameters, then adds or updates it
   *  depending on if it exists within the system.
   * @param name : name of event to be updated
   * @param type : type of event
   * @param cost : cost of event
   */
  editEvent(name: string, date: string, time: number): void{
    name = name.trim();
    if (!name) { return; }
    this.selectEvent(this.voidEvent);
    const newEvent  = <Event>({name: name, date: date, time: time});
    const filtered: Event[] = this.events.filter(event => event.name !== newEvent.name);
    if(filtered.length === this.events.length){
      this.addEvent(newEvent);
    }
    else{
      this.events = filtered;
      this.eventService.updateEvent(newEvent)
      .subscribe(()=> this.events.push(newEvent));
    }
    this.selectEvent(this.voidEvent);
  }
  }