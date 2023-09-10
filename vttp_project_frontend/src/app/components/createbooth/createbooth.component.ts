import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CreateboothService } from 'src/app/services/createbooth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-createbooth',
  templateUrl: './createbooth.component.html',
  styleUrls: ['./createbooth.component.css']
})
export class CreateboothComponent implements OnInit {
  boothForm!: FormGroup;

  constructor(private fb: FormBuilder, private userSvc: UserService,
    private createBoothSvc: CreateboothService) {}

  ngOnInit() {
    this.boothForm = this.fb.group({
      boothName: this.fb.control<string>('', [ Validators.required ]),
      description: this.fb.control<string>('', [ Validators.required ])
    })
  }

  newBooth() {
    const boothName = this.boothForm.value.boothName;
    const description = this.boothForm.value.description;

    const userId = this.userSvc.currentUser;

  }

}
