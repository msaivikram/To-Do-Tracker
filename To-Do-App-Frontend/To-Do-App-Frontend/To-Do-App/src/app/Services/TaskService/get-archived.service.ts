
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TASK } from 'src/app/model/Task';
import { TokenService } from '../token.service';


@Injectable({
  providedIn: 'root'
})
export class GetArchivedService {

  
  constructor(private HTTP:HttpClient,private jwtToken:TokenService) {}
  getArchiveTasksUrl:string= "http://localhost:9000/api/v2/user/archived";
  getTasks():Observable<TASK[]>
  {
    const token = this.jwtToken.getheader();
    const a = {headers:token}
    return this.HTTP.get<TASK[]>(`${this.getArchiveTasksUrl}`,a);
  }
}
