import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserData } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';
import { ErrordialogComponent } from '../errordialog/errordialog.component';
import { ErrorService } from 'src/app/services/error.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder, private userSvc: UserService, private router: Router,
    private dialog: MatDialog, private errorSvc: ErrorService) {}

  loginForm!: FormGroup;

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      password: this.fb.control<string>('', [ Validators.required ])
    })
  }

  login() {
    let u: UserData;

    u = this.loginForm.value as UserData;
    this.userSvc.login(u)
      .then(data => {
        console.log("data >> " + data);
        this.userSvc.currentUser = data as UserData;
        console.log("currentUser name >> " + this.userSvc.currentUser.displayName);

        this.router.navigate(['/']);
      })
      .catch(error => {
        console.log(error);

        this.errorSvc.errorMsg = error.error;
        this.errorSvc.errorStatus = error.status;

        this.dialog.open(ErrordialogComponent);
      })
  }

}
