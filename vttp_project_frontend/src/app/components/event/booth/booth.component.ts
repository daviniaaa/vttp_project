import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { BoothDetails } from 'src/app/models';
import { BoothService } from 'src/app/services/booth.service';

@Component({
  selector: 'app-booth',
  templateUrl: './booth.component.html',
  styleUrls: ['./booth.component.css']
})
export class BoothComponent implements OnInit, AfterViewInit {
  booths: BoothDetails[] = [];
  displayedColumns: string[] = ['boothName', 'description'];
  dataSource!: MatTableDataSource<BoothDetails>;

  constructor(private boothSvc: BoothService, private router: Router) {}

  ngOnInit() {
    this.boothSvc.getBooths(this.eventId).then(data => {
      console.log(data);
      this.booths = data as BoothDetails[];

      this.dataSource = new MatTableDataSource<BoothDetails>(this.booths);
    })
  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  get eventId() {
    return this.boothSvc.currentEventId;
  }

  goToProfile(id: string) {
    this.router.navigate(['/profile', id]);
  }

}
