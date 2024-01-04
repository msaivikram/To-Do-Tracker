package com.bej.NotificationService.proxy;

import com.bej.NotificationService.domain.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient(name ="user-task-service", url = "http://localhost:8087")
public interface NotificationProxy {

    @GetMapping("/api/v2/user/allTasks")
    public ResponseEntity<List<Task>> getAllUserTasks(@RequestParam("email") String email, @RequestHeader("Authorization") String authHeader) ;
}
