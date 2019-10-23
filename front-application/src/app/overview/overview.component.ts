import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../dataservices/company.service';
import { Company } from '../interfaces/Company';
import { User } from '../interfaces/User';
import { UserService } from '../dataservices/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ResponseObject } from '../interfaces/ResponseObject';

declare var window: any;

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  companyData: Company[] = [];
  companyColums: string[] = ['id', 'name'];
  hide = true;

  userData: User[] = [];
  userColums: string[] = 
    ['userId',
    'username',
    'password',
    'name',
    'surname',
    'email',
    'yearOfBirth',
    'companyName']

    userForm: FormGroup;
    formError: string;


  constructor(
    public companyService: CompanyService,
    private formBuilder: FormBuilder,
    public userService: UserService) {
      window.s = this;
    }

  ngOnInit() {
    this.initForm();
    this.getData();
  }

  initForm () {
    this.userForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(4)]],
      name: ['', [Validators.required, Validators.minLength(2)]],
      surname: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      yearOfBirth: [2000, [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear())]],
      companyId: ['', [Validators.required, Validators.minLength(4)]]
    })
  }

  async getData() {
    let _companyData = await this.companyService.getCompanies();
    this.companyData = Array.isArray(_companyData) ? _companyData : [_companyData];
    console.log(this.companyData);
    let _userData = await this.userService.getUsers();
    this.userData = Array.isArray(_userData) ? _userData : [_userData];
    console.log(this.userData)
  }

  async submit() {
    try {
      if(this.userForm.invalid) return
      const user: User = {
        username: this.userForm.get('username').value,
        password: this.userForm.get('password').value,
        name: this.userForm.get('name').value,
        surname: this.userForm.get('surname').value,
        email: this.userForm.get('email').value,
        yearOfBirth: this.userForm.get('yearOfBirth').value,
        companyId: this.userForm.get('companyId').value,
      }
      let data: ResponseObject = await this.userService.saveUser(user);
      if(data.success) {
        this.getData();
      } else {
        if(data.message) {
          this.formError = data.message
        } else {
          this.formError = data.message
        }
      }
      console.log(user);
      
    } catch (error) {
      this.formError = "Something went wrong"
    }
  }

}
