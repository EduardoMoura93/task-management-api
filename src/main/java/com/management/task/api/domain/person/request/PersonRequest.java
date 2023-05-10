package com.management.task.api.domain.person.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {
    private Long id;
    private String name;
    private String department;
}
