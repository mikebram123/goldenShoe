import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { Product } from '../product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[]
  size1: number

  constructor(public custService: CustomerService, router:Router) {
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

}
