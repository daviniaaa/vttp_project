import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit {
  constructor (private eventSvc: EventService) {}
  map!: google.maps.Map;
  @ViewChild("map") mapElement: any;

  ngAfterViewInit() {
    console.log("latitude >> " + this.latitude);

    const mapProperties = {
      center: new google.maps.LatLng(this.latitude, this.longitude),
      zoom: 17,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    this.map = new google.maps.Map(this.mapElement.nativeElement, mapProperties);
      const marker = new google.maps.Marker({
      position: new google.maps.LatLng(this.latitude, this.longitude),
      map: this.map
    })

  }

  get latitude() {
    return this.eventSvc.currentEventLat;
  }

  get longitude() {
    return this.eventSvc.currentEventLng;
  }



}
