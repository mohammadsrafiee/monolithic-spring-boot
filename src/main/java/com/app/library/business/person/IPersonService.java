package com.app.library.business.person;

import com.app.library.business.person.impl.PersonEntity;
import com.app.library.business.person.impl.PersonModel;
import com.app.library.common.layer.IService;
import com.app.library.common.search.SearchModel;

import java.util.List;

public interface IPersonService extends IService {

    PersonModel saveOrUpdate(PersonModel person);

    PersonModel getById(Long id);

    List<PersonModel> getAll(SearchModel<PersonEntity> search);

    void delete(Long id);
}
