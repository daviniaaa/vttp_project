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

  getEvents() {
    return firstValueFrom(this.httpClient.get('/api/home'));
  }
  getExternalEvents() {
    return firstValueFrom(this.httpClient.get('/api/external'));
  }
  getExternalEventByUuid(uuid: string) {
    return firstValueFrom(this.httpClient.get(`/api/external/${uuid}`));
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
