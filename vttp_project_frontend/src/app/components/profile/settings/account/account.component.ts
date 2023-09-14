import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {
  @ViewChild('image') image!: ElementRef;

  imageValid: boolean = false;
  defaultImage: string = "https://vttp-davinia.sgp1.digitaloceanspaces.com/miniproject/profile.png";

  constructor(private userSvc: UserService, private router: Router) {}

  imageSelected() {
    this.imageValid = true;
  }

  post() {
    if (!this.imageValid) {
      alert("Please select an image to upload.")
      return;
    }

    const form = new FormData();
    const file = this.image.nativeElement.files[0] as File;
    form.set("file", file);

    this.userSvc.uploadProfilePicture(form, this.currentUserDataId?this.currentUserDataId:"")
    .then(data => {
      console.log(data);
    }).catch(error => {
      console.error(error);

      this.router.navigate(['/profile', this.currentUserDataId]);
    })
  }

  get currentImageUrl() {
    return this.userSvc.currentUser.imageUrl;
  }

  get currentUserDataId() {
    return this.userSvc.currentUser.userDataId;
  }

}
