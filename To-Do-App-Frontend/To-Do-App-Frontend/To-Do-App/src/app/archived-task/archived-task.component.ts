

import { Component } from '@angular/core';

import { MatTableDataSource } from '@angular/material/table';
import { GetArchivedService } from '../Services/TaskService/get-archived.service';
import { TASK } from '../model/Task';
import { Router } from '@angular/router';
import {OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { DeleteTaskService } from '../Services/TaskService/delete-task.service';
import { CompletionArchiveService } from '../Services/TaskService/completion-archive.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-archived-task',
  templateUrl: './archived-task.component.html',
  styleUrls: ['./archived-task.component.css']
})
export class ArchivedTaskComponent implements OnInit{
    
    
  message: string = '';
  tasks: TASK[] = [];
  displayedColumns: string[] = ['taskHeading', 'dueDate', 'dueTime', 'priority', 'taskContent', 'deleteTask'];

  dataSource: MatTableDataSource<TASK>;
  @ViewChild(MatSort)
  sort: MatSort = new MatSort;

  constructor(private archiveService: GetArchivedService, private router: Router, private deleteService: DeleteTaskService, private archiveComplete: CompletionArchiveService) {
    this.dataSource = new MatTableDataSource<TASK>([]);
  }

  isTaskCompleted(task: TASK): boolean {
    return task.isCompleted;
  }

  ngOnInit(): void {
    this.archiveService.getTasks().subscribe(
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
        confirm("Are yousure want to delete");
        location.reload()
      },
      (error) => {
        console.error('Error deleting task', error);
      }
    );
  }




  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
}
