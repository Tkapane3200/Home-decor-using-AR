import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnvService } from '../../../env';
import { DashboardModel } from '../Model/DashboardModel';


@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  env = new EnvService();
  url: string = this.env.API_URL + '/api/dashboard/';

  constructor(
    private http: HttpClient
  ) { }

  // Dashboard Get All
  // http://localhost:8080/api/dashboard/get-data
  getDashboardData() {
    return this.http.get<DashboardModel>(this.url + 'get-data');
  }
}
