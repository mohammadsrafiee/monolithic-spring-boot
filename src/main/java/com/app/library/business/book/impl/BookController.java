package com.app.library.business.book.impl;

import com.app.library.business.book.IBookController;
import com.app.library.common.log.impl.Log;
import com.app.library.common.search.SearchModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(IBookController.BOOK_PATH)
public class BookController implements IBookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @Log
    @Override
    public ResponseEntity<BookModel> saveOrUpdate(BookModel book) {
        BookModel updatedBook = service.saveOrUpdate(book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedBook);
    }

    @Log
    @Override
    public ResponseEntity<BookModel> getById(Long bookId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getById(bookId));
    }

    @Log
    @Override
    public ResponseEntity<List<BookModel>> getAll(SearchModel<BookEntity> search) {
        List<BookModel> books = service.getAll(search);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(books);
    }

    @Log
    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}