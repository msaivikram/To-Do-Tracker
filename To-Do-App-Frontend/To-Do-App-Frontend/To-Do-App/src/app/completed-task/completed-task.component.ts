
import { Component,ViewChild } from '@angular/core';

import { MatTableDataSource } from '@angular/material/table';
import { TASK } from '../model/Task';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { DeleteTaskService } from '../Services/TaskService/delete-task.service';
import { GetCompletedService } from '../Services/TaskService/get-completed.service';
import { MatPaginator } from '@angular/material/paginator';
import { TodayArchiveService } from '../Services/TaskService/today-archive.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-completed-task',
  templateUrl: './completed-task.component.html',
  styleUrls: ['./completed-task.component.css']
})
export class CompletedTaskComponent {
   
      
  message: String='' ;
  private hasRefreshed = false;

  displayedColumns: string[] = ['taskHeading', 'dueDate', 'dueTime', 'priority', 'taskContent','isArchived','deleteTask'];

    dataSource: MatTableDataSource<TASK>;
    @ViewChild(MatSort)
  sort: MatSort = new MatSort;
    constructor(private completedService: GetCompletedService,
      private router: Router,
      private deleteService: DeleteTaskService,
      private archiveService: TodayArchiveService,
      private route: ActivatedRoute)
      {
      this.dataSource = new MatTableDataSource<TASK>([]); 
      }
  


  
    ngOnInit(): void {
      this.completedService.getTasks().subscribe(
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
        confirm("Are you sure want to delete");
        location.reload()
      },
      (error) => {
        console.error('Error deleting task', error);
      }
    );
  }
  markAsArchived(taskID: number) {
    const isArchived = true;

    this.archiveService.updateTaskArchive(taskID, isArchived).subscribe(
      (response: any) => {
        console.log('Task Archived', response);
        confirm("Task moved to Archive");
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
