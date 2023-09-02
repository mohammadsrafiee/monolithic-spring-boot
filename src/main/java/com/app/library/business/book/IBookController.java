package com.app.library.business.book;

import com.app.library.business.book.impl.BookEntity;
import com.app.library.business.book.impl.BookModel;
import com.app.library.common.layer.IController;
import com.app.library.common.search.SearchModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface IBookController extends IController {
    String BOOK_PATH = "/book";
    String SAVE_OR_UPDATE_PATH = "/";
    String DELETE_PATH = "/{id}";
    String GET_BY_ID_PATH = "/{id}";
    String SEARCH_PATH = "/search";

    @PostMapping(path = SAVE_OR_UPDATE_PATH)
    ResponseEntity<BookModel> saveOrUpdate(@Valid @RequestBody BookModel book);

    @GetMapping(path = GET_BY_ID_PATH)
    ResponseEntity<BookModel> getById(@PathVariable Long id);

    @PostMapping(SEARCH_PATH)
    ResponseEntity<List<BookModel>> getAll(@RequestBody SearchModel<BookEntity> search);

    @DeleteMapping(DELETE_PATH)
    ResponseEntity<Void> delete(@PathVariable Long id);
}
