
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TASK } from 'src/app/model/Task';
import { TokenService } from './token.service'

@Injectable({
  providedIn: 'root'
})
export class DetailedViewService {

  private detailUrl: string = "http://localhost:9000/api/v2/user/getTaskById/";
  constructor(private http:HttpClient,private jwtToken:TokenService) { }
  getSingleBestSeller(id: string): Observable<TASK | undefined>
  {
    const token = this.jwtToken.getheader();
    const a = {headers:token}
    return this.http.get<TASK>(`${this.detailUrl}${id}`,a);
  }
}
