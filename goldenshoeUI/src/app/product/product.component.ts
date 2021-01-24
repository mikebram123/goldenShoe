import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { Product } from '../product';
import { Customer } from '../customer';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[]
  size1: number
  currentUser: Customer
  product: Product

  constructor(public custService: CustomerService, router:Router) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.products=[{
      productID:0, productBrands: "", productName:"", productPrice:0
    }]
    this.size1=0

   }

   fetchProduct(size:number){
     this.custService.fetchBySize(size).subscribe(
       Response=>
       {this.products=Response}
     )
   }

  ngOnInit(): void {
    
  }

  addToCart(productID:number, size:number){
    this.custService.moveToCart(1, productID, size, this.currentUser.customerID).subscribe(
      response=>{
        this.product=response
        this.fetchProduct(size)
      }
    )
  }

}
