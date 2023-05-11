package com.management.task.api.domain.task.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.management.task.api.domain.person.model.PersonModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String department;
    private Long duration;
    private Boolean completed;
    private LocalDateTime completedDate;
}
