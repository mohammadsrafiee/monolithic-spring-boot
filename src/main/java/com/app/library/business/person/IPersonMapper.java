package com.app.library.business.person;

import com.app.library.business.person.impl.PersonEntity;
import com.app.library.business.person.impl.PersonModel;
import com.app.library.common.layer.IMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface IPersonMapper extends IMapper<PersonModel, PersonEntity> {
}
