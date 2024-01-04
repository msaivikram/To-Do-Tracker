
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TASK } from 'src/app/model/Task';
import { TokenService } from '../token.service';

@Injectable({
  providedIn: 'root'
})
export class GetTomorrowTasksService {

  constructor(private HTTP:HttpClient,private jwtToken:TokenService) {}
  getTomorrowTasksUrl:string= "http://localhost:9000/api/v3/tomorrow";
  getTasks()
  {
    const token = this.jwtToken.getheader();
    const a = {headers:token}
    debugger
    return this.HTTP.get<TASK[]>(`${this.getTomorrowTasksUrl}`,a);
    
  }

}
