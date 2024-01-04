import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../token.service';

@Injectable({
  providedIn: 'root'
})
export class DeleteTaskService {

  private baseUrl = 'http://localhost:9000/api/v2'; 



  constructor(private http: HttpClient, private tokenService: TokenService) {} 

  deleteTask(taskID: number): Observable<any>
  {
    const headers = this.tokenService.getheader(); 
    return this.http.delete(`${this.baseUrl}/user/deleteTask/${taskID}`, { headers });
  }
}
