import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventDetails } from 'src/app/models';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private eventSvc: EventService) {}
  event: EventDetails = { eventDetailsId: "", userDataId: "", title: "", description: "",
    imageUrl: "", category: "" };

  ngOnInit() {
    const eventId = this.activatedRoute.snapshot.params['eventId'];

    this.eventSvc.getEvent(eventId).then(e => {
      this.event = e as EventDetails;
      console.log(this.event.title);
    })

  }



}
