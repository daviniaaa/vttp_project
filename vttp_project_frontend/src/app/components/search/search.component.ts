import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataObject, EventDetails } from 'src/app/models';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchResults: DataObject[] = [];

  constructor(private activatedRoute: ActivatedRoute, private eventSvc: EventService) {}

  ngOnInit() {
    const params = this.activatedRoute.snapshot.queryParams["keyword"];
    console.log("params >> " + params);

    this.eventSvc.search(params).then( result => {
      console.log("search result >> " + result);
      this.searchResults = result as DataObject[];
    })
  }

}
