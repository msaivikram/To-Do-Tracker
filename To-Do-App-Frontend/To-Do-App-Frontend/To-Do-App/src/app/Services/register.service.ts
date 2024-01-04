
import { Injectable } from '@angular/core';
import { USER } from '../model/User'
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private HTTP:HttpClient,) { }
  registerUrl="http://localhost:9000/api/v2/register"
  
  registerUser(user:USER):Observable<USER>{
    console.log('Register service received data:', user);
    return this.HTTP.post<USER>(this.registerUrl,user)
    .pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error registering user:', error);
        throw error; // Rethrow the error to be caught by the calling code
      })
    )
  }
}
