
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }
  Token:string="";

  setToken(to:string){
    localStorage.setItem('token', to);
  }

  getToken(){
    return localStorage.getItem('token');
  }

  clearToken(){
    localStorage.removeItem('token');  }

  getheader():HttpHeaders{
    const token = this.getToken();
    console.log("jwt Token:" + token)
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }
}
