
import { Component, OnInit, numberAttribute } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TASK } from '../model/Task';
import { DetailedViewService  } from '../Services/detailed-view.service';
import { Router } from '@angular/router';
import { TokenService } from "../Services/token.service"
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { TodayHeadEditService } from '../Services/today-head-edit.service';

@Component({
  selector: 'app-detailed-view',
  templateUrl: './detailed-view.component.html',
  styleUrls: ['./detailed-view.component.css']
})
export class DetailedViewComponent implements OnInit{
    
  Details: FormGroup;
  task: TASK | undefined;
  minDate: Date;


  constructor(private route: ActivatedRoute, private detailService: DetailedViewService, private token: TokenService, private router: Router, private forms: FormBuilder, private editService: TodayHeadEditService) {
    const today = new Date();
    today.setDate(today.getDate() - 1);
    this.minDate = today;    
    this.Details = this.forms.group({
      taskHeading: new FormControl('', [Validators.required,Validators.minLength(3),Validators.maxLength(50),]),
      taskContent: new FormControl('', [Validators.required,Validators.minLength(5),Validators.maxLength(200), ]),
      dueDate: new FormControl('', [Validators.required,]),
      dueTime: new FormControl(''),
      priority: new FormControl(''),
      category: new FormControl('', [Validators.required,Validators.minLength(3),Validators.maxLength(15),]),
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      let id = params.get('id') || '';

      this.detailService.getSingleBestSeller(id).subscribe((data) => {
        if (data) {
          this.update(data);
        }
      });
    }
  )}

  update(details: TASK) {
    this.Details.setValue({
      taskHeading: details.taskHeading,
      taskContent: details.taskContent,
      dueDate: details.dueDate,
      dueTime: details.dueTime,
      priority: details.priority,
      category: details.category
    });
  }

  onsubmit() {
    const taskIdString = this.route.snapshot.paramMap.get('id');
    console.log("First");
    if (taskIdString !== null) {
      const taskId = parseInt(taskIdString, 10);
      console.log("Second");
      if (!isNaN(taskId)) {
        console.log("Third");
        console.log("this.Details.value:", this.Details.value);
        this.editService.editTask(taskId, this.Details.value).subscribe(data => {
          console.log("$");
          console.log(data);
          confirm("Task is Updated");
          console.log("UPDATED");
          this.router.navigate(['/today']); 

          
          
        });
      } else {
        console.log("Task ID is not a valid number");
      }
    } else {
      console.log("Task ID is not available");
    }
  }

  formchangingvalue: boolean = false;
  changeboolean() {
    this.formchangingvalue = true;
  }
}

