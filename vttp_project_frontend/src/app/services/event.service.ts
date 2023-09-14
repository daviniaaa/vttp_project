import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  currentEventLat = 0;
  currentEventLng = 0;


  constructor(private httpClient: HttpClient) { }

  getExternalEvents() {
    return firstValueFrom(this.httpClient.get('/api/external'));//, { responseType: 'text' }));
  }
  getExternalEventByUuid(uuid: string) {
    return firstValueFrom(this.httpClient.get(`/api/external/${uuid}`));//, { responseType: 'text' }));
  }

  getEvent(id: string) {
    // return firstValueFrom(this.httpClient.get('/api/event', { params: { eventId: id } }));
    return firstValueFrom(this.httpClient.get(`/api/event/${id}`));
  }

  search(keyword: string) {
    const params = new HttpParams().set("keyword", keyword);
    return firstValueFrom(this.httpClient.get('/api/search', { params }));
  }

  getImage(uuid: string) {
    return firstValueFrom(this.httpClient.get(`/api/external/image/${uuid}`, { responseType: 'blob' }));
  }
}
