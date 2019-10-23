import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http-service.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  constructor(public httpService: HttpService) { }

  ngOnInit() {
    this.getData();
  }

  async getData() {
    console.log( await this.httpService.getCompanies() );
  }

}
