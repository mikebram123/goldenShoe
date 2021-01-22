import { Injectable } from '@angular/core';
import { Customer } from './customer';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  rootURL: string;
  isLoggedIn: boolean;
  currentUser: Customer;

  constructor(private httpsvc:HttpClient) {
    this.currentUser= JSON.parse(localStorage.getItem("currentUser"));

    this.rootURL= "http://localhost:8080/goldenshoe"
   }

   addNewCustomer(newCustomer){
    const httpOpts = {
      headers: new HttpHeaders({"Content-Type":"application/x-www-form-urlencoded"})
    }

    const params = new URLSearchParams();
    params.set('customerEmail', newCustomer.customerEmail)
    params.set('customerName', newCustomer.customerName)
    params.set('customerPass', newCustomer.customerPass)
    params.set('customerUser', newCustomer.customerUser)

    return this.httpsvc.post<Customer>(this.rootURL+"/customer/register", params.toString(), httpOpts).subscribe(
      (res) => console.log(res),
      (err) => console.log(err)
    );
   }

   async getCustomerLogin(user: string, password: string){
    const httpOpts = {
      headers: new HttpHeaders({"Content-Type":"application/x-www-form-urlencoded"})
    }
    const params = new URLSearchParams();
    params.set('customerUser', user)
    params.set('customerPass', password)

    const result = await this.httpsvc.post<Customer>(this.rootURL+"/customer/login", params.toString(), httpOpts).toPromise();
    if(result){
      this.currentUser = result;
      localStorage.setItem('currentUser', JSON.stringify(result));
      this.isLoggedIn= true;
      console.log("fjfne")
      }
      return result;
   }

   customerLogout(){
    localStorage.removeItem("currentUser");
    this.isLoggedIn = false;
  }

  checkIsLoggedIn(){
    if(localStorage.getItem("currentUser")){
      return true;
    }else{
      return false;
    }
  }
}
