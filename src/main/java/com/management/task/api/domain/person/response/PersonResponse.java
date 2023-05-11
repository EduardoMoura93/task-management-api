package com.management.task.api.domain.person.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponse {
    private String name;
    private String department;
    private String totalHoursOnTasks;
    private String averageHours;
}
