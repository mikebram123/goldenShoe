import { Component } from '@angular/core';
import { CustomerService } from './customer.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'goldenshoeUI';

  constructor(public custService:CustomerService){
    this.custService.isLoggedIn = custService.checkIsLoggedIn();
  }
}

