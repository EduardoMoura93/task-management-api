package com.management.task.api.domain.department.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentResponse {
    List<String> department;
    Long totalPerson;
    Long totalTask;
}
