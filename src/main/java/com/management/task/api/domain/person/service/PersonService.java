package com.management.task.api.domain.person.service;

import com.management.task.api.domain.person.assembler.PersonAssemblerMapper;
import com.management.task.api.domain.person.model.PersonModel;
import com.management.task.api.domain.person.repository.PersonRepository;
import com.management.task.api.domain.person.request.PersonRequest;
import com.management.task.api.domain.person.response.PersonMessageResponse;
import com.management.task.api.domain.person.response.PersonResponse;
import com.management.task.api.domain.person.validator.PersonValidator;
import com.management.task.api.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.management.task.api.common.Constants.*;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;
    private final PersonAssemblerMapper mapper;
    private final PersonValidator validator;
    private final TaskService taskService;

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

    public List<PersonResponse> findAll() {
        List<PersonModel> personModels = repository.findAll();
        return personModels.stream().map(this::converter).collect(Collectors.toList());
    }

    public PersonResponse converter(PersonModel personModel){
        PersonResponse person = new PersonResponse();
        person.setName(personModel.getName());
        person.setDepartment(personModel.getDepartment());
        person.setTotalHoursOnTasks(taskService.calculateTotalHours(personModel.getTasks()));
        return person;
    }

    public PersonResponse findByNameAndPeriod(String name, String startDate, String endDate) {
        PersonResponse person = new PersonResponse();
        PersonModel personModel = validator.validatorByName(name);

        person.setName(personModel.getName());
        person.setDepartment(personModel.getDepartment());
        person.setAverageHours(taskService.calculateAverageHoursByPeriod(personModel, startDate, endDate));
        return person;
    }
}
