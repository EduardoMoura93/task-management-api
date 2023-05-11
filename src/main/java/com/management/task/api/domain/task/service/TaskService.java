package com.management.task.api.domain.task.service;

import com.management.task.api.domain.person.model.PersonModel;
import com.management.task.api.domain.person.validator.PersonValidator;
import com.management.task.api.domain.task.assembler.TaskAssemblerMapper;
import com.management.task.api.domain.task.assembler.TaskResponseAssemblerMapper;
import com.management.task.api.domain.task.model.TaskModel;
import com.management.task.api.domain.task.repository.TaskRepository;
import com.management.task.api.domain.task.request.TaskRequest;
import com.management.task.api.domain.task.response.TaskMessageResponse;
import com.management.task.api.domain.task.response.TaskResponse;
import com.management.task.api.domain.task.validator.TaskValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.management.task.api.common.Constants.*;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskAssemblerMapper mapper;
    private final TaskResponseAssemblerMapper taskMapper;
    private final PersonValidator personValidator;
    private final TaskValidator taskValidator;

    public TaskMessageResponse create(TaskRequest request) {
        request.setCompleted(false);
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
        task.setCompletedDate(LocalDateTime.now());
        repository.save(task);

        return new TaskMessageResponse(FINISHED_TASK);
    }

    public Long calculateDuration(LocalDateTime deadline) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration;
        if (deadline.isBefore(now)) {
            duration = Duration.between(deadline, now);
        } else {
            duration = Duration.between(now, deadline);
        }
        return duration.toMinutes();
    }

    public String calculateTotalHours(List<TaskModel> tasks) {
        Long totalMinutes = 0L;
        for (TaskModel task : tasks) {
            totalMinutes += task.getDuration();
        }
       return formatTime(totalMinutes);
    }

    public String formatTime(long minutes) {
        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;
        return String.format("%02d:%02d", hours, remainingMinutes);
    }

    public String calculateAverageHoursByPeriod(PersonModel personModel, String startDate, String endDate) {
        String start = startDate + " 00:00:00";
        String end = endDate + " 23:59:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<TaskModel> tasks = personModel.getTasks();
        int taskCount = 0;
        long totalDuration = 0;

        for (TaskModel task : tasks) {
            LocalDateTime completedDate = task.getCompletedDate();
            if (completedDate != null) {
                LocalDateTime taskDateTime = LocalDateTime.parse(completedDate.format(formatter), formatter);
                LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);

                if (taskDateTime.isAfter(startDateTime) && taskDateTime.isBefore(endDateTime)) {
                    long duration = task.getDuration();
                    totalDuration += duration;
                    taskCount++;
                }
            }
        }

        if (taskCount == 0) {
            return NO_TASKS_COMPLETED;
        } else {
            long averageDuration = totalDuration / taskCount;
            return formatTime(averageDuration);
        }
    }

    public List<TaskResponse> findPenging() {
        return taskMapper.toResources(repository.findTop3ByAssignedPersonIsNullOrderByDeadlineAsc());
    }
}
