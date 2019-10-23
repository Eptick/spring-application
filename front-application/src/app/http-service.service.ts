import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { headersToString } from 'selenium-webdriver/http';

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
      let data = await this.get(`/company`)
      return data
    } catch (error) {
      console.error(error);
    }
  }

  objectToHttpParams(object: Object): HttpParams {
    let params = new HttpParams()
      for(let key in object) {
        params.set(key, object[key])
      }
      return params;
  }

  async get(path: String, params?: HttpParams, headers?: HttpHeaders) {
    return await this.http.get(`${this.base}${path}`, {params: params, headers}).toPromise()
  }
  async post(path: String, params?: HttpParams, headers?: HttpHeaders) {
    return await this.http.post(`${this.base}${path}`, params.toString(), { headers: headers}).toPromise()
  }
  async put(path: String, params?: HttpParams, headers?: HttpHeaders) {
    return await this.http.put(`${this.base}${path}`, {params: params, headers}).toPromise()
  }
  async delete(path: String, headers?: HttpHeaders) {
    return await this.http.delete(`${this.base}${path}`, { headers}).toPromise()
  }
}

