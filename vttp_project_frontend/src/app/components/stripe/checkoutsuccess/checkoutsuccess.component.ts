import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkoutsuccess',
  templateUrl: './checkoutsuccess.component.html',
  styleUrls: ['./checkoutsuccess.component.css']
})
export class CheckoutsuccessComponent implements OnInit {

  redirectTime: number = 5;
  intervalId: any;

  constructor(private router: Router) {}

  ngOnInit() {
    // console.log(this.redirectTime);
    this.intervalId = setInterval(() => {this.minusTime()}, 1000);

    setTimeout(() => {this.redirect()}, 5000 );

  }

  minusTime() {
    this.redirectTime = (this.redirectTime - 1);
    // console.log(this.redirectTime);
  }

  redirect() {
    clearInterval(this.intervalId);
    this.router.navigate(['/']);
  }

}
