package com.app.library.business.person.book;

import com.app.library.business.book.impl.BookModel;
import com.app.library.business.person.impl.PersonModel;
import com.app.library.common.layer.IController;
import com.app.library.common.search.SearchModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IPersonBookController extends IController {
    String BASE = "/";
    String PERSON_BOOK_PATH = BASE + "person-book";
    String BORROW_PATH = BASE + "{book-id}";
    String RELEASE_PATH = BASE + "{book-id}";
    String MY_BOOKS_PATH = "/";

    @PostMapping(BORROW_PATH)
    ResponseEntity<PersonModel> borrowBook(@PathVariable("book-id") Long bookId);

    @DeleteMapping(RELEASE_PATH)
    ResponseEntity<PersonModel> releaseBook(@PathVariable("book-id") Long bookId);

    @PostMapping(MY_BOOKS_PATH)
    ResponseEntity<List<BookModel>> myBooks(@RequestBody SearchModel<PersonModel> search);
}
