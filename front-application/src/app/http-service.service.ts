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

  async get(path: String, params?: HttpParams, headers?: HttpHeaders) {
    return await this.http.get(`${this.base}${path}`, {params: params, headers, withCredentials: true}).toPromise()
  }
  async post(path: String, params?: string, headers?: HttpHeaders) {
    return await this.http.post(`${this.base}${path}`, params, { headers: headers, withCredentials: true}).toPromise()
  }
  async put(path: String, params?: HttpParams, headers?: HttpHeaders) {
    return await this.http.put(`${this.base}${path}`, {params: params, headers, withCredentials: true}).toPromise()
  }
  async delete(path: String, headers?: HttpHeaders) {
    return await this.http.delete(`${this.base}${path}`, { headers, withCredentials: true }).toPromise()
  }
}

