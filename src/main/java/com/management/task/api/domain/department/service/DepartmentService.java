package com.management.task.api.domain.department.service;

import com.management.task.api.domain.department.response.DepartmentResponse;
import com.management.task.api.domain.person.repository.PersonRepository;
import com.management.task.api.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final PersonRepository personRepository;
    private final TaskRepository taskRepository;

    public DepartmentResponse findAll() {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setTotalTask(taskRepository.count());
        departmentResponse.setDepartment(personRepository.findAllDepartments());
        departmentResponse.setTotalPerson(personRepository.count());
        return departmentResponse;
    }
}
