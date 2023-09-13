import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { BoothDetails } from '../models';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class BoothService {

  constructor(private httpClient: HttpClient, private tokenSvc: TokenService) { }

  loadCreateBooth() {
    // return firstValueFrom(this.httpClient.get('/api/event', { params: { eventId: id } }));
    return firstValueFrom(this.httpClient.get("/api/createbooth"));
  }
  loadCreateBoothh() {
    return firstValueFrom(this.tokenSvc.request("get", "/api/createbooth"));
  }

  createBooth(b: BoothDetails) {
    return firstValueFrom(this.httpClient.post('/api/createbooth', b, { responseType: 'text' }));
  }
}
