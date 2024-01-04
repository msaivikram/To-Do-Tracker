import { Component } from '@angular/core';
import { PRIORITY, TASK } from '../model/Task';
import { TodayArchiveService } from '../Services/TaskService/today-archive.service';
import { DeleteTaskService } from '../Services/TaskService/delete-task.service';
import { TodayTasksCompletedService } from '../Services/TaskService/completion-service.service';
import { Router } from '@angular/router';
import { GetrestOfTheTasksService } from '../Services/TaskService/get-rest-of-the-tasks.service';
import { TodayHeadEditService } from '../Services/today-head-edit.service';
import { RestOfTheTaskByPriorityService } from '../Services/TaskService/rest-of-the-task-by-priority.service';


@Component({
  selector: 'app-restof-the-tasks',
  templateUrl: './restof-the-tasks.component.html',
  styleUrls: ['./restof-the-tasks.component.css']
})
export class RestofTheTasksComponent {
      
  sortAscending = true;
  showTickIcon = false;
  constructor(private service: GetrestOfTheTasksService,  
    private router: Router, 
    private priorityService: RestOfTheTaskByPriorityService,
    private editService:TodayHeadEditService,private completedTasksService:TodayTasksCompletedService
    ,private ArchiveService:TodayArchiveService,private deleteService:DeleteTaskService) { }
  Tasks: TASK[] = [];
  searchText:string=''

  message: string = '';
  ngOnInit(): void {
    this.fetchTasks();
    this.service.getTasks().subscribe(
      (data) => {
        this.Tasks = data;
        this.showTickIcon = this.Tasks.every(task => task.isCompleted);

      }
      ,(error) => {
        if (error.error && error.error.message === "You have no tasks for this day") {
          this.message = "You have no tasks for ${new Date().toLocaleDateString()} (Today)";
        } else {
          this.message = 'An error occurred while fetching tasks.';
        }
      }
    );
 
  }



  onCardClick(taskID: number) {
    this.router.navigate(['/task', taskID]);
  }

  fetchTasks() {
    this.priorityService.getrestOfTheTaskByPriority().subscribe(
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
          confirm("Task Marked as Completed");
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
    this.deleteService.deleteTask(taskID).subscribe(
      (response) => {
        console.log('Task deleted successfully', response);
      },
      (error) => {
        console.error('Error deleting task', error);
      }
    );
    confirm("Are you sure want to delete")
    location.reload()
  }
  getBorderColor(priority: string): string {
    switch (priority) {
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
  }


}

