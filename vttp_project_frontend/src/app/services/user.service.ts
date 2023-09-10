import { UserData } from './../models';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject, firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // @Output() getLoggedInName: EventEmitter<any> = new EventEmitter();
  // loggedIn: boolean = false;
  currentUser: UserData = { email: "", userPassword: "" };

  constructor(private httpClient: HttpClient) { }

  createAccount(u: UserData) {
    return firstValueFrom(this.httpClient.post('/api/create', u));//, { responseType: 'text' }));
  }

  login(u: UserData) {
    return firstValueFrom(this.httpClient.post('/api/login', u));//, { responseType: 'text' }));
  }

  getProfile(id: string) {
    return firstValueFrom(this.httpClient.get(`/api/profile/${id}`));
  }
}
