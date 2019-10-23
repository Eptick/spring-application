import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
})
export class LoginPageComponent {
  loginForm: FormGroup;
  hide = true;

  formError = ''

  constructor(public authService: AuthService, private formBuilder: FormBuilder, private router: Router) { 
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(4)]]

      // email: ['', [Validators.required, Validators.email]],
    })
  }

  async submit() {
    try {
      if(this.loginForm.invalid) return
      const username = this.loginForm.get('username').value
      const password = this.loginForm.get('password').value
      let data = await this.authService.login(username, password);
      if(data.success) {
        this.router.navigate(["/overview"])
      } else {
        this.formError = "Wrong username or password"
      }
    } catch (error) {
      this.formError = "Something went wrong"
    }
  }

}
