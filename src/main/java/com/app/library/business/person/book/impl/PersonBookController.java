package com.app.library.business.person.book.impl;

import com.app.library.business.book.impl.BookModel;
import com.app.library.business.person.book.IPersonBookController;
import com.app.library.business.person.book.IPersonBookService;
import com.app.library.business.person.impl.PersonModel;
import com.app.library.common.log.impl.Log;
import com.app.library.common.search.SearchModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(IPersonBookController.PERSON_BOOK_PATH)
public class PersonBookController implements IPersonBookController {

    private final IPersonBookService personBookService;

    public PersonBookController(IPersonBookService personBookService) {
        this.personBookService = personBookService;
    }

    @Log
    @Override
    public ResponseEntity<PersonModel> borrowBook(Long bookId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personBookService.borrowBook(bookId));
    }

    @Log
    @Override
    public ResponseEntity<PersonModel> releaseBook(Long bookId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personBookService.releaseBook(bookId));
    }

    @Log
    @Override
    public ResponseEntity<List<BookModel>> myBooks(SearchModel<PersonModel> search) {
        // TODO use Credential Principle
        //  now I am writing sample
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personBookService.myBooks(search));
    }
}