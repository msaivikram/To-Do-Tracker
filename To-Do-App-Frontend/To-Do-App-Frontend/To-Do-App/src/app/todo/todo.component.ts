
import { Component ,OnInit} from '@angular/core';
import { TokenService } from '../Services/token.service';
import { Router } from '@angular/router';
import { PRIORITY, TASK } from '../model/Task';
import { GetTomorrowTasksService } from '../Services/TaskService/get-tomorrow-tasks.service';
import { TomorrowTaskByPriorityService } from '../Services/TaskService/tomorrow-task-by-priority.service';
import { TodayTasksCompletedService } from '../Services/TaskService/completion-service.service';
import { TodayHeadEditService } from '../Services/today-head-edit.service';
import { TodayArchiveService } from '../Services/TaskService/archive-service.service';
import { DeleteTaskService } from '../Services/TaskService/delete-task.service';
import { Observable,Subscription, interval  } from 'rxjs';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit{
  private updateSubscription: Subscription | undefined;
  sortAscending = true;
  showTickIcon = false;
  constructor(private service: GetTomorrowTasksService,  
    private router: Router, 
    private priorityService: TomorrowTaskByPriorityService,
    private editService:TodayHeadEditService,private completedTasksService:TodayTasksCompletedService
    ,private ArchiveService:TodayArchiveService,private deleteService:DeleteTaskService) { }
  Tasks: TASK[] = [];
  searchText:string=''


  message: string = '';
  ngOnInit(): void {
    
    // this.fetchTasks();
    
    this.service.getTasks().subscribe(
      (data) => {
        this.Tasks = data;
        this.showTickIcon = this.Tasks.every(task => task.isCompleted);
        
        
      }
      , (error) => {
        if (error.error && error.error.message === 'You have no tasks for this day') {
          const tomorrow = new Date();
          tomorrow.setDate(new Date().getDate() + 1);
          this.message = "You have no tasks for ${tomorrow.toLocaleDateString()} (Tomorrow)";
        } else {
          this.message = 'An error occurred while fetching tasks.';
      }
  });
 
  }



  onCardClick(taskID: number) {
    this.router.navigate(['/task', taskID]);
  }

  fetchTasks() {
    this.priorityService.getTomorrowTasksByPriority().subscribe(
      (tasks: TASK[]) => {
        this.Tasks = tasks;
      },
      (error) => {
        console.error('Error fetching tasks:', error);
      }
    );
  }
  sortTasksByPriority() {
    const priorityOrder = [PRIORITY.HIGH, PRIORITY.MEDIUM, PRIORITY.LOW];

    this.Tasks.sort((a, b) => {
      return priorityOrder.indexOf(a.priority) - priorityOrder.indexOf(b.priority);
    });

    this.sortAscending = true; 
  }
  
  markAsCompleted(taskID: number) {
    const isComplete = true;
  
    this.completedTasksService.updateTaskCompletion(taskID, isComplete).subscribe(
      (response: any) => {
        console.log('Task marked as completed:', response);
  
        const task = this.Tasks.find(t => t.taskID === taskID);
        if (task) {
          task.isCompleted = true;
          confirm("Task marked as Completed");
          location.reload()
          }
      },
      (error: any) => {
        console.error('Error updating task completion', error);
      }
    );
  }
  markAsArchived(taskID: number) {
    const isArchived = true;
  
    this.ArchiveService.updateTaskArchive(taskID, isArchived).subscribe(
      (response: any) => {
        console.log('Task Archived', response);
  
        const task = this.Tasks.find(t => t.taskID === taskID);
        if (task) {
          task.isArchived = true;
          confirm("Task Moved to Archive");

          }
      },
      (error: any) => {
        console.error('Error', error);
      }
    );
  }
  deleteTask(taskID: number) {
    
    if(confirm("Are you sure want to delete")==true){
      
      console.log(taskID);
      
      this.deleteService.deleteTask(taskID).subscribe(
      
        (response) => {
          console.log('Task deleted successfully', response);
        },
        (error) => {
          console.error('Error deleting task', error);
        }
      );
     
      location.reload()
    }
    else{
      return console.log("No tasks");
    }
    
  }
  getBorderColor(priorityLevel: string): string {
    switch (priorityLevel) {
      case 'HIGH':
        return 'green';
      case 'MEDIUM':
        return 'yellow';
      case 'LOW':
        return 'red';
      default:
        return 'transparent';
    }
  }
  onSearch() {
    this.Tasks = this.Tasks.filter((task) => {
      return task.taskHeading.toLowerCase().includes(this.searchText.toLowerCase());
    });
  // onSearch():void {
  //   if(this.searchText===''||!this.searchText){
  //     this.Tasks=this.Tasks;
  //   }
  //   else{
  //     this.Tasks = this.Tasks.filter(task =>
  //              task.taskHeading?.toLowerCase().includes(this.searchText.toLowerCase()));
  //   };
  }
  

}
