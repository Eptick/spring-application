import { Injectable } from '@angular/core';
import { User } from '../interfaces/User';
import { ResponseObject } from '../interfaces/ResponseObject';

import { HttpService } from '../http-service.service';
import { HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public httpService: HttpService) { }

  async getUsers (): Promise<User[]> {
    let data;
    try {
      data = await this.httpService.get('/user')
    } catch (error) {
      data = {}
    }
    return data;
  }

  async saveUser (user: User): Promise<ResponseObject> {
    let data;
    try {
      data = await this.httpService.post('/user', JSON.stringify(user), new HttpHeaders({"Content-Type": "application/json"}));
    } catch (error) {
      console.error(error);
      data = {success: false, message: 'Something went wrong'}
    }
    return data;
  }
}
