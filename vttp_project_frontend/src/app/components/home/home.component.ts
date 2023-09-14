import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { DataObject } from 'src/app/models';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-home',
  animations: [
    trigger('openClose', [
      state('open', style({ top: '40vh' })),
      state('closed', style({ top: '45vh'})),
      transition('open => closed', [
        animate('1.5s ease-out')
      ])
    ]),
    trigger('fadeOut', [
      state('start', style({ opacity: 1 })),
      state('end', style({ opacity: 0})),
      transition('start => end', [
        animate('1.5s ease-in')
      ])
    ])
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isOpen: boolean = true;
  noFade: boolean = true;
  contentPresent = false;
  imagePresent: boolean = true;

  events: DataObject[] = [];
  eventsShopping: DataObject[] = [];
  eventsAttractions: DataObject[] = [];
  eventsArts: DataObject[] = [];
  eventsSports: DataObject[] = [];
  eventsOthers: DataObject[] = [];

  constructor(private eventSvc: EventService) {}

  ngOnInit() {
    setTimeout(() => {
      this.loadPage();
    }, 100);


    setTimeout(() => {
      this.noFade = false;
    }, 2000);

    setTimeout(() => {
      this.contentPresent = true;
    }, 3400);

    setTimeout(() => {
      this.imagePresent = false;
    }, 3600);


    this.eventSvc.getEvents().then(e => {
    // this.eventSvc.getExternalEvents().then(e => {
      // console.log(e); // returns an array

      this.events = e as DataObject[];
      // for(let ev of this.events) {
      //   this.eventsOthers.push(ev);
      // }

      for(let ev of this.events) {
        switch(ev.type.toLowerCase()) {
          //Shopping, History & Culture, Arts,Attractions, Sports, Nature & Wildlife
          case "shopping":
            this.eventsShopping.push(ev);
            break;
          case "attractions":
            this.eventsAttractions.push(ev);
            break;
          case "arts":
            this.eventsArts.push(ev);
            break;
          case "sports":
            this.eventsSports.push(ev);
            break;
          default:
            this.eventsOthers.push(ev);
            break;
        }
      }


    })
  }

  image(uuid: string) {
    this.eventSvc.getImage(uuid).then(
      data => {this.createImageFromBlob(data);
      }, error => {
        console.log(error);
      }
    )
  }

  imageToShow: any;
  createImageFromBlob(image: Blob): any {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
       this.imageToShow = reader.result;
       return reader.result;
    }, false);

    if (image) {
       reader.readAsDataURL(image);
    }
  }

  loadPage() {
    this.isOpen = false;
  }

}
