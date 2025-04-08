import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnvService } from '../../../env';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  env = new EnvService();
  url: string = this.env.API_URL + '/api/product/';

  constructor(
    private http: HttpClient
  ) { }

  // Product Add
  createProduct(data: any) {
    return this.http.post(this.url + 'add', data);
  }

  // Product Update
  updateProduct(data: any) {
    return this.http.post(this.url + 'update', data);
  }
  // Product Delete
  deleteProduct(data: any) {
    return this.http.post(this.url + 'delete', data);
  }
  // Product Get All
  getAllProduct() {
    return this.http.get(this.url + 'get-all');
  }
  // Product Get By Id
  getProductById(data: any) {
    return this.http.get(this.url + 'get-by-id/' + data);
  }


}
