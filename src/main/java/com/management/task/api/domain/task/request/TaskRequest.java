package com.management.task.api.domain.task.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequest {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String department;
    private Long assignedPersonId;
    private Boolean completed;
}
