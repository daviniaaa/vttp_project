import { Component, OnInit } from '@angular/core';
import { EventDetails } from 'src/app/models';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  events: EventDetails[] = [];
  eventsLifestyle: EventDetails[] = [];
  eventsFood: EventDetails[] = [];
  eventsMusic: EventDetails[] = [];
  eventsSports: EventDetails[] = [];

  constructor(private eventSvc: EventService) {}

  ngOnInit() {
    this.eventSvc.getEvents().then(e => {
      // console.log(e); // returns an array

      this.events = e as EventDetails[];

      for(let ev of this.events) {
        switch(ev.category) {
          case "lifestyle":
            this.eventsLifestyle.push(ev);
            break;
          case "food":
            this.eventsFood.push(ev);
            break;
          case "music":
            this.eventsMusic.push(ev);
            break;
          case "sports":
            this.eventsSports.push(ev);
            break;
          default:
            console.error("No valid category");
            break;
        }
      }

      console.log(this.eventsLifestyle);
    })
  }
}
