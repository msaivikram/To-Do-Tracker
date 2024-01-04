import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponentComponent } from './register-component/register-component.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { TodayTaskComponent } from './today-task/today-task.component';
import { AuthenticationService } from './Services/authentication.service';
import { TodoComponent } from './todo/todo.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { DetailedViewComponent } from './detailed-view/detailed-view.component';
import { HeaderComponent } from './header/header.component';
import { ArchivedTaskComponent } from './archived-task/archived-task.component';
import { PastTaskComponent } from './past-task/past-task.component';
import { CompletedTaskComponent } from './completed-task/completed-task.component';
import { RestofTheTasksComponent } from './restof-the-tasks/restof-the-tasks.component';
import { LandingViewComponent } from './landing-view/landing-view.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { SideBarTommorowComponent } from './side-bar-tommorow/side-bar-tommorow.component';
import { SideBarRestComponent } from './side-bar-rest/side-bar-rest.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
const routes: Routes = [
  {path:"",redirectTo:"/landing", pathMatch:"full" },
  {path:"landing",component:LandingViewComponent},
  {path:"register",component:RegisterComponentComponent},
  {path:"login",component:LoginComponentComponent},
  {path:"today",component:SideBarComponent,canActivate:[AuthenticationService]},
  {path:"todo",component:SideBarTommorowComponent,canActivate:[AuthenticationService]},
  {path:"addTask",component:AddTaskComponent,canActivate:[AuthenticationService]},
  { path: 'task/:id', component: DetailedViewComponent ,canActivate:[AuthenticationService]},
  {path:'archived',component:ArchivedTaskComponent},
  {path:'past',component:PastTaskComponent},
  {path:'completed',component:CompletedTaskComponent},
  {path:'restOfTheTasks',component:SideBarRestComponent,canActivate:[AuthenticationService]},
  {path:"**", component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
