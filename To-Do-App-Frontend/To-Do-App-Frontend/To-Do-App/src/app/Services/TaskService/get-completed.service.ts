
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TASK } from 'src/app/model/Task';
import { TokenService } from '../token.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GetCompletedService {

  
  constructor(private HTTP:HttpClient,private jwtToken:TokenService) {}
  getCompletedTasksUrl:string= "http://localhost:9000/api/v2/user/completed";
  getTasks():Observable<TASK[]>
  {
    const token = this.jwtToken.getheader();
    const a = {headers:token}
    return this.HTTP.get<TASK[]>(`${this.getCompletedTasksUrl}`,a);
  }
}
