import { Component,ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TASK } from '../model/Task';
import { DeleteTaskService } from '../Services/TaskService/delete-task.service';
import { Router } from '@angular/router';
import { GetPastTaskService } from '../Services/TaskService/get-past-task.service';
import { CompletionArchiveService } from '../Services/TaskService/completion-archive.service';
import { TodayTasksCompletedService } from '../Services/TaskService/today-tasks-completed.service';
import { TodayArchiveService } from '../Services/TaskService/today-archive.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-past-task',
  templateUrl: './past-task.component.html',
  styleUrls: ['./past-task.component.css']
})
export class PastTaskComponent {
    
    
  message: String = '';
  displayedColumns: string[] = ['taskHeading', 'dueDate', 'dueTime', 'priority', 'taskContent', 'isComplete', 'deleteTask'];

  dataSource: MatTableDataSource<TASK>;
  @ViewChild(MatSort)
  sort: MatSort = new MatSort;

  constructor(
    private pastService: GetPastTaskService,
    private router: Router,
    private deleteService: DeleteTaskService,
    private completedTasksService: TodayTasksCompletedService,
    private archiveService: TodayArchiveService
  ) {
    this.dataSource = new MatTableDataSource<TASK>([]);
  }

  ngOnInit(): void {
    this.pastService.getTasks().subscribe(
      (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;

      },
      (error) => {
        if (error.error && error.error.message === 'You have no tasks for this day') {
          this.message = `You have no Archived Task`;
        } else {
          this.message = 'An error occurred while fetching tasks.';
        }
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
  }

  markAsCompleted(taskID: number) {
    const isComplete = true;

    this.completedTasksService.updateTaskCompletion(taskID, isComplete).subscribe(
      (response: any) => {
        console.log('Task marked as completed:', response);
      },
      (error: any) => {
        console.error('Error updating task completion', error);
      }
    );
  }

  markAsArchived(taskID: number) {
    const isArchived = true;

    this.archiveService.updateTaskArchive(taskID, isArchived).subscribe(
      (response: any) => {
        console.log('Task Archived', response);
        confirm("Are you sure want to archive");
        location.reload()
      },
      (error: any) => {
        console.error('Error', error);
      }
    );
  }
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
}
