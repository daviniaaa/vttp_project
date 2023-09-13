import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BoothService } from 'src/app/services/booth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-createbooth',
  templateUrl: './createbooth.component.html',
  styleUrls: ['./createbooth.component.css']
})
export class CreateboothComponent implements OnInit {
  boothForm!: FormGroup;

  constructor(private fb: FormBuilder, private userSvc: UserService,
    private boothSvc: BoothService, private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.boothSvc.loadCreateBoothh().catch((error) => {
      console.error(error);

      // const eventId = this.activatedRoute.snapshot.params['eventId'];
      // this.router.navigate(['/events', eventId]);
      this.router.navigate(['/login']);
    })

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
