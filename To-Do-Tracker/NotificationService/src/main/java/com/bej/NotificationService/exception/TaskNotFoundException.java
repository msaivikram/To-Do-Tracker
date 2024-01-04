package com.bej.NotificationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason="The Task is not Found")
public class TaskNotFoundException extends Exception{
}
