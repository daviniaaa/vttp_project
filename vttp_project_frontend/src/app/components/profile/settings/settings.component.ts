import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent {
  accountFocused: string = "settings-option focused";
  notificationsFocused: string = "settings-option";
  advancedFocused: string = "settings-option";

  constructor(private _formBuilder: FormBuilder) {}

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
}
