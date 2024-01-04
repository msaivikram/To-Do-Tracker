package com.bej.NotificationService.repository;

import com.bej.NotificationService.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Task,String> {
}
