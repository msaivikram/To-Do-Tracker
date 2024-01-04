package com.bej.NotificationService.service;

import com.bej.NotificationService.domain.Priority;
import com.bej.NotificationService.domain.Task;
import com.bej.NotificationService.proxy.NotificationProxy;
import com.bej.NotificationService.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements INotificationService{
    private NotificationProxy notificationProxy;
    private NotificationRepository notificationRepository;
    @Autowired
    public NotificationServiceImpl(NotificationProxy notificationProxy, NotificationRepository notificationRepository) {
        this.notificationProxy = notificationProxy;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Task> getTasks(String email, String authheader) {
        List<Task> tasklist= (List<Task>) notificationProxy.getAllUserTasks(email,authheader).getBody();
        return tasklist;
    }

    @Override
    public List<Task> todayTasks(String email, String authHeader) {
        LocalDate currentDate = LocalDate.now();
        List<Task> allTasks = (List<Task>) notificationProxy.getAllUserTasks(email,authHeader).getBody();
        List<Task> tasksForToday = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.getDueDate() != null && task.getDueDate().isEqual(currentDate)) {
                tasksForToday.add(task);
            }
        }

        return tasksForToday;
    }

    @Override
    public List<Task> todayTaskSortedByPriority(String email, String authHeader) { //not working
        List<Task> tasksForToday = todayTasks(email, authHeader);
        Comparator<Task> priorityComparator = Comparator.comparing(task -> {
            Priority priority = task.getPriority();
            if (priority == Priority.HIGH) {
                return 0;
            } else if (priority == Priority.MEDIUM) {
                return 1;
            } else if (priority==Priority.LOW){
                return 2;
            }
            return null;
        });
        tasksForToday.sort (priorityComparator);
        return tasksForToday;

    }

    @Override
    public List<Task> todayTaskSortedCompletionStatus(String email, String authHeader) {

        List<Task> tasksForToday = todayTasks(email, authHeader);
        Comparator<Task> completionChecker = Comparator.comparing(task -> {
            boolean completionStatus = task.isCompleted ();
            if (completionStatus == true) {
                return 1;
            } else
            {
                return 2;
            }
        });
        tasksForToday.sort (completionChecker);
        return tasksForToday;
    }

    @Override
    public List<Task> todayTaskSortedByTime(String email, String authHeader) { //not working
        List<Task> tasksForToday = todayTasks(email, authHeader);
        Comparator<Task> timeComparator = (task1, task2) -> task1.getDueTime ().compareTo(task2.getDueTime ());

        tasksForToday.sort(timeComparator);

        return tasksForToday;
    }

    @Override
    public List<Task> tomorrowTasks(String email, String authHeader) {
        LocalDate currentDate = LocalDate.now ().plusDays (1);
        List<Task> allTasks = (List<Task>) notificationProxy.getAllUserTasks(email,authHeader).getBody();
        List<Task> tasksForTomorrow = new ArrayList<> ();
        for (Task task : allTasks)
        {
            if (task.getDueDate () != null && task.getDueDate ().isEqual (currentDate))
            {
                tasksForTomorrow.add (task);
            }
        }
        return tasksForTomorrow;

    }

    @Override
    public List<Task> tomorrowTaskSortedByPriority(String email, String authHeader) { //not working
        List<Task> tomorrowTasksSortedByPriority = tomorrowTasks (email, authHeader);
        Comparator<Task> priorityComparator = Comparator.comparing(task -> {
            Priority priority = task.getPriority();
            if (priority == Priority.HIGH) {
                return 1;
            } else if (priority == Priority.MEDIUM) {
                return 2;
            } else {
                return 3;
            }
        });
        tomorrowTasksSortedByPriority.sort (priorityComparator);
        return tomorrowTasksSortedByPriority;

    }

    @Override
    public List<Task> tomorrowTaskSortedCompletionStatus(String email, String authHeader) {
        List<Task> tomorrowTasksSortedByCompletion = tomorrowTasks (email, authHeader);
        Comparator<Task> completionChecker = Comparator.comparing(task -> {
            boolean completionStatus = task.isCompleted ();
            if (completionStatus == true) {
                return 1;
            } else
            {
                return 2;
            }
        });
        tomorrowTasksSortedByCompletion.sort (completionChecker);
        return tomorrowTasksSortedByCompletion;

    }

    @Override
    public List<Task> tomorrowTaskSortedByTime(String email, String authHeader) {
        List<Task> tomorrowTasksSortedByTime = tomorrowTasks (email, authHeader);
        Comparator<Task> timeComparator = (task1, task2) -> task1.getDueTime().compareTo(task2.getDueTime());

        tomorrowTasksSortedByTime.sort(timeComparator);

        return tomorrowTasksSortedByTime;

    }

    @Override
    public List<Task> restOfTheTasks(String email, String authHeader) { //after tommorow any date
        LocalDate currentDate = LocalDate.now();
        LocalDate tomorrowDate = currentDate.plusDays(1);

        List<Task> allTasks = (List<Task>) notificationProxy.getAllUserTasks(email, authHeader).getBody();
        List<Task> remainingTasks = new ArrayList<>();

        for (Task task : allTasks) {
            LocalDate taskDueDate = task.getDueDate();
            if (taskDueDate != null && !taskDueDate.isEqual(currentDate) && !taskDueDate.isEqual(tomorrowDate) && !taskDueDate.isBefore(currentDate)) {
                remainingTasks.add(task);
            }
        }
        return remainingTasks;

    }

    @Override
    public List<Task> restOfTheTaskSortedByPriority(String email, String authHeader) { //not working
        List<Task> restOfTheTaskSortedByPriority = restOfTheTasks (email, authHeader);
        Comparator<Task> priorityComparator = Comparator.comparing(task -> {
            Priority priority = task.getPriority();
            if (priority == Priority.HIGH) {
                return 1;
            } else if (priority == Priority.MEDIUM) {
                return 2;
            } else {
                return 3;
            }
        });
        restOfTheTaskSortedByPriority.sort (priorityComparator);
        return restOfTheTaskSortedByPriority;

    }

    @Override
    public List<Task> restOfTheTaskSortedCompletionStatus(String email, String authHeader) {
        List<Task> restOfTheTaskSortedCompletionStatus = restOfTheTasks(email, authHeader);
        Comparator<Task> completionChecker = Comparator.comparing(task -> {
            boolean completionStatus = task.isCompleted ();
            if (completionStatus == true) {
                return 1;
            } else
            {
                return 2;
            }
        });
        restOfTheTaskSortedCompletionStatus.sort (completionChecker);
        return restOfTheTaskSortedCompletionStatus;

    }

    @Override
    public List<Task> restOfTheTaskSortedByDate(String email, String authHeader) { //not working
        List<Task> tomorrowTasks = restOfTheTasks(email, authHeader);
        Comparator<Task> dateAndTimeComparator = (task1, task2) -> {
            LocalDate dueDate1 = task1.getDueDate();
            LocalDate dueDate2 = task2.getDueDate();
            if (dueDate1.isEqual(dueDate2)) {
                LocalTime time1 = task1.getDueTime ();
                LocalTime time2 = task2.getDueTime ();
                return time1.compareTo(time2);
            } else {
                return dueDate1.compareTo(dueDate2);
            }
        };
        tomorrowTasks.sort(dateAndTimeComparator);
        return tomorrowTasks;

    }

    @Override
    public List<Task> pastTasks(String email, String authHeader) {
        LocalDate currentDate = LocalDate.now();
        List<Task> allTasks = (List<Task>) notificationProxy.getAllUserTasks(email, authHeader).getBody();
        List<Task> pastTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getDueDate() != null && task.getDueDate().isBefore(currentDate)) {
                pastTasks.add(task);
            }
        }
        return pastTasks;

    }

    @Override
    public Task findTaskById(String taskID, String authHeader) {
        		List<Task> allTasks = notificationProxy.getAllUserTasks(taskID, authHeader).getBody();


		Optional<Task> foundTask = Optional.empty ();
		for (Task task : allTasks) {
			if (task.getTaskID ().equals (taskID)) {
				foundTask = Optional.of (task);
				break;
			}
		}

        return (null);
    }
//doubts suryanna
//1. why optional keyword is usesded here   2. sortbycompletionstatus   3. sort by date in resT...  4. REST of tsk by priority...
    //jwt filter
}
