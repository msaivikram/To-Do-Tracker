package com.bej.TaskService.service;

import com.bej.TaskService.domain.Priority;
import com.bej.TaskService.domain.Task;
import com.bej.TaskService.domain.User;
import com.bej.TaskService.exception.TaskAlreadyExistException;
import com.bej.TaskService.exception.TaskNotFoundException;
import com.bej.TaskService.exception.UserAlreadyExistsException;
import com.bej.TaskService.exception.UserNotFoundException;
import com.bej.TaskService.proxy.TaskProxy;
import com.bej.TaskService.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements ITaskService{
    private UserTaskRepository userTaskRepository;
    private TaskProxy taskProxy;
    @Autowired
    public TaskServiceImpl(UserTaskRepository userTaskRepository,TaskProxy taskProxy) {
        this.userTaskRepository = userTaskRepository;
        this.taskProxy=taskProxy;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {

        Optional<User> optUser=userTaskRepository.findById(user.getEmail());
        if(optUser.isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        // return userTrackRepository.save(user);
        User savedUser=userTaskRepository.save(user);
        if(!(savedUser.getEmail().isEmpty()))
        {
            ResponseEntity response=taskProxy.saveUser(user);
            System.out.println(response);
        }
        return savedUser;
    }

    @Override
    public User addTasktoList(String email, Task task) throws UserNotFoundException, TaskAlreadyExistException {

        if (!userTaskRepository.existsById(email)) {     //adding purpose
            throw new UserNotFoundException();
        }
          User user = userTaskRepository.findById(email).orElse(null);

        //assert user != null;
        if (user.getList() == null) {
            user.setList(Arrays.asList(task));
        } else {
            List<Task> tracksList = user.getList();
            tracksList.add(task);
            user.setList(tracksList);
        }

        return userTaskRepository.save(user);

    }

    @Override
    public Task getTaskByID(String email, String taskID) {     //for searching
        Optional<User> optionalUser = userTaskRepository.findById(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Task> userTasks = user.getList();

            for (Task task : userTasks) {
                if (task.getTaskID().equals(taskID)) {
                    return task;
                }
            }
        }
        return null;
    }

    @Override
    public User updateExistingTask(String email,Task task) throws UserNotFoundException, TaskNotFoundException {

        if (!userTaskRepository.existsById(email)) {
            throw new UserNotFoundException();
        }

        User user = userTaskRepository.findById(email).orElse(null);

        if (user == null) {
            throw new UserNotFoundException();
        }

        List<Task> tasksList = user.getList();

        if (tasksList == null || tasksList.isEmpty()) {
            throw new TaskNotFoundException();

}
        boolean taskFound = false;
        for (int i = 0; i < tasksList.size(); i++) {
            Task existingTask = tasksList.get(i);

            if (existingTask.getTaskID().equals(task.getTaskID())) {
                taskFound = true;
                // Update the details of the track here. For example:


                existingTask.setTaskHeading (task.getTaskHeading ());
                existingTask.setTaskContent (task.getTaskContent ());
                existingTask.setDueDate (task.getDueDate ());
                existingTask.setPriority(task.getPriority());
                existingTask.setCategory (task.getCategory ());
                existingTask.setCompleted (task.isCompleted ());
                existingTask.setDueTime (task.getDueTime ());
                tasksList.set(i, existingTask);
                break;
            }
        }

        if (!taskFound) {
            throw new TaskNotFoundException();
        }

        user.setList(tasksList);

        return userTaskRepository.save(user);

    }

    @Override
    public User deleteExistingTask(String email, String taskID) throws UserNotFoundException, TaskNotFoundException {

        boolean taskIdIsPresent = false;
        if(userTaskRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userTaskRepository.findById(email).get();
        List<Task> tasksList = user.getList();
        taskIdIsPresent = tasksList.removeIf(x->x.getTaskID().equals(taskID)); //dount
        System.out.println("Deleting task");
//        taskIdIsPresent = true;
        if(!taskIdIsPresent)
        {
            throw new TaskNotFoundException();
        }
        user.setList(tasksList);
        return userTaskRepository.save(user);
    }

    @Override
    public User updateTaskPriority(String email, String taskID, String priority) throws UserNotFoundException, TaskNotFoundException {

        Optional<User> optionalUser = userTaskRepository.findById (email);
        if (optionalUser.isEmpty ()) {
            throw new UserNotFoundException ();
        }
        User user = optionalUser.get ();
        List<Task> userTasks = user.getList();
        for (Task task : userTasks) {
            if (task.getTaskID ().equals(taskID)) {
                userTasks.remove (task);///doubt
                task.setPriority(Priority.valueOf(priority));
                userTasks.add (task);
                user.setList(userTasks);
                return userTaskRepository.save(user);
            }
        }
        throw new TaskNotFoundException ();

    }
    private List<Task> archivedTasks = new ArrayList<>();
    private List<Task> completedTasks = new ArrayList<>();
    @Override
    public List<Task> getAllUserTasks(String email) throws UserNotFoundException {
//marking purpose
        Optional<User> optionalUser = userTaskRepository.findById(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Task> allUserTasks = user.getList();
            Iterator<Task> iterator = allUserTasks.iterator();

            while (iterator.hasNext()) {
                Task task = iterator.next();

                if (task.isCompleted() && task.isArchived()) {
                    archivedTasks.add(task);
                    iterator.remove();
                } else if (task.isCompleted()) {
                    completedTasks.add(task);
                    iterator.remove();
                }
            }

            return allUserTasks;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<Task> getArchivedTasks(String email) {

        Set<String> seenTaskIds = new HashSet<>();
        List<Task> uniqueArchivedTasks = new ArrayList<>();

        // archivedTasks - 1,2,3,3,4,4,5
        // seenTaskIds  - (1,2,3,4,5)  > [1,2,3,4,5]
        // 1,2,3,4,5
        for (Task task : archivedTasks) {
            String taskId = task.getTaskID();
            if (seenTaskIds.add(taskId)) {
                uniqueArchivedTasks.add(task); //doubt
            }
        }

        archivedTasks.clear();

        return uniqueArchivedTasks;
    }

    @Override
    public User updateTaskComplete(String email,String taskID, boolean isComplete) throws UserNotFoundException, TaskNotFoundException {

        Optional<User> optionalUser = userTaskRepository.findById(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        List<Task> userTasks = user.getList();
        boolean taskUpdated = false;

        for (Task task : userTasks) {
            if (task.getTaskID().equals(taskID)) {
                task.setCompleted(isComplete);
                taskUpdated = true;
                break;
            }
        }

        if (taskUpdated) {
            user.setList(userTasks);
            return userTaskRepository.save(user);
        } else {
            throw new TaskNotFoundException();
        }
    }

    @Override
    public User updateTaskArchive(String email,String taskID, boolean isArchived) throws UserNotFoundException, TaskNotFoundException {

        Optional<User> optionalUser = userTaskRepository.findById(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        List<Task> userTasks = user.getList();
        boolean taskUpdated = false;
        for (Task task : userTasks) {
            if (task.getTaskID().equals(taskID) ) {
                task.setArchived(isArchived);
                taskUpdated = true;
                break;
            }
        }
        if (taskUpdated) {
            user.setList(userTasks);
            return userTaskRepository.save(user);
        } else {
            throw new TaskNotFoundException();
        }
    }

    @Override
    public List<Task> getCompletedTasks(String email) {
        Set<String> seenTaskIds = new HashSet<>();
        List<Task> uniqueCompletedTasks = new ArrayList<>();

        for (Task task : completedTasks) {
            String taskId = task.getTaskID();
            if (seenTaskIds.add(taskId)) {
                uniqueCompletedTasks.add(task); //doubt
            }
        }

        completedTasks.clear();

        return uniqueCompletedTasks;
    }
}
