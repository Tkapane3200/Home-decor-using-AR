import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnvService } from '../../../env';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  env = new EnvService();

  url: string = this.env.API_URL + '/api/product-category/';

  constructor(
    private http: HttpClient
  ) { }

  //ProductCategory-Start
  //Product Category Add
  addProductCategory(data: any) {
    return this.http.post(this.url + 'add', data);
  }
  //Product Category Update
  updateProductCategory(data: any) {
    return this.http.post(this.url + 'update', data);
  }
  //Product Category Delete
  deleteProductCategory(data: any) {
    return this.http.post(this.url + 'delete', data);
  }
  //Product Category Get All
  getAllProductCategory() {
    return this.http.get(this.url + 'get-all');
  }
  //Product Category Get By Id
  getProductCategoryById(data: any) {
    return this.http.get(this.url + 'get-by-id/' + data);
  }
  //ProductCategory-End
}
