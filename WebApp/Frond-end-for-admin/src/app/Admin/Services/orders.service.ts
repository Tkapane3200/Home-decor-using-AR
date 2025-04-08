import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnvService } from '../../../env';

import { Orders } from '../Model/Orders';
import { log } from 'node:console';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  env = new EnvService();
  url: string = this.env.API_URL + '/api/orders/';

  constructor(
    private http: HttpClient
  ) { }

  // Order Get All
  getAllOrders() {
    return this.http.get<Orders[]>(this.url + 'get-all');
  }

  // Order Get By Id
  getOrderById(id: any) {
    return this.http.get(this.url + 'get-by-id?id=' + id);
  }

  //http://localhost:8080/api/orders/update-status
  // Order Update Status
  updateOrderStatus(data: any) {
   

    return this.http.post(this.url + 'update-status', data);
  }
}
