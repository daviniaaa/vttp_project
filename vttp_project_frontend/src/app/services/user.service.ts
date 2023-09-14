import { UserData, UserSettings } from './../models';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Subject, firstValueFrom } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // @Output() getLoggedInName: EventEmitter<any> = new EventEmitter();
  // loggedIn: boolean = false;
  currentUser: UserData = { email: "", userPassword: "" };

  constructor(private httpClient: HttpClient, private tokenSvc: TokenService) { }

  createAccount(u: UserData) {
    return firstValueFrom(this.httpClient.post('/api/create', u));//, { responseType: 'text' }));
  }

  login(u: UserData) {
    // const headers: HttpHeaders = new HttpHeaders({Authorization: 'Basic '
    // + window.btoa(u.email + ':' + u.userPassword)})
    return firstValueFrom(this.httpClient.post('/api/login', u));//;, { headers }));//, { responseType: 'text' }));
  }

  getProfile(id: string) {
    return firstValueFrom(this.httpClient.get(`/api/profile/${id}`));
  }

  getEventsByUser(id: string) {
    return firstValueFrom(this.httpClient.get(`/api/profile/${id}/events`));
  }

  getUserSettings(id: string) {
    return firstValueFrom(this.tokenSvc.request("get", `/api/settings/${id}`));
  }

  updateUserSettings(u: UserSettings) {
    return firstValueFrom(this.tokenSvc.request("put", "/api/settings", u));
  }

  getCurrentUser(): string | null {
    return window.localStorage.getItem("current_user_data_id");
  }

  setCurrentUser(user: UserData | null): void {
    if (user !== null) {
      window.localStorage.setItem("current_user_data_id", JSON.stringify(user.userDataId));
      window.localStorage.setItem("current_user_display_name", JSON.stringify(user.displayName));
      window.localStorage.setItem("current_user_email", JSON.stringify(user.email));
      window.localStorage.setItem("current_user_image_url", JSON.stringify(user.imageUrl));
    }
    else {
      window.localStorage.removeItem("current_user_data_id");
      window.localStorage.removeItem("current_user_display_name");
      window.localStorage.removeItem("current_user_email");
      window.localStorage.removeItem("current_user_image_url");

    }
  }

  uploadProfilePicture(form: FormData, id: string) {
    return firstValueFrom(this.tokenSvc.request("put", `/api/upload/${id}`, form));
  }

  deleteUser(id: string) {
    return firstValueFrom(this.tokenSvc.request("delete", `/api//delete/profile/${id}`));
  }
}
