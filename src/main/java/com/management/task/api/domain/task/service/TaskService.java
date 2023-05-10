package com.management.task.api.domain.task.service;

import com.management.task.api.domain.person.model.PersonModel;
import com.management.task.api.domain.person.validator.PersonValidator;
import com.management.task.api.domain.task.assembler.TaskAssemblerMapper;
import com.management.task.api.domain.task.model.TaskModel;
import com.management.task.api.domain.task.repository.TaskRepository;
import com.management.task.api.domain.task.request.TaskRequest;
import com.management.task.api.domain.task.response.TaskMessageResponse;
import com.management.task.api.domain.task.validator.TaskValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.management.task.api.common.Constants.*;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskAssemblerMapper mapper;
    private final PersonValidator personValidator;
    private final TaskValidator taskValidator;

    public TaskMessageResponse create(TaskRequest request) {
        repository.save(mapper.toEntity(request));
        return new TaskMessageResponse(SUCCESSFULLY_REGISTERED);

    }

    public TaskMessageResponse addPersonToTask(Long taskId, Long personId) {
        PersonModel person = personValidator.validatorById(personId);
        TaskModel task = taskValidator.validatorById(taskId);

        taskValidator.isCompleted(task.getCompleted());

        if(taskValidator.validatorDepartments(task, person)){
            task.setAssignedPerson(person);
            repository.save(task);
            return new TaskMessageResponse(person.getName() +  SUCCESSFULLY_PERSON_TO_TASK + task.getTitle());
        }
       throw new IllegalArgumentException(DIFFERENT_SECTORS);
    }

    public TaskMessageResponse finalize(Long taskId) {
        TaskModel task = taskValidator.validatorById(taskId);

        taskValidator.isCompleted(task.getCompleted());

        task.setCompleted(true);
        task.setDuration(calculateDuration(task.getDeadline()));
        repository.save(task);

        return new TaskMessageResponse(FINISHED_TASK + task.getDuration());
    }

    public String calculateDuration(LocalDateTime deadline){
        LocalDateTime now = LocalDateTime.now();
        Duration duration;
        if (deadline.isBefore(now)) {
            duration = Duration.between(deadline, now);
        } else {
            duration = Duration.between(now, deadline);
        }
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        LocalTime time = LocalTime.of((int) hours, (int) minutes);
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


}
