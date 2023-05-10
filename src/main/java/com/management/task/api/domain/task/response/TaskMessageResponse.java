package com.management.task.api.domain.task.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskMessageResponse {
    private String message;
    public TaskMessageResponse(String message) {
        this.message = message;
    }
}
