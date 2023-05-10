package com.management.task.api.domain.person.assembler;

import com.management.task.api.common.AbstractMapper;
import com.management.task.api.domain.person.model.PersonModel;
import com.management.task.api.domain.person.request.PersonRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonAssemblerMapper extends AbstractMapper<PersonModel, PersonRequest> {
}
