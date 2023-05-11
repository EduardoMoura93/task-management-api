package com.management.task.api.domain.person;

import com.management.task.api.domain.person.request.PersonRequest;
import com.management.task.api.domain.person.response.PersonMessageResponse;
import com.management.task.api.domain.person.response.PersonResponse;
import com.management.task.api.domain.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonApi {

    private final PersonService service;

    @PostMapping
    public PersonMessageResponse create (@RequestBody PersonRequest request){
        return service.create(request);
    }

    @PutMapping("{personId}")
    public PersonMessageResponse update(@PathVariable(value = "personId") Long personId, @RequestBody PersonRequest request){
        return service.update(personId, request);
    }

    @DeleteMapping("{personId}")
    public PersonMessageResponse delete(@PathVariable(value = "personId") Long personId){
        return service.delete(personId);
    }

    @GetMapping
    public List<PersonResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/average-hours")
    public PersonResponse findByNameAndPeriod(@QueryParam("name") String name,
                                              @QueryParam("startDate") String startDate,
                                              @QueryParam("endDate") String endDate){
        return service.findByNameAndPeriod(name, startDate, endDate);
    }

}
