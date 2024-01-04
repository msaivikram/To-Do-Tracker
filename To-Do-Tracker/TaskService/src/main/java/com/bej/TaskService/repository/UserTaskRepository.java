package com.bej.TaskService.repository;

import com.bej.TaskService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTaskRepository extends MongoRepository<User,String> {
}
