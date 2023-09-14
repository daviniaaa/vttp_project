import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataObject } from 'src/app/models';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-external',
  templateUrl: './external.component.html',
  styleUrls: ['./external.component.css']
})
export class ExternalComponent {
  constructor(private activatedRoute: ActivatedRoute, private eventSvc: EventService,
    private router: Router) {}

  eventPayload: DataObject[] = [];
  event!: DataObject;

  ngOnInit() {
    const eventId = this.activatedRoute.snapshot.params['eventId'];

    this.eventSvc.getExternalEventByUuid(eventId).then(e => {
      this.eventPayload = e as DataObject[];
      console.log(this.eventPayload[0]);
      this.event = this.eventPayload[0];

      this.event.body = this.event.body.replaceAll("\<p>", "").replaceAll("\</p>", "")
        .replaceAll("\<br>", "").replaceAll("\<i>", "").replaceAll("\</i>", "")
        .replaceAll("amp;", "").replaceAll("&g", "").replaceAll("&l", "").replaceAll("&nb", "")
        .replaceAll("t;", "").replaceAll("sp;", "")

      this.eventSvc.currentEventLat = parseFloat(this.event.location.latitude);
      this.eventSvc.currentEventLng = parseFloat(this.event.location.longitude);

    })

  }

  goToCreateBoothPage() {
    this.router.navigate(['/createbooth', this.event.uuid]);
  }
}
