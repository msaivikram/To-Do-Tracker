package com.bej.TaskService.controller;

import com.bej.TaskService.domain.Task;
import com.bej.TaskService.domain.User;
import com.bej.TaskService.exception.TaskAlreadyExistException;
import com.bej.TaskService.exception.TaskNotFoundException;
import com.bej.TaskService.exception.UserAlreadyExistsException;
import com.bej.TaskService.exception.UserNotFoundException;
import com.bej.TaskService.service.ITaskService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class TaskController {

private ITaskService taskService;
private ResponseEntity responseEntity;
    @Autowired
    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        // Register a new user and save to db, return 201 status if user is saved else 500 status
        try {
            responseEntity = new ResponseEntity<>(taskService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }
        return responseEntity;
    }
    @GetMapping("/user/getTaskById/{taskID}")
    public ResponseEntity<?> getTaskByID(@PathVariable String taskID, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException {
//        try {
//            Claims claims = (Claims) request.getAttribute("Claims");
//            String userId = claims.getSubject();
//
//            if (userId.equals(claims.getSubject())) {
//                Task task = taskService.getTaskByID(userId, taskID);
//                if (task == null) {
//                    throw new TaskNotFoundException();
//                }
////                return ResponseEntity.ok(task);
//                responseEntity=new ResponseEntity<>(task,HttpStatus.OK);
//                return responseEntity;
//            }
//        } catch (TaskNotFoundException e) {
//            // If the task is not found, return 404 NOT FOUND status
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//           // throw new TaskNotFoundException();
//        }
//
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");


        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("Claims");
            String email = claims.getSubject();

            if (!email.equals(claims.getSubject())) {
                // If userId does not match, return 403 FORBIDDEN status
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Forbidden");
            }

            Task task = taskService.getTaskByID(email, taskID);

            if (task == null) {
                throw new TaskNotFoundException();
            }

            // If everything is successful, return 200 OK status with the task
            responseEntity = new ResponseEntity<>(task, HttpStatus.OK);
            return responseEntity;

        } catch (TaskNotFoundException e) {
            // If the task is not found, return 404 Not found status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // If other unexpected exceptions occur, return 500 INTERNAL SERVER ERROR status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
    @PostMapping("/user/addTaskList")
    public ResponseEntity<?> addTaskToList(@RequestBody Task task, HttpServletRequest request) throws UserAlreadyExistsException, UserNotFoundException, TaskAlreadyExistException {

//        try {
//            Claims claims = (Claims) request.getAttribute("Claims");
//            String userId = claims.getSubject();
//            responseEntity = new ResponseEntity<>(taskService.addTasktoList(userId, task), HttpStatus.CREATED);
//        } catch (UserNotFoundException e) {
//            // If the user is not found, return 404 NOT FOUND status
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } catch (TaskAlreadyExistsException e) {
//            // If the task already exists, return 409 CONFLICT status
//            return (ResponseEntity<User>) ResponseEntity.status(HttpStatus.CONFLICT);
//        } catch (Exception e) {
//            // If other unexpected exceptions occur, return 500 INTERNAL SERVER ERROR status
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//        return responseEntity;



        try {
           System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("Claims");
            System.out.println("claims in addList "+claims);
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: " +email);
            responseEntity = new ResponseEntity<>(taskService.addTasktoList(email,task), HttpStatus.CREATED);
        } catch (TaskAlreadyExistException | UserNotFoundException e) {
            e.printStackTrace ();
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("user/updateTask/task/{taskID}")
    public ResponseEntity<?> updateExistingTask(@RequestBody Task updatedTask, @PathVariable String taskID, HttpServletRequest request)
            throws UserNotFoundException, TaskNotFoundException {
//        Claims claims = (Claims) request.getAttribute ("claims");
//        String userId = claims.getSubject ();
//        // Check if the token's subject (user) matches the subject of the token generated during login.
//        if (userId.equals (claims.getSubject ())) {
//            return new ResponseEntity<> (taskService.updateExistingTask (userId,task), HttpStatus.OK);
//        } else {
//            // Handle unauthorized access (e.g., return 401 Unauthorized).
//            return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
//        }

        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("Claims");
            String email = claims.getSubject();

            if (email.equals(claims.getSubject())) {
                // Ensure that the taskID is set in the updatedTask object
                updatedTask.setTaskID(taskID);
                responseEntity = new ResponseEntity<>(taskService.updateExistingTask(email, updatedTask), HttpStatus.OK);
            } else {

                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException | TaskNotFoundException e) {
            if (e instanceof UserNotFoundException) {
                throw new UserNotFoundException();
            } else {
                throw new TaskNotFoundException();
            }
        }

        return responseEntity;
    }
    @DeleteMapping("user/deleteTask/{taskID}")
    public ResponseEntity<?> deleteExistingTask(@PathVariable String taskID, HttpServletRequest request)
            throws TaskNotFoundException {

        System.out.println("header" + request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("Claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String email = claims.getSubject();
        System.out.println("email :: "+email);
        try {
            responseEntity = new ResponseEntity<>(taskService.deleteExistingTask(email,taskID), HttpStatus.OK);
        } catch (UserNotFoundException | TaskNotFoundException m) {
            throw new TaskNotFoundException();
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/user/updateTaskPriority/task/{taskID}/priority")
    public ResponseEntity<?> updateTaskPriorityLevel(@PathVariable String taskID, @RequestBody String priority, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String email = claims.getSubject ();
            return new ResponseEntity<> (taskService.updateTaskPriority(email, taskID, priority), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException();
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user/allTasks")
    public ResponseEntity<?> getAllUserTasks(HttpServletRequest request) throws UserNotFoundException {

        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("Claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
            responseEntity = new ResponseEntity<>(taskService.getAllUserTasks(email), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }
    @GetMapping("/user/archived")
    public ResponseEntity<?> getArchivedTasks(HttpServletRequest request) {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String email = claims.getSubject ();
            String token = "Bearer" + request.getHeader ("Authorization");
            List<Task> task = taskService.getAllUserTasks (email);
            List<Task> tasks = taskService.getArchivedTasks (email);
            return new ResponseEntity<>(tasks, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user/completed")
    public ResponseEntity<?> getCompletedTasks(HttpServletRequest request) {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String email = claims.getSubject ();
            String token = "Bearer" + request.getHeader ("Authorization");
            List<Task> task = taskService.getAllUserTasks (email);
            List<Task> tasks = taskService.getCompletedTasks (email);
            return new ResponseEntity<> (tasks, HttpStatus.OK);
        }catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/user/updateTaskCompletion/task/{taskID}/isComplete")
    public ResponseEntity<?> updateTaskCompletion(@PathVariable String taskID, @RequestBody boolean isComplete, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String email = claims.getSubject ();
            return new ResponseEntity<> (taskService.updateTaskComplete (email, taskID, isComplete), HttpStatus.OK);
        } catch (UserNotFoundException e) {
//            throw new UserNotFoundException();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (TaskNotFoundException e) {
//            throw new TaskNotFoundException();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace (); //same as e.getMessage()
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/user/updateTaskArchive/task/{taskID}/isArchive")
    public ResponseEntity<?> updateTaskArchive(@PathVariable String taskID, @RequestBody boolean isArchive, HttpServletRequest request) throws UserNotFoundException, TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String email = claims.getSubject ();
            return new ResponseEntity<> (taskService.updateTaskArchive (email, taskID, isArchive), HttpStatus.OK);
        } catch (UserNotFoundException e) {
//            throw new UserNotFoundException();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (TaskNotFoundException e) {
//            throw new TaskNotFoundException();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private String getUserIdFromClaims(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("Claims");
        System.out.println("email from claims :: " + claims.getSubject());
        return claims.getSubject();
    }
}
