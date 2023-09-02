package com.app.library.business.person.impl;

import com.app.library.business.person.IPersonMapper;
import com.app.library.business.person.IPersonRepository;
import com.app.library.business.person.IPersonService;
import com.app.library.common.log.impl.Log;
import com.app.library.common.search.FilterQuery;
import com.app.library.common.search.SearchModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    private final IPersonRepository repository;
    private final IPersonMapper mapper;

    public PersonService(IPersonRepository repository, IPersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Log
    @Override
    public PersonModel saveOrUpdate(PersonModel person) {
        if ((person != null) && (person.getId() != null)) {
            PersonModel byId = this.getById(person.getId());
            person.setBooks(byId.getBooks());
        }
        return this.mapper.toModel(
                repository.save(
                        mapper.toEntity(person)));
    }

    @Log
    @Override
    public PersonModel getById(Long id) {
        return mapper.toModel(repository.getReferenceById(id));
    }

    @Log
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Log
    @Override
    public List<PersonModel> getAll(SearchModel<PersonEntity> search) {
        List<PersonModel> result = new ArrayList<>();
        List<PersonEntity> persons = new ArrayList<>();
        Example<PersonEntity> example = FilterQuery.example(search);
        Page<PersonEntity> personsPage;
        if (example == null) {
            personsPage = repository.findAll(FilterQuery.pageRequest(search));
        } else {
            personsPage = repository.findAll(example, FilterQuery.pageRequest(search));
        }
        if (personsPage != null) {
            persons = personsPage.getContent();
        }
        persons.forEach((e) -> result.add(mapper.toModel(e)));
        return result;
    }

}
