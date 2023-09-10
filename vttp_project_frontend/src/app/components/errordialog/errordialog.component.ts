import { Component, Inject, OnInit } from '@angular/core';
import { ErrorService } from 'src/app/services/error.service';

@Component({
  selector: 'app-errordialog',
  templateUrl: './errordialog.component.html',
  styleUrls: ['./errordialog.component.css']
})
export class ErrordialogComponent implements OnInit {

  errorMsg: string = "";
  errorStatus: number = 0;

  constructor(private errorSvc: ErrorService) { }

  ngOnInit() {
    this.errorMsg = this.errorSvc.errorMsg;
    this.errorStatus = this.errorSvc.errorStatus;
  }

}
