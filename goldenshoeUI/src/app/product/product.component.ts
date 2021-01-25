import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { Product } from '../product';
import { Customer } from '../customer';
import { SpecificProductComponent } from '../specific-product/specific-product.component';

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
  specificProduct: SpecificProductComponent
  isShown: boolean

  constructor(public custService: CustomerService, private router:Router) {
    this.currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.products=[{
      productID:0, productBrands: "", productName:"", productPrice:0, productColour:"", productFit:"",productStyle:""
    }]
    this.size1=1
    this.product={
      productID:0, productBrands: "", productName:"", productPrice:0, productColour:"", productFit:"",productStyle:""
    }
    this.isShown = true

   }

   fetchProduct(size:number){
     this.custService.fetchBySize(size).subscribe(
       Response=>
       {this.products=Response
      this.isShown = false
      }
     )
   }

  ngOnInit(): void {
    this.fetchProduct(1)
  }



  fetchProductInfo(productID:number){
    this.custService.findProduct(productID).subscribe(
      response=>{
        this.product = response
        sessionStorage.setItem("productID", this.product.productID.toString())
        this.router.navigate(["specificProduct"])
        
      }
    )
  }

}
