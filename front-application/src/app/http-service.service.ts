import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class HttpService {

  base: String;

  constructor(public http: HttpClient) {
    this.base = environment.apiUrl
  }

  async getCompanies() {
    try {
      let data = await this.http.get(`${this.base}/company`).toPromise()
      return data
    } catch (error) {
      console.error(error);
    }
  }
}

