import { BoothDetails } from './../../models';
import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { BoothService } from 'src/app/services/booth.service';
import { ErrorService } from 'src/app/services/error.service';
import { UserService } from 'src/app/services/user.service';
import { ErrordialogComponent } from '../errordialog/errordialog.component';

@Component({
  selector: 'app-createbooth',
  templateUrl: './createbooth.component.html',
  styleUrls: ['./createbooth.component.css']
})
export class CreateboothComponent implements OnInit {
  boothForm!: FormGroup;
  eventId: string = "";

  constructor(private fb: FormBuilder, private userSvc: UserService,
    private boothSvc: BoothService, private router: Router, private activatedRoute: ActivatedRoute,
    private dialog: MatDialog, private errorSvc: ErrorService) {}

  ngOnInit() {
      this.eventId = this.activatedRoute.snapshot.params['eventId'];

      this.boothSvc.loadCreateBooth().catch((error) => {
        console.error(error);

        if (this.userSvc.getCurrentUser() != null) {
          alert("Your login session has expired. Please log in again.");
          this.userSvc.currentUser = { email: "", userPassword: "" };
          this.userSvc.setCurrentUser(null);
        }

        this.router.navigate(['/login']);
      })


      this.boothForm = this.fb.group({
        boothName: this.fb.control<string>('', [ Validators.required ]),
        description: this.fb.control<string>('', [ Validators.required ])
      })
  }

  newBooth() {
    if (this.userSvc.currentUser.userDataId == null) {
      alert("You must be logged in to create a post.");
      this.router.navigate(['/login']);
    }

    let booth: BoothDetails = this.boothForm.value as BoothDetails;
    booth.userDataId = this.userSvc.currentUser.userDataId;

    this.boothSvc.createBooth(booth, this.eventId).then(data => {
      console.log(data);

      alert("Successfully posted!");
      this.router.navigate(['/events', this.eventId]);
    }).catch(error => {
      console.log(error);

      this.errorSvc.errorMsg = error.error.message;
      this.errorSvc.errorStatus = error.status;

      this.dialog.open(ErrordialogComponent);
    })

  }

}
