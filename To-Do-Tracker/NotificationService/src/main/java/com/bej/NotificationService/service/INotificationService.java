package com.bej.NotificationService.service;

import com.bej.NotificationService.domain.Task;

import java.util.List;

public interface INotificationService {

    List<Task> getTasks(String email, String authheader);
    List<Task> todayTasks(String email,String authHeader);
    List<Task> todayTaskSortedByPriority(String email,String authHeader);
    List<Task> todayTaskSortedCompletionStatus(String email,String authHeader);
    List<Task> todayTaskSortedByTime(String email, String authHeader);
    List<Task> tomorrowTasks(String email,String authHeader) ;
    List<Task> tomorrowTaskSortedByPriority(String email,String authHeader);
    List<Task> tomorrowTaskSortedCompletionStatus(String email,String authHeader);

    List<Task> tomorrowTaskSortedByTime(String email, String authHeader);

    List<Task> restOfTheTasks(String email, String authHeader) ;
    List<Task> restOfTheTaskSortedByPriority(String email,String authHeader);
    List<Task> restOfTheTaskSortedCompletionStatus(String email,String authHeader);
    List<Task> restOfTheTaskSortedByDate(String email, String authHeader);
    List<Task> pastTasks(String email,String authHeader);
   public Task findTaskById(String email, String authHeader);
}
