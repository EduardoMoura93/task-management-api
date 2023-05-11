package com.management.task.api.domain.task.assembler;

import com.management.task.api.common.AbstractMapper;
import com.management.task.api.domain.task.model.TaskModel;
import com.management.task.api.domain.task.response.TaskResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskResponseAssemblerMapper extends AbstractMapper<TaskModel, TaskResponse> {
}
