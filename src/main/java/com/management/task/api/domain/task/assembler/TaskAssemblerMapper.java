package com.management.task.api.domain.task.assembler;

import com.management.task.api.common.AbstractMapper;
import com.management.task.api.domain.task.model.TaskModel;
import com.management.task.api.domain.task.request.TaskRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskAssemblerMapper extends AbstractMapper<TaskModel, TaskRequest> {
}
