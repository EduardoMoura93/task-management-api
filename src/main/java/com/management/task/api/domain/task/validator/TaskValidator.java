package com.management.task.api.domain.task.validator;

import com.management.task.api.domain.person.model.PersonModel;
import com.management.task.api.domain.task.model.TaskModel;
import com.management.task.api.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.management.task.api.common.Constants.TASK_COMPLETED;
import static com.management.task.api.common.Constants.TASK_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class TaskValidator {
    private final TaskRepository repository;

    public TaskModel validatorById(Long taskId){
        return repository.findById(taskId).orElseThrow(() -> new IllegalArgumentException(TASK_NOT_FOUND));
    }

    public Boolean validatorDepartments(TaskModel task, PersonModel person){
       return task.getDepartment().equals(person.getDepartment());
    }

    public void isCompleted(Boolean completed) {
        if(completed){
            throw new IllegalArgumentException(TASK_COMPLETED);
        }
    }
}
