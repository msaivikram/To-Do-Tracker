import { Component } from '@angular/core';

import { LoginService } from "../Services/login.service";
import { LOGIN } from '../model/Login';
import { TokenService } from '../Services/token.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent {
      
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private tokenService: TokenService,
    private Route: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required,Validators.email]],
      // , Validators.required
      userPassword: ['', Validators.required],
    });
  }

  jwt_token = '';

  login() {
    if (this.loginForm.valid) {
      const formdata = this.loginForm.value;
      console.log(formdata);
      this.loginService.getAllUserDetails().subscribe((data: any[]) => {
        const matchingUser = data.filter(
          (d) =>
            d.email === formdata.email && d.userPassword === formdata.userPassword
        );
        console.log(matchingUser);

        if (matchingUser.length > 0) {
          this.loginService.loginUser(matchingUser[0]).subscribe((data) => {
            this.jwt_token = data;
            this.tokenService.setToken(data);
            console.log('JWT Token: ' + this.tokenService.getToken());
            this.Route.navigateByUrl('/today');
          });
        }
      });
    }
  }
}
