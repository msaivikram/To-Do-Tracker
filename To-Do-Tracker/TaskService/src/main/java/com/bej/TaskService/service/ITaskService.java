package com.bej.TaskService.service;

import com.bej.TaskService.domain.Task;
import com.bej.TaskService.domain.User;
import com.bej.TaskService.exception.TaskAlreadyExistException;
import com.bej.TaskService.exception.TaskNotFoundException;
import com.bej.TaskService.exception.UserAlreadyExistsException;
import com.bej.TaskService.exception.UserNotFoundException;

import java.util.List;

public interface ITaskService {
    User registerUser(User user) throws UserAlreadyExistsException;

    User addTasktoList(String email, Task task) throws UserNotFoundException, TaskAlreadyExistException;
    Task getTaskByID(String email, String taskID);
    User updateExistingTask(String email,Task task) throws UserNotFoundException, TaskNotFoundException;
    User deleteExistingTask(String userId, String taskID) throws UserNotFoundException, TaskNotFoundException;


    User updateTaskPriority(String email, String taskID, String priority) throws UserNotFoundException, TaskNotFoundException;

    List<Task> getAllUserTasks(String email) throws UserNotFoundException;
    public List<Task> getArchivedTasks(String email);
    public User updateTaskComplete(String email, String taskID, boolean isComplete) throws UserNotFoundException, TaskNotFoundException;
    public User updateTaskArchive(String email, String taskID, boolean isArchived) throws UserNotFoundException, TaskNotFoundException;
    public List<Task> getCompletedTasks(String email);
}
