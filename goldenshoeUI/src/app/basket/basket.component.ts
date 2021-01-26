import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Basket } from '../basket';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { Product } from '../product';
import { ProductBasketAssignment } from '../product-basket-assignment';
import { ProductSizeAssignment } from '../product-size-assignment';
import { Size } from '../size';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  basket: Basket
  product: Product
  productSizeAssignment: ProductSizeAssignment
  productBasketAssignment: ProductBasketAssignment
  size: Size
  currentUser: Customer

  productBasketArray: ProductBasketAssignment[]




  constructor(public custService: CustomerService,private router:Router) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.basket={
      basketID: 0,
      totalValue: 0
    }
    this.product={
      productID:0,
      productName:"",
      productBrands:"",
      productPrice:0, productColour:"", productFit:"",productStyle:""
    }
    this.size={
      sizeID:0,
      size:0
    }
    this.productSizeAssignment={
      linkedProduct:this.product,
      quantity:0,
      productSizeAssignmentID:0,
      linkedSize:this.size      
    }
    this.productBasketArray=[{
      productBasketAssignmentID: 0,
      quantityOrdered: 0,
      linkedSizes:this.productSizeAssignment
    }]

   }

   fetchCurrentBasket(){
     this.custService.fetchCurrentBasket(this.currentUser.customerID).subscribe(
       response=>{
         this.basket = response
         this.fetchBasketProducts(this.basket.basketID)
       }
     )
   }

   fetchBasketProducts(basketID: number){
     this.custService.fetchAssignedProducts(basketID).subscribe(
       response=>{
         this.productBasketArray=response
       }
     )
   }

   checkout(){
     this.custService.checkout(this.basket.basketID).subscribe(
       response=>{
       this.fetchCurrentBasket()
       this.router.navigate(["checkout"])
       }
     )
   }

   delete(productBasketAssignmentID){
     this.custService.delete(productBasketAssignmentID).subscribe(
       response =>{
         this.productBasketAssignment = response
         this.fetchCurrentBasket
       }
     )
   }

  ngOnInit(): void {
    this.fetchCurrentBasket()
  }

}
