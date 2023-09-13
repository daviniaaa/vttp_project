import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserData } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';
import { ErrordialogComponent } from '../errordialog/errordialog.component';
import { ErrorService } from 'src/app/services/error.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder, private userSvc: UserService, private router: Router,
    private dialog: MatDialog, private errorSvc: ErrorService, private tokenSvc: TokenService) {}

  loginForm!: FormGroup;

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      userPassword: this.fb.control<string>('', [ Validators.required ])
    })
  }

  login() {
    let u: UserData;

    u = this.loginForm.value as UserData;
    this.userSvc.login(u)
      .then(data => {
        console.log("data >> " + JSON.stringify(data));
        this.userSvc.currentUser = data as UserData;
        console.log("currentUser name >> " + this.userSvc.currentUser.displayName);

        console.log("currentUser token >> " + this.userSvc.currentUser.token);
        this.tokenSvc.setAuthToken(this.userSvc.currentUser.token? this.userSvc.currentUser.token : null);

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
