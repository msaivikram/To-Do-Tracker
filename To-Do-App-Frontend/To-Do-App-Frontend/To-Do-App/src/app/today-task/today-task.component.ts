

import { Component, OnInit, Renderer2, Inject } from '@angular/core';
import { GetTodayTasksService } from '../Services/TaskService/get-today-tasks.service';
import { TodayHeadEditService } from '../Services/today-head-edit.service';
import { TASK } from '../model/Task';
import { Router } from '@angular/router';
import { TodayTasksByPriority } from '../Services/TaskService/today-task-by-priority.service';
import { PRIORITY } from '../model/Task';
import { TodayTasksCompletedService } from '../Services/TaskService/completion-service.service';
import { TodayArchiveService } from '../Services/TaskService/archive-service.service';
import { DeleteTaskService } from '../Services/TaskService/delete-task.service';
import { DOCUMENT } from '@angular/common';


@Component({
  selector: 'app-today-task',
  templateUrl: './today-task.component.html',
  styleUrls: ['./today-task.component.css']
})
export class TodayTaskComponent implements OnInit{
    
  sortAscending = true;
  showTickIcon = false;
  darkTheme = false;
  isDarkTheme = false;
  searchText:string=''

  constructor(
    private service: GetTodayTasksService,
    private router: Router,
    private priorityService: TodayTasksByPriority,
    private editService: TodayHeadEditService,
    private completedTasksService: TodayTasksCompletedService,
    private ArchiveService: TodayArchiveService,
    private deleteService: DeleteTaskService,
  ) {}

  Tasks: TASK[] = [];
  message: string = '';

  ngOnInit(): void {
    this.fetchTasks();
    this.service.getTasks().subscribe(
      (data) => {
        this.Tasks = data;
        this.showTickIcon = this.Tasks.every((task) => task.isCompleted);
      },
      (error) => {
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
    this.priorityService.getTodayTasksByPriority().subscribe(
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

        const task = this.Tasks.find((t) => t.taskID === taskID);
        if (task) {
          task.isCompleted = true;
          confirm("Task Markeda as Completed");
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

        const task = this.Tasks.find((t) => t.taskID === taskID);
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
  }

}
