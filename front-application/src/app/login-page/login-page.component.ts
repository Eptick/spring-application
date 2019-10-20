import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent implements OnInit {
  loginForm: FormGroup;

  constructor() { 
    this.loginForm = new FormGroup({
       firstName: new FormControl()
    });
  }

  ngOnInit() {
    console.log("aaa")
  }

}
