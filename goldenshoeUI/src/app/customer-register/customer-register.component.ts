import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {

  customerRegisterForm:FormGroup;
  submitted=false;

  constructor(private formBuilder:FormBuilder, private custService: CustomerService, private router:Router) {

   }

   addNewCustomer(newCustomer: Customer){
     this.custService.addNewCustomer(newCustomer);
     this.router.navigate(['/login']);
   }
   

  ngOnInit(): void {
    this.customerRegisterForm = this.formBuilder.group({
      customerName: ['', Validators.required],
      customerEmail: ['', [Validators.required, Validators.email]],
      customerPass: ['', Validators.required],
      customerUser: ['', Validators.required],
      confirm:['', [Validators.required, Validators.email]]
    },{
      validator: (form:FormGroup) => {return form.get('customerEmail').value !==
      form.get('confirm').value ? { emailMismatch: true } : null}     
    }
    );
  }

  get f(){ return this.customerRegisterForm.controls; }
  get isEmailMismatch(){return this.customerRegisterForm.getError('emailMismatch')}

  onSubmit(newCustomer){
    this.submitted=true;

    if(this.customerRegisterForm.invalid){
      return
    } else{
      console.log(JSON.stringify(this.customerRegisterForm.value))
      this.addNewCustomer(newCustomer);
    }
  }

}
