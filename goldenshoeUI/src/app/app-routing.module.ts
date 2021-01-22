import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { CustomerRegisterComponent } from './customer-register/customer-register.component';
import { CustomerComponent } from './customer/customer.component';
import { HomeComponent } from './home/home.component';


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
  }
  


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
