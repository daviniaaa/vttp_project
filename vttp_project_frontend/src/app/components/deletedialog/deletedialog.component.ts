import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-deletedialog',
  templateUrl: './deletedialog.component.html',
  styleUrls: ['./deletedialog.component.css']
})
export class DeletedialogComponent {
  constructor(private dialogRef: MatDialogRef<DeletedialogComponent>, private router: Router,
    private userSvc: UserService, private tokenSvc: TokenService) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  delete(){
    this.userSvc.currentUser.userDataId? this.userSvc.deleteUser( this.userSvc.currentUser.userDataId)
      : this.router.navigate(['/']);

    this.dialogRef.close();

    this.userSvc.currentUser = { email: "", userPassword: "" };

    this.tokenSvc.setAuthToken(null);
    this.userSvc.setCurrentUser(null);


    this.router.navigate(['/']);
  }

}
