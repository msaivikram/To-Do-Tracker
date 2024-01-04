
import { Component, OnInit } from '@angular/core';
import { USER } from '../model/User';
import { TASK } from '../model/Task';
import { RegisterService } from '../Services/register.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-component',
  templateUrl: './register-component.component.html',
  styleUrls: ['./register-component.component.css']
})
export class RegisterComponentComponent implements OnInit{
    
  userForm: FormGroup; 

  constructor(private registerService: RegisterService, private fb: FormBuilder) {
    this.userForm = this.fb.group({
      // userId: ['', [Validators.required,Validators.pattern(/^\d+$/)]],
// Validators.required
      email: ['', [Validators.required, Validators.email]],
      userPassword: ['',[Validators.required,Validators.minLength(6),Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/)]],
      userName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20), Validators.pattern(/^[a-zA-Z]+\s[a-zA-Z]+$/)]],
      userphoneNumber: ['', [Validators.required,Validators.pattern(/^[789]\d{9}$/)]],
    });
  }

  ngOnInit(): void { }

  register() {
    if (this.userForm.valid) {
      console.log('Form is valid:', this.userForm.valid);
    const formdata = this.userForm.value;
    console.log('Form data:', formdata);
      this.registerService.registerUser(this.userForm.value).subscribe(
        (data) => {
          console.log("Registered");
        },
        (error: HttpErrorResponse) => {
          if (error.status === 409) {
            alert("User already exists.");
          } else {
            console.error("Error registering user:", error);
          }
        }
      );
    }
  }
}
