import { Component, Input, OnInit, Output } from '@angular/core';
import { UserSettings } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  emailNotif!: boolean;
  teleNotif!: boolean;
  @Input() settings!: UserSettings;

  constructor(private userSvc: UserService) {}
  ngOnInit() {
    this.emailNotif = this.settings.emailNotif;
    this.teleNotif = this.settings.teleNotif;

  }

  updateSettings() {

    setTimeout(() => {

      console.log("email >> " + this.emailNotif);
      console.log("tele >> " + this.teleNotif);

      const u: UserSettings = { userSettingsId : this.settings.userSettingsId,
        userDataId: this.settings.userDataId, emailNotif: this.emailNotif, teleNotif: this.teleNotif}
      this.userSvc.updateUserSettings(u).then(data => {
        console.log(data);
      })
    }, 5000)

  }



}
