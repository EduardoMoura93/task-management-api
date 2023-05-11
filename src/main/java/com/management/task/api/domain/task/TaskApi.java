package com.management.task.api.domain.task;

import com.management.task.api.domain.task.request.TaskRequest;
import com.management.task.api.domain.task.response.TaskMessageResponse;
import com.management.task.api.domain.task.response.TaskResponse;
import com.management.task.api.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService service;

    @PostMapping
    public TaskMessageResponse create(@RequestBody TaskRequest request){
        return service.create(request);
    }

    @PutMapping("/allocate/taskId/{taskId}/personId/{personId}")
    public TaskMessageResponse addPersonToTask(@PathVariable(value = "taskId") Long taskId, @PathVariable(value = "personId") Long personId){
        return service.addPersonToTask(taskId, personId);
    }

    @PutMapping("/finalize/{taskId}")
    public TaskMessageResponse finalize(@PathVariable(value = "taskId") Long taskId){
        return service.finalize(taskId);
    }

    @GetMapping("/pending")
    List<TaskResponse> findPenging(){return service.findPenging();}
}
