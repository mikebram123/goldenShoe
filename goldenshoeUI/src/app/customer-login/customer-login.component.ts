import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';


@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {

  customerLoginForm: FormGroup;

  customerUser: string;
  customerPass: string;

  constructor(private formBuilder: FormBuilder, private custService:CustomerService, private router:Router) {

   }

  ngOnInit(): void {
    this.customerLoginForm=this.formBuilder.group({
      customerUser: ['', Validators.required],
      customerPass:['', Validators.required]
    });
  }

  onSubmit(){

  }

    /*
   Called on login button which searches for username and password in database
   navigates to profile page once logged in successfully
   */
  customerLogin(user: string, password: string){
    this.custService.getCustomerLogin(user, password).then(()=>{
      this.router.navigate(['/profile']);
    });
  }

}
