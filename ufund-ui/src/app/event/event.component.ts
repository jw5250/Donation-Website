import { Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { EventService } from '../services/event.service';
import { Event } from '../dataClasses/event';
@Component({
    selector: 'app-event',
    templateUrl: './event.component.html',
    styleUrls: [ './event.component.css' ]
  })
  export class EventComponent implements OnInit {
  MAXLEN: number = 30;
  events: Event[] = [];
  eventManagementErrorMessage : string[] = [];
  voidEvent: Event = {name: "", date: "", time: ""};
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
                  eventData.item(2).value);
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
   * @param time : time of event
   */
  editEvent(eventName: String, eventDate: String, eventTime: String): void{
      let foundError : boolean = false;
      this.eventManagementErrorMessage = [];
      eventName = eventName.trim();
      eventDate = eventDate.trim();
      eventTime = eventTime.trim();
      if (!eventName || !eventDate || !eventTime) { return; }
      if(eventName.length > this.MAXLEN){
        this.eventManagementErrorMessage.push("Name is greater than " + this.MAXLEN + " characters.");
        foundError = true;
      }
      if(eventDate.length > this.MAXLEN){
        this.eventManagementErrorMessage.push("Date is greater than " + this.MAXLEN + " characters.");
        foundError = true;
      }
      if(eventTime.length > this.MAXLEN){
        this.eventManagementErrorMessage.push("Time is greater than " + this.MAXLEN + " characters.");
        foundError = true;
      }
      this.selectEvent(this.voidEvent);
      const eventData =document.getElementsByName("eventInput") as NodeListOf<HTMLInputElement>;
      for(let i = 0; i < eventData.length;i++){
        eventData.item(i).value = "";
      }
      if(foundError === true){
        return;
      }

      const newEvent : Event = <Event>({name: eventName, date: eventDate, time: eventTime});
      console.log(newEvent);
      const filtered: Event[] = this.events.filter(event => event.name !== newEvent.name);
      if(filtered.length === this.events.length){
        this.addEvent(newEvent);
      }else{
        this.events = filtered;
        this.eventService.updateEvent(newEvent)
        .subscribe(()=> this.events.push(newEvent));
      }
      this.selectEvent(this.voidEvent);
    }
  }