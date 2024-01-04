package com.bej.NotificationService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
@Document
public class Task {
    @Id
    private String taskID;
    private String taskHeading;
    private String taskContent;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private Priority priority;
    private String category;
    private boolean isCompleted;
    private LocalTime reminder;
    private boolean isArchived;

    public Task() {
    }

    public Task(String taskID, String taskHeading, String taskContent, LocalDate dueDate, LocalTime dueTime, Priority priority, String category, boolean isCompleted, LocalTime reminder, boolean isArchived) {
        this.taskID = taskID;
        this.taskHeading = taskHeading;
        this.taskContent = taskContent;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.priority = priority;
        this.category = category;
        this.isCompleted = isCompleted;
        this.reminder = reminder;
        this.isArchived = isArchived;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskHeading() {
        return taskHeading;
    }

    public void setTaskHeading(String taskHeading) {
        this.taskHeading = taskHeading;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalTime dueTime) {
        this.dueTime = dueTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalTime getReminder() {
        return reminder;
    }

    public void setReminder(LocalTime reminder) {
        this.reminder = reminder;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID='" + taskID + '\'' +
                ", taskHeading='" + taskHeading + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", dueDate=" + dueDate +
                ", dueTime=" + dueTime +
                ", priority=" + priority +
                ", category='" + category + '\'' +
                ", isCompleted=" + isCompleted +
                ", reminder=" + reminder +
                ", isArchived=" + isArchived +
                '}';
    }
}
