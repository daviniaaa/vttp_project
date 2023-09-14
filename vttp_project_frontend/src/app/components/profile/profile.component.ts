import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataObject, UserData } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: UserData = { email: "", userPassword: "" };
  events: DataObject[] = [];
  defaultImage: string = "https://vttp-davinia.sgp1.digitaloceanspaces.com/miniproject/profile.png";
  mobile: boolean = false;

  constructor(private userSvc: UserService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.mobile = window.innerWidth < 600;

    const profileId = this.activatedRoute.snapshot.params['profileId'];
    this.userSvc.getProfile(profileId).then(user => {
      this.profile = user as UserData;
    })

    this.userSvc.getEventsByUser(profileId).then(data => {
      console.log(data);
      this.events = data as DataObject[];
    })

  }

}
