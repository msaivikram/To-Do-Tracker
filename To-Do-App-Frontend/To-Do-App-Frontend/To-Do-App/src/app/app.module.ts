import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddTaskComponent } from './add-task/add-task.component';
import { ArchivedTaskComponent } from './archived-task/archived-task.component';


import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import {MatRadioModule} from '@angular/material/radio';

import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import { ReactiveFormsModule } from '@angular/forms';
import { CompletedTaskComponent } from './completed-task/completed-task.component';
import { PastTaskComponent } from './past-task/past-task.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { SideBarRestComponent } from './side-bar-rest/side-bar-rest.component';
import { SideBarTommorowComponent } from './side-bar-tommorow/side-bar-tommorow.component';
import {DetailedViewComponent} from './detailed-view/detailed-view.component';
import { HeaderComponent } from './header/header.component';
import { LandingViewComponent } from './landing-view/landing-view.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { RegisterComponentComponent } from './register-component/register-component.component';
import { RestofTheTasksComponent } from './restof-the-tasks/restof-the-tasks.component';
import { TodayTaskComponent } from './today-task/today-task.component';
import { TodoComponent } from './todo/todo.component';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { MatMenuModule } from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatTooltipModule} from '@angular/material/tooltip';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatSortModule } from '@angular/material/sort';
import {MatInputModule} from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import {MatButtonModule} from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';
import { FooterComponent } from './footer/footer.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    AddTaskComponent,
    ArchivedTaskComponent,
    
    CompletedTaskComponent,
    PastTaskComponent,
    SideBarComponent,
    SideBarRestComponent,
    SideBarTommorowComponent,
    DetailedViewComponent,
    HeaderComponent,
    LandingViewComponent,
    LoginComponentComponent,
    RegisterComponentComponent,
    RestofTheTasksComponent,
    TodayTaskComponent,
    TodoComponent,
    FooterComponent,
    PageNotFoundComponent
   
     
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatRadioModule,
    MatFormFieldModule,
    FormsModule,
    HttpClientModule,
    MatCardModule,
    MatToolbarModule,
    ReactiveFormsModule,
    NgxMaterialTimepickerModule,
    MatMenuModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatDatepickerModule,
    MatSnackBarModule,
    MatTooltipModule,
    MatGridListModule,
    MatSortModule,
    MatInputModule,
    MatNativeDateModule,
    MatButtonModule,
    MatChipsModule   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
