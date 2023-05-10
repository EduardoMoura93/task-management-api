package com.management.task.api.domain.person.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonMessageResponse {
    private String message;
    public PersonMessageResponse(String message) {
        this.message = message;
    }
}
