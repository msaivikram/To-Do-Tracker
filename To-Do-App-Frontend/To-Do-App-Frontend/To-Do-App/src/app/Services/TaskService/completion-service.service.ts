
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenService } from '../token.service';
import { Observable, throwError } from 'rxjs';
import { TASK } from 'src/app/model/Task';

@Injectable({
  providedIn: 'root'
})
export class TodayTasksCompletedService {

  private apiUrl = 'http://localhost:9000/api/v2/user/updateTaskCompletion';

  constructor(private http: HttpClient,private jwt:TokenService) {}

  updateTaskCompletion(taskID: number, isComplete: boolean): Observable<any> {
    const url = `${this.apiUrl}/task/${taskID}/isComplete`;

    const token = this.jwt.getToken();

    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });

      return this.http.put(url, isComplete, { headers });
    } else {
      return throwError("Token is not available.");
    }
  }
}
