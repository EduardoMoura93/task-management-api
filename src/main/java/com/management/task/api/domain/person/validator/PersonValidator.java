package com.management.task.api.domain.person.validator;

import com.management.task.api.domain.person.model.PersonModel;
import com.management.task.api.domain.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.management.task.api.common.Constants.PERSON_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class PersonValidator {
    private final PersonRepository repository;

    public PersonModel validatorById(Long personId){
        return repository.findById(personId).orElseThrow(() -> new IllegalArgumentException(PERSON_NOT_FOUND + personId));
    }
}
