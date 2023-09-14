import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { BoothDetails } from '../models';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class BoothService {

  currentEventId: string = "";

  constructor(private httpClient: HttpClient, private tokenSvc: TokenService) { }

  // loadCreateBooth() {
    // return firstValueFrom(this.httpClient.get('/api/event', { params: { eventId: id } }));
    // return firstValueFrom(this.httpClient.get("/api/createbooth"));
  // }
  loadCreateBooth() {
    return firstValueFrom(this.tokenSvc.request("get", "/api/createbooth"));
  }

  createBooth(b: BoothDetails, eventId: string) {
    return firstValueFrom(this.tokenSvc.request("post", `/api/createbooth/${eventId}`, b));
  }

  getBooths(eventId: string) {
    this.currentEventId = eventId;
    return firstValueFrom(this.httpClient.get(`/api/booths/${eventId}`));
  }


}
