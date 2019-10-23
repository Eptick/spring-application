import { Injectable } from '@angular/core';
import { HttpService } from '../http-service.service';
import { Company } from '../interfaces/Company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(public httpService: HttpService) {}

  async getCompanies (): Promise<Company[]> {
    let data;
    try {
      data = await this.httpService.get('/company')
    } catch (error) {
      data = {}
    }
    return data;
  }
}
