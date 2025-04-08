import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenCheckService {

  localStorage: any;
  token: any;

  constructor() {
    console.log("TokenCheckService");
  }

  //token check service

  checkToken() {

    // check if token is present in local storage

    //let token = JSON.parse(this.localStorage.getItem('token')) || "null" //  Cannot read properties of undefined (reading 'getItem')

    try {
      this.token = localStorage.getItem('token');
    }
    catch (error) {
      // console.log(error);
      return false;
    }



    //let token = localStorage.getItem('token');
    if (this.token) {
      return true;
    }
    return false;

  }

  setToken(token: any) {
    try {
      localStorage.setItem('token', token);
      return true;
    }
    catch (error) {
      return false;
    }

  }

  getToken() {
    return localStorage.getItem('token');
  }

  removeToken() {
    localStorage.removeItem('token');
    return true;
  }

}
