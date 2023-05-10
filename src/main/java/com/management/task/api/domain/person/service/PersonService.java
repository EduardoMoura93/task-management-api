package com.management.task.api.domain.person.service;

import com.management.task.api.domain.person.assembler.PersonAssemblerMapper;
import com.management.task.api.domain.person.repository.PersonRepository;
import com.management.task.api.domain.person.request.PersonRequest;
import com.management.task.api.domain.person.response.PersonMessageResponse;
import com.management.task.api.domain.person.validator.PersonValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.management.task.api.common.Constants.*;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonAssemblerMapper mapper;
    private final PersonValidator validator;

    public PersonMessageResponse create(PersonRequest request) {
        repository.save(mapper.toEntity(request));
        return new PersonMessageResponse(SUCCESSFULLY_REGISTERED);
    }

    public PersonMessageResponse update(Long personId, PersonRequest request) {
        validator.validatorById(personId);
        request.setId(personId);
        repository.save(mapper.toEntity(request));
        return new PersonMessageResponse(SUCCESSFULLY_UPDATE);
    }

    public PersonMessageResponse delete(Long personId) {
        validator.validatorById(personId);
        repository.deleteById(personId);
        return new PersonMessageResponse(SUCCESSFULLY_DELETE);
    }
}
