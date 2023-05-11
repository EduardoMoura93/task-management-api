package com.management.task.api.domain.department;

import com.management.task.api.domain.department.response.DepartmentResponse;
import com.management.task.api.domain.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/department")
@RequiredArgsConstructor
public class DepartmentsApi {

    private final DepartmentService service;

    @GetMapping
    public DepartmentResponse findAll(){return service.findAll();}

}
