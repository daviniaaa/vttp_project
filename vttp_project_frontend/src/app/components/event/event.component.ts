import { TimePeriodObject } from './../../models';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataObject } from 'src/app/models';
import { BoothService } from 'src/app/services/booth.service';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
  constructor(private activatedRoute: ActivatedRoute, private eventSvc: EventService,
    private router: Router, private boothSvc: BoothService) {}

  eventPayload: DataObject[] = [];
  event!: DataObject;
    // assigning empty object removes the TypeError but the map does not load because it is
    // initialised before values are assigned
    // = { uuid: "", name: "", type:"", tags:[], description:"", body:"",
    // location:{ latitude:"", longitude:""}, address:{ block:"", streetName:"",
    // buildingName: "", postalCode:""}, eventDetailList:[{timePeriod: []}], thumbnails:[]};

  ngOnInit() {
    const eventId = this.activatedRoute.snapshot.params['eventId'];

    this.eventSvc.getEvent(eventId).then(e => {
      console.log(e);

      this.event = e as DataObject;

      this.event.body = this.event.body.replaceAll("\<p>", "").replaceAll("\</p>", "")
        .replaceAll("\<br>", "").replaceAll("\<i>", "").replaceAll("\</i>", "")
        .replaceAll("amp;", "").replaceAll("&g", "").replaceAll("&l", "").replaceAll("&nb", "")
        .replaceAll("t;", "").replaceAll("sp;", "")

      this.eventSvc.currentEventLat = parseFloat(this.event.location.latitude);
      this.eventSvc.currentEventLng = parseFloat(this.event.location.longitude);

      this.boothSvc.currentEventId = eventId;

    })

  }

  goToCreateBoothPage() {
    this.router.navigate(['/createbooth', this.event.uuid]);
  }

}
