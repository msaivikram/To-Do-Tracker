package com.bej.TaskService.proxy;

import com.bej.TaskService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Component
@FeignClient(name="user-authentication-service",url = "localhost:8083")
public interface TaskProxy {
    @PostMapping("/api/v1/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
