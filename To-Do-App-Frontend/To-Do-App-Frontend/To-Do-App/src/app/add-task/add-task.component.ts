
import { Component,OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TASK } from '../model/Task';
import { AddTaskService } from '../Services/add-task.service';

import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {
   
   
  taskForm: FormGroup;

  nextTaskID: number = 1;
  minDate: Date;
  currentDate: Date;
  currentTime: string;

  constructor(private fb: FormBuilder, private addTaskService: AddTaskService,private router:Router) {
    const today = new Date();
    today.setDate(today.getDate() );
    this.currentDate = new Date(); 
    this.minDate = today;
    this.currentTime = new Date().toTimeString().split(' ')[0];
    this.taskForm = this.fb.group({

      taskID: [0],
      taskHeading: ['', [Validators.required, Validators.minLength(3)]],
      taskContent: ['', [Validators.required, Validators.minLength(5)]],
      dueDate: [null, (control: any) => this.validateDate(control)],
      dueTime: [this.currentTime, (control: any) => this.validateTime(control)],
      priority: ['', Validators.required],
      category: ['', [Validators.required, Validators.minLength(3)]],
      isCompleted: [false],
      reminder: [null],
      isArchived: [false],
    });
  }

  ngOnInit(): void { }

  onSubmit() {
    if (this.taskForm.valid) {
      const newTask: TASK = {
        ...this.taskForm.value,
        taskID: this.generateTaskID(),
      };
  
      this.addTaskService.addTask(newTask).subscribe(
        (response: any) => {
          console.log('Task added:', response);
          confirm("Task Added Successfully");
          this.router.navigate(['/today']); 
        },
        (error: any) => {
          console.error('Error adding task:', error);
        }
      );
    }
  }

  validateDate(control: any) {
    const selectedDate = new Date(control.value);
    const currentDateWithoutTime = new Date(this.currentDate);
    currentDateWithoutTime.setHours(0, 0, 0, 0);
  
    if (selectedDate < currentDateWithoutTime) {
      return { 'dateFromYesterday': true };
    }
    return null;
  }
  
  validateTime(control: any) {
    const selectedTime = control.value;
    const selectedDate = new Date(control.value);

    if (selectedTime < this.currentTime) {
      return { 'invalidTime': true };
    }
    return null;
  }

  generateTaskID(): number {
    const minInt = -2147483648;
    const maxInt = 2147483647;
    return Math.floor(Math.random() * (maxInt - minInt + 1)) + minInt;
  }
}
