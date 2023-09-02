package com.app.library.business.person.impl;

import com.app.library.business.person.IPersonController;
import com.app.library.business.person.IPersonService;
import com.app.library.common.log.impl.Log;
import com.app.library.common.search.SearchModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PersonController.PERSON_PATH)
public class PersonController implements IPersonController {
    private final IPersonService service;

    public PersonController(IPersonService service) {
        this.service = service;
    }

    @Log
    @Override
    public ResponseEntity<PersonModel> saveOrUpdate(PersonModel person) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.saveOrUpdate(person));
    }

    @Log
    @Override
    public ResponseEntity<PersonModel> getById(Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @Log
    @Override
    public ResponseEntity<List<PersonModel>> getAll(SearchModel<PersonEntity> search) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAll(search));
    }

    @Log
    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}