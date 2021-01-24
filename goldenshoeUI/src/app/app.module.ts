import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerComponent } from './customer/customer.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerRegisterComponent } from './customer-register/customer-register.component';
import { BasketComponent } from './basket/basket.component';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { ArraySortPipePipe } from './array-sort-pipe.pipe';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    CustomerLoginComponent,
    CustomerRegisterComponent,
    BasketComponent,
    HomeComponent,
    ProductComponent,
    CustomerProfileComponent,
    ArraySortPipePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
