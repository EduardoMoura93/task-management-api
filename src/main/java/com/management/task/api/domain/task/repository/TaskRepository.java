package com.management.task.api.domain.task.repository;

import com.management.task.api.domain.task.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findTop3ByAssignedPersonIsNullOrderByDeadlineAsc();
}

