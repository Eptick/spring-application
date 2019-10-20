import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { HttpService } from '../http-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  loginForm: FormGroup;
  matcher: ErrorStateMatcher;

  constructor(public httpService: HttpService) { 
    this.loginForm = new FormGroup({
       firstName: new FormControl()
    });
    this.matcher = new ErrorStateMatcher();
  }

  ngOnInit() {
    console.log("aaa")
    this.logCompanies();
  }

  async logCompanies() {
    console.log(await this.httpService.getCompanies());
  }

}
