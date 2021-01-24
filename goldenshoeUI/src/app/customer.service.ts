import { Injectable } from '@angular/core';
import { Customer } from './customer';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Product } from './product';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {


  rootURL: string;
  isLoggedIn: boolean;
  currentUser: Customer;
  isUserInUse: Boolean;
  serverMessage: String;

  constructor(private httpsvc:HttpClient) {
    this.currentUser= JSON.parse(localStorage.getItem("currentUser"));
    this.isUserInUse = true;
    this.serverMessage = "Username is taken please chose another one"

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

  fetchAllProducts(): Observable<Product[]> {
    return this.httpsvc.get<Product[]>(this.rootURL+'/product/listAll')
    
  }

  fetchBySize(size: number): Observable<Product[]>{
    var contentData = 
            "size=" + size
    const httpOptions= {
      headers: new HttpHeaders(
        {"Content-Type":"application/x-www-form-urlencoded"}
      )
      
    }
    return this.httpsvc.post<Product[]>("http://localhost:8080/goldenshoe/product/findBySize", contentData, httpOptions)
  }

  moveToCart(quantity: number, productID:number, size:number, customerID: number): Observable<Product>{
    var contentData=
        "quantity=" + quantity+ "&productID=" + productID + "&size=" + size + "&customerID=" + customerID
    const httpOptions= {
      headers: new HttpHeaders(
          {"Content-Type":"application/x-www-form-urlencoded"}
          )
        }
    return this.httpsvc.post<Product>("http://localhost:8080/goldenshoe/basket/addToCart", contentData, httpOptions)


  }
}
