
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LOGIN } from '../model/Login'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private HTTP:HttpClient) { }
  loginUrl="http://localhost:9000/api/v1/logins"
  detailsUrl="http://localhost:9000/api/v1/getAllUsers"

  getAllUserDetails(): Observable<LOGIN[]>
  {
    return this.HTTP.get<LOGIN[]>(this.detailsUrl);
  }

  loginUser(user:LOGIN):Observable<string>
  {
    return this.HTTP.post(this.loginUrl,user,{responseType:'text'});
  }
}
