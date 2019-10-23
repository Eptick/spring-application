import { Injectable } from '@angular/core';
import { HttpService } from './http-service.service';
import { HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  errorMessage: String;
  errorCode: number;
  authenticated = false;

  constructor(public httpService: HttpService) {
    this.authenticated = localStorage.getItem('loggedIn') ? true : false;
  }

  async login(username, password) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
    try {
      let params = new HttpParams().set("username", username).set( "password", password)
      let d  = await this.httpService.post('/login', params, headers);
      this.loggedIn()
      return { success: true }
    } catch (error) {
      return { success: false,  message: this.errorMessage, errorCode: this.errorCode}
    }

  }

  loggedIn () {
    this.authenticated = true
    localStorage.setItem('loggedIn', "true")
  }

  loggedOut() {
    this.authenticated = false;
    localStorage.removeItem('loggedIn')
  }

  async logout() {
    try {
      await this.httpService.get('/logout');
      this.loggedOut();
      return { success: true }
    } catch (error) {
      return { success: false,  message: error.message}
    }

  }
}
