package com.bej.NotificationService.controller;

import com.bej.NotificationService.domain.Task;
import com.bej.NotificationService.exception.TaskNotFoundException;
import com.bej.NotificationService.service.NotificationServiceImpl;
import feign.FeignException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class NotificationController {
    private NotificationServiceImpl service;

    @Autowired
    public NotificationController(NotificationServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/gettasks")
    public ResponseEntity<?> gettasks(HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            System.out.println ("Authheader:" + authheader);
            String email = claims.getSubject ();
            return new ResponseEntity<> (service.getTasks (email, authheader), HttpStatus.OK);
        }catch (FeignException.NotFound e) {
            e.printStackTrace ();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace ();
            throw new TaskNotFoundException();
        }
    }

    @GetMapping("/today")
    public ResponseEntity<?> getTasksForToday(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.todayTasks (email, authheader);

            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }
            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (TaskNotFoundException e) {
            e.printStackTrace ();
            throw new TaskNotFoundException ();
        }


    }

    @GetMapping("/todayTasksByPriority")
    public ResponseEntity<?> getTasksTodayByPriority(String email, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("Claims");//retrieving the claims from http request
        String authheader = request.getHeader("Authorization");
        List<Task> tasksForToday = service.todayTaskSortedByPriority(email, authheader);

        if (tasksForToday.isEmpty()) {
            String errorMessage = "You have no tasks " + LocalDate.now() + " this day";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(tasksForToday, HttpStatus.OK);
    }

    @GetMapping("/todayTasksByCompletion")
    public ResponseEntity<?> getTasksForTodayByCompletion(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.todayTaskSortedCompletionStatus (email, authheader);
            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }
            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todayTasksByTime")
    public ResponseEntity<?> getTasksForTodayByTime(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.todayTaskSortedByTime (email, authheader);

            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }

            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tomorrow")
    public ResponseEntity<?> getTasksForTomorrow(String email, HttpServletRequest request)throws TaskNotFoundException
    {
        try
        {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authHeader = request.getHeader ("Authorization");
            List<Task> tasksForTomorrow = service.tomorrowTasks (email, authHeader);
            if (tasksForTomorrow.isEmpty ()) {
                throw new TaskNotFoundException ();
            }
            return new ResponseEntity<> (tasksForTomorrow, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tomorrowTasksByPriority")
    public ResponseEntity<?> getTasksTomorrowByPriority(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.tomorrowTaskSortedByPriority (email, authheader);

            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }

            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tomorrowTasksByCompletion")
    public ResponseEntity<?> getTasksForTomorrowByCompletion(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.tomorrowTaskSortedCompletionStatus(email, authheader);

            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }
            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tomorrowTasksByTime")
    public ResponseEntity<?> getTasksForTomorrowByTime(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.tomorrowTaskSortedByTime (email, authheader);

            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }

            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/restOfTheTasks")
    public ResponseEntity<?> restOfTheTasks(String email, HttpServletRequest request) {
        try
        {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authHeader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.restOfTheTasks (email, authHeader);
            if (tasksForToday.isEmpty ())
            {
                throw new TaskNotFoundException ();
            }
            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/restOfTheTaskByPriority")
    public ResponseEntity<?> getRestOfTheTaskByPriority(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.restOfTheTaskSortedByPriority(email, authheader);

            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }

            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/restOfTheTasksByCompletion")
    public ResponseEntity<?> getRestOfTheTasksByCompletion(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.restOfTheTaskSortedCompletionStatus(email, authheader);

            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }
            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/restOfTheTaskByDate")
    public ResponseEntity<?> getRestOfTheTaskByDate(String email, HttpServletRequest request) throws TaskNotFoundException {
        try {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authheader = request.getHeader ("Authorization");
            List<Task> tasksForToday = service.restOfTheTaskSortedByDate (email, authheader);

            if (tasksForToday.isEmpty ()) {
                throw new TaskNotFoundException ();
            }
            return new ResponseEntity<> (tasksForToday, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/pastTasks")
    public ResponseEntity<?> getPastTasks(String email, HttpServletRequest request)throws TaskNotFoundException
    {
        try
        {
            Claims claims = (Claims) request.getAttribute ("Claims");
            String authHeader = request.getHeader ("Authorization");
            List<Task> tasksForTomorrow = service.pastTasks (email, authHeader);
            if (tasksForTomorrow.isEmpty ()) {
                throw new TaskNotFoundException ();
            }
            return new ResponseEntity<> (tasksForTomorrow, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace ();
            return new ResponseEntity<> (e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private String getUserIdFromClaims(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("Claims");
        System.out.println("Email from claims :: " + claims.getSubject());
        return claims.getSubject();
    }
}
