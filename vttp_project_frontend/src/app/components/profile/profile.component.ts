import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserData } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: UserData = { email: "", userPassword: "" };

  constructor(private userSvc: UserService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    const profileId = this.activatedRoute.snapshot.params['profileId'];
    this.userSvc.getProfile(profileId).then(user => {
      this.profile = user as UserData;
    })
  }

}
