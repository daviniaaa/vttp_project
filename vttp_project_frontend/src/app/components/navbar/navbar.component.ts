import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Params, Router } from '@angular/router';
import { UserData } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  searchForm!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private userSvc: UserService,
    private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.searchForm = this.fb.group({
      searchInput: this.fb.control<string>('', [ Validators.required ])
    })
  }

  search() {
    const searchInput: string = this.searchForm.value.searchInput;

    if (searchInput) {
      const queryParams: Params = { keyword: searchInput };

      this.router.navigate(["/search"],  { queryParams })
        .then(() => {
          window.location.reload();
        });
    }

  }

  get currentUsername(): string {
    return this.userSvc.currentUser.displayName? this.userSvc.currentUser.displayName : "Login";
  }

  get userId(): string {
    return this.userSvc.currentUser.userDataId? this.userSvc.currentUser.userDataId : "";
  }

  goToProfile() {
    if (this.userSvc.currentUser.userDataId != undefined) {
      const userId: string = this.userSvc.currentUser.userDataId;
      this.router.navigate(['/profile', userId]);
    }
  }

  goToSettings() {
    if (this.userSvc.currentUser.userDataId != undefined) {
      const userId: string = this.userSvc.currentUser.userDataId;
      this.router.navigate(['/profile', userId, 'settings']);
    }
  }

  signOut() {
    this.userSvc.currentUser = { email: "", userPassword: "" };
    this.router.navigate(['/']);
    this.snackBar.open("Sign out successful", "DISMISS");
  }
}
