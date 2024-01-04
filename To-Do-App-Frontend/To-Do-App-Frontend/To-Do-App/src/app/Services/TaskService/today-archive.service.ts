
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { TokenService } from '../token.service';

@Injectable({
  providedIn: 'root'
})
export class TodayArchiveService {

  private apiUrl = 'http://localhost:9000/api/v2/user/updateTaskArchive';

  constructor(private http: HttpClient,private jwt:TokenService) {}

  updateTaskArchive(taskID: number, isArchive: boolean): Observable<any> {
    const url = `${this.apiUrl}/task/${taskID}/isArchive`;

    const token = this.jwt.getToken();

    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });

      return this.http.put(url, isArchive, { headers });
    } else {
      return throwError("Token is not available.");
    }
  }
}
