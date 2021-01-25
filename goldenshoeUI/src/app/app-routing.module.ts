import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BasketComponent } from './basket/basket.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { CustomerRegisterComponent } from './customer-register/customer-register.component';
import { CustomerComponent } from './customer/customer.component';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { SpecificProductComponent } from './specific-product/specific-product.component';


const routes: Routes = [

  {
    path:'home', component:HomeComponent
  },
  {
    path:'customer',component:CustomerComponent},
    
  {
    path: 'register', 
    component:CustomerRegisterComponent,
  },
  {
    path: 'login',
    component:CustomerLoginComponent
  },
  {path:'',
  redirectTo:'/home', 
  pathMatch:'full'},
  {
    path:'profile', 
    component: CustomerProfileComponent
  },
  {
    path: 'product',
    component: ProductComponent
  },
  {
    path:'basket',
    component: BasketComponent
  },
  {
    path:'checkout',
    component: CheckoutComponent
  },
  {
    path: 'specificProduct',
    component: SpecificProductComponent
  }
  


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
