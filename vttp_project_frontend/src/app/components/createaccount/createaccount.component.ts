import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { UserData } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';
import { ErrordialogComponent } from '../errordialog/errordialog.component';
import { Router } from '@angular/router';
import { ErrorService } from 'src/app/services/error.service';

@Component({
  selector: 'app-createaccount',
  templateUrl: './createaccount.component.html',
  styleUrls: ['./createaccount.component.css']
})
export class CreateaccountComponent implements OnInit {

  constructor(private fb: FormBuilder, private userSvc: UserService, private dialog: MatDialog,
    private errorSvc: ErrorService, private router: Router) {}

  createForm!: FormGroup;
  errorMsg: string = "";
  errorStatus: number = 0;

  ngOnInit() {
    this.createForm = this.fb.group({
      displayName: this.fb.control<string>('', [ Validators.required ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      userPassword: this.fb.control<string>('', [ Validators.required ]),
      confirmPassword: this.fb.control<string>('', [ Validators.required ])
    })
  }

  createAccount() {
    let u: UserData;
    console.log("createForm name >> " + this.createForm.value.displayName);

    u = this.createForm.value as UserData;
    this.userSvc.createAccount(u)
      .then(data => {
        console.log("data >> " + data);

        // this.userSvc.loggedIn = true;
        // this.userSvc.currentUser = u.displayName;

        this.router.navigate(['/']);
      })
      .catch(error => {
        this.errorSvc.errorMsg = error.error;
        this.errorSvc.errorStatus = error.status;

        this.dialog.open(ErrordialogComponent);
    });
  }

}
