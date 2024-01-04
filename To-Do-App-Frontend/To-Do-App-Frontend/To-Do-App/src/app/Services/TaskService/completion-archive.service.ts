import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { TokenService } from '../token.service';

@Injectable({
  providedIn: 'root'
})
export class CompletionArchiveService {

  
  private baseUrl = 'http://localhost:9000/api/v4'; 
  constructor(private http: HttpClient, private tokenService: TokenService,private jwt:TokenService) {}

  markTaskAsCompleted(taskID: number, isComplete: boolean): Observable<any>{
    const url = `${this.baseUrl}/complete/${taskID}/isComplete`;
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
