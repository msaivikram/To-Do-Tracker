import { Time } from "@angular/common";
import { PRIORITY } from "./Priority";
export type TASK = 
{
    
    taskID :number;
    taskHeading:string;
    taskContent:string;
    dueDate:Date;
    dueTime:Time;
    priority:PRIORITY;
    category:string;
    isCompleted:boolean;
    reminder:Time;
    isArchived:boolean;
}

export { PRIORITY };