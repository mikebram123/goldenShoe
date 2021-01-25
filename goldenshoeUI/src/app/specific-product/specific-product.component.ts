import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { Product } from '../product';

@Component({
  selector: 'app-specific-product',
  templateUrl: './specific-product.component.html',
  styleUrls: ['./specific-product.component.css']
})
export class SpecificProductComponent implements OnInit {

 
 product:Product
 productID: number
 sizes: Number[]
 currentUser: Customer
 
  constructor(public custService: CustomerService, private router:Router) {
    this.product={
      productID:0, productBrands: "", productName:"", productPrice:0, productColour:"", productFit:"",productStyle:""
    }
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    
    
    
   }

  ngOnInit(): void {
    this.productID = +sessionStorage.getItem("productID")
    this.fetchProductInfo()
    this.getSizes()
    
  }

fetchProductInfo(){
  this.custService.findProduct(this.productID).subscribe(
    response=>{
      this.product = response
      this.getSizes()
    }
  )
}

getSizes(){
  this.custService.getSizes(this.productID).subscribe(
    response=>{
      this.sizes = response
      
    }
  )
  
}


addToCart(size:number){
  this.custService.moveToCart(1, this.productID, size, this.currentUser.customerID).subscribe(
    response=>{
      this.product=response
      this.router.navigate(["basket"])
    }
  )
}

}
