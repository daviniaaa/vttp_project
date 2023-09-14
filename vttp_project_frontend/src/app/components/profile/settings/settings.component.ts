import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { UserSettings } from 'src/app/models';
import { TokenService } from 'src/app/services/token.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  accountFocused: string = "settings-option focused";
  notificationsFocused: string = "settings-option";
  advancedFocused: string = "settings-option";
  settings!: UserSettings;
  isLoading: boolean = true;

  constructor(private userSvc: UserService, private router: Router, private tokenSvc: TokenService) {}

  ngOnInit() {

    setTimeout(() => {
      this.userSvc.getUserSettings(this.currentUserId).then(data => {
        console.log(data);
        this.settings = data as UserSettings;

        this.isLoading = false;

      }).catch(error => {
        if (this.userSvc.getCurrentUser() != null && this.tokenSvc.getAuthToken == null) {
          alert("Your login session has expired. Please log in again.");
          this.userSvc.currentUser = { email: "", userPassword: "" };
          this.userSvc.setCurrentUser(null);
        }
        this.router.navigate(['/login']);
      })


    }, 2000);


  }

  focus(s: string) {
    switch (s) {
      case "account":
        this.accountFocused  = "settings-option focused";
        this.notificationsFocused = "settings-option";
        this.advancedFocused = "settings-option";
        break;
      case "settings":
        this.accountFocused  = "settings-option";
        this.notificationsFocused = "settings-option focused";
        this.advancedFocused = "settings-option";
        break;
      case "advanced":
        this.accountFocused  = "settings-option";
        this.notificationsFocused = "settings-option";
        this.advancedFocused = "settings-option focused";
        break;
      default:
        console.error("focus() called but no valid option");
    }
  }

  get currentUserId(): string {
    return this.userSvc.currentUser.userDataId? this.userSvc.currentUser.userDataId : "";
  }
}
