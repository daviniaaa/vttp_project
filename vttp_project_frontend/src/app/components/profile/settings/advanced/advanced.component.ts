import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeletedialogComponent } from 'src/app/components/deletedialog/deletedialog.component';

@Component({
  selector: 'app-advanced',
  templateUrl: './advanced.component.html',
  styleUrls: ['./advanced.component.css']
})
export class AdvancedComponent {

  constructor(private dialog: MatDialog) {}

  deleteUser() {
    this.dialog.open(DeletedialogComponent);

  }
}
