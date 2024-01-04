
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TASK } from 'src/app/model/Task';
import { TokenService } from '../token.service';

@Injectable({
  providedIn: 'root'
})
export class GetTodayTasksService {

  constructor(private HTTP:HttpClient,private jwtToken:TokenService) {}
  getTodayTasksUrl:string= "http://localhost:9000/api/v3/today";
  getTasks():Observable<TASK[]>
  {
    const token = this.jwtToken.getheader();
    const a = {headers:token}
    return this.HTTP.get<TASK[]>(`${this.getTodayTasksUrl}`,a);
  }
}
