import { Injectable } from '@angular/core';
import { Customer } from './customer';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Product } from './product';
import { Basket } from './basket';
import { ProductBasketAssignment } from './product-basket-assignment';


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

   //Service method to add a new customer
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

   //Service Method to login with customer data
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

   //Removes customer from local storage
   customerLogout(){
    localStorage.removeItem("currentUser");
    this.isLoggedIn = false;
  }

  //Returns true if customer is logged in
  checkIsLoggedIn(){
    if(localStorage.getItem("currentUser")){
      return true;
    }else{
      return false;
    }
  }

  //Calls the list all method in the api
  fetchAllProducts(): Observable<Product[]> {
    return this.httpsvc.get<Product[]>(this.rootURL+'/product/listAll')
    
  }

  //Calls the findbysize method in the api
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

  //Calls the addToCart method in the api
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

  //Calls fetchCurrentBasket method in the api
  fetchCurrentBasket(customerID:number):Observable<Basket>{
    var contentData=
      "customerID="+customerID
      const httpOptions= {
        headers: new HttpHeaders(
            {"Content-Type":"application/x-www-form-urlencoded"}
            )
          }
    return this.httpsvc.post<Basket>("http://localhost:8080/goldenshoe/basket/fetchCurrentBasket", contentData, httpOptions)
  }

  fetchAssignedProducts(basketID:number):Observable<ProductBasketAssignment[]>{
    var contentData=
      "basketID="+basketID
      const httpOptions={
        headers: new HttpHeaders(
          {"Content-Type":"application/x-www-form-urlencoded"}
          )
      }
    return this.httpsvc.post<ProductBasketAssignment[]>("http://localhost:8080/goldenshoe/basket/fetchBasketProducts", contentData, httpOptions)
  }

  checkout(basketID:number):Observable<Basket>{
    var contentData=
      "basketID="+basketID
      const httpOptions={
        headers: new HttpHeaders(
          {"Content-Type":"application/x-www-form-urlencoded"}
          )
      }
    return this.httpsvc.post<Basket>("http://localhost:8080/goldenshoe/basket/checkout", contentData, httpOptions)
  }


findProduct(productID:number):Observable<Product>{
  var contentData=
  "productID="+productID
  const httpOptions={
    headers: new HttpHeaders(
      {"Content-Type":"application/x-www-form-urlencoded"}
      )
  }
return this.httpsvc.post<Product>("http://localhost:8080/goldenshoe/product/findProd", contentData, httpOptions)


}

getSizes(productID:number):Observable<Number[]>{
  var contentData=
  "productID="+productID
  const httpOptions={
    headers: new HttpHeaders(
      {"Content-Type":"application/x-www-form-urlencoded"}
      )
  }
return this.httpsvc.post<Number[]>("http://localhost:8080/goldenshoe/product/findSizes", contentData, httpOptions) 
}


delete(productBasketAssignmentID:number):Observable<ProductBasketAssignment>{
  var contentData=
  "productBasketAssignmentID="+productBasketAssignmentID
  const httpOptions={
    headers: new HttpHeaders(
      {"Content-Type":"application/x-www-form-urlencoded"}
      )
  }
return this.httpsvc.post<ProductBasketAssignment>("http://localhost:8080/goldenshoe/basket/delete", contentData, httpOptions) 
}




}
