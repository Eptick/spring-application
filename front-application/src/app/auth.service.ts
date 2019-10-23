import { Injectable } from '@angular/core';
import { HttpService } from './http-service.service';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  errorMessage: String;
  errorCode: number;
  loggedIn = false;

  constructor(public httpService: HttpService, public cookieService: CookieService) {
  }

  async login(username, password) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
    try {
      let params = new HttpParams().set("username", username).set( "password", password)
      let d  = await this.httpService.post('/login', params, headers);
      this.loggedIn = true
      return { success: true }
    } catch (error) {
      return { success: false,  message: this.errorMessage, errorCode: this.errorCode}
    }

  }

  async logout() {
    try {
      await this.httpService.get('/logout');
      this.loggedIn = false
      return { success: true }
    } catch (error) {
      return { success: false,  message: error.message}
    }

  }
}
