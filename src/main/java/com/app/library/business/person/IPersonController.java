package com.app.library.business.person;

import com.app.library.business.person.impl.PersonEntity;
import com.app.library.business.person.impl.PersonModel;
import com.app.library.common.layer.IController;
import com.app.library.common.log.impl.Log;
import com.app.library.common.search.SearchModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IPersonController extends IController {
    String PERSON_PATH = "/person";
    String SAVE_OR_UPDATE_PATH = "/";
    String DELETE_PATH = "/{id}";
    String GET_BY_ID_PATH = "/{id}";
    String SEARCH_PATH = "/search";

    @PostMapping(path = SAVE_OR_UPDATE_PATH)
    ResponseEntity<PersonModel> saveOrUpdate(@RequestBody PersonModel book);

    @GetMapping(path = GET_BY_ID_PATH)
    ResponseEntity<PersonModel> getById(@PathVariable Long id);

    @PostMapping(SEARCH_PATH)
    ResponseEntity<List<PersonModel>> getAll(@RequestBody SearchModel<PersonEntity> search);

    @DeleteMapping(DELETE_PATH)
    ResponseEntity<Void> delete(@PathVariable Long id);
}
