import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {
  errorMsg: string = "";
  errorStatus: number = 0;

  constructor() { }
}
