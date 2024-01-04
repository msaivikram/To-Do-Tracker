import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { TokenService } from './token.service'
import { TASK } from '../model/Task';

@Injectable({
  providedIn: 'root'
})
export class TodayHeadEditService {

  constructor(private http: HttpClient, private jwtToken: TokenService) {}

  url: string = 'http://localhost:9000/api/v2/user/updateTask/task/';

  editTask(taskID: number, newTaskData: any): Observable<any> {
    const headers = this.jwtToken.getheader();
    const options = { headers };
    return this.http.put<TASK>(`${this.url}${taskID}`, newTaskData, options);
  }
}
