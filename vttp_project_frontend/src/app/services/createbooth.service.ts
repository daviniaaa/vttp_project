import { firstValueFrom } from 'rxjs';
import { BoothDetails } from './../models';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CreateboothService {

  constructor(private httpClient: HttpClient) { }

  createBooth(b: BoothDetails) {
    return firstValueFrom(this.httpClient.post('/api/createbooth', b, { responseType: 'text' }));
  }
}
