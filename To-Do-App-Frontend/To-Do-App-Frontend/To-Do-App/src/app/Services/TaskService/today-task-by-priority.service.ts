
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../token.service';
import { TASK } from 'src/app/model/Task';

@Injectable({
  providedIn: 'root'
})
export class TodayTasksByPriority {

  private baseUrl = 'http://localhost:9000/api/v3';

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  getTodayTasksByPriority(): Observable<TASK[]> {
    const authToken = this.tokenService.getToken();

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${authToken}`
    });
    const url = `${this.baseUrl}/todayTasksByPriority`;

    return this.http.get<TASK[]>(url, { headers });
  }
}
