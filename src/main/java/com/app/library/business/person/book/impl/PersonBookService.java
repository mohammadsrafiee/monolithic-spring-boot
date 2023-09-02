package com.app.library.business.person.book.impl;

import com.app.library.business.book.IBookMapper;
import com.app.library.business.book.IBookRepository;
import com.app.library.business.book.IBookService;
import com.app.library.business.book.impl.BookEntity;
import com.app.library.business.book.impl.BookModel;
import com.app.library.business.person.IPersonMapper;
import com.app.library.business.person.IPersonRepository;
import com.app.library.business.person.IPersonService;
import com.app.library.business.person.book.IPersonBookService;
import com.app.library.business.person.impl.PersonEntity;
import com.app.library.business.person.impl.PersonModel;
import com.app.library.common.log.impl.Log;
import com.app.library.common.search.SearchModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonBookService implements IPersonBookService {
    private final IPersonRepository personRepository;
    private final IPersonService personService;
    private final IPersonMapper personMapper;
    private final IBookRepository bookRepository;
    private final IBookService bookService;
    private final IBookMapper bookMapper;

    // TODO change it by security context
    private static final Long USER_ID = 0L;

    public PersonBookService(IPersonMapper personMapper,
                             IBookMapper bookMapper,
                             IPersonService personService,
                             IBookService bookService,
                             IBookRepository bookRepository,
                             IPersonRepository personRepository) {
        this.personService = personService;
        this.bookService = bookService;
        this.personMapper = personMapper;
        this.bookMapper = bookMapper;
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }


    @Log
    @Override
    @Transactional
    public PersonModel borrowBook(Long bookId) {
        PersonModel result = null;
        BookEntity book = bookRepository.getReferenceById(bookId);
        PersonEntity person = personRepository.getReferenceById(USER_ID);
        if (book.getCount() > 0) {
            book.setCount(book.getCount() - 1);
            bookRepository.save(book);
            person.getBooks().add(book);
            result = personMapper.toModel(personRepository.save(person));
        }
        return result;
    }

    @Log
    @Override
    @Transactional
    public PersonModel releaseBook(Long id) {
        PersonModel result = null;
        BookEntity book = bookRepository.getReferenceById(id);
        PersonEntity person = personRepository.getReferenceById(USER_ID);
        if ((book.getId() != null) &&
                (person.getId() != null)) {
            person.getBooks().remove(book);
            result = personMapper.toModel(personRepository.save(person));
            book.setCount(book.getCount() + 1);
            bookRepository.save(book);
        }
        return result;
    }

    @Log
    @Override
    public List<BookModel> myBooks(SearchModel<PersonModel> search) {
        PersonModel person = personService.getById(USER_ID);
        if (person != null) {
            return new ArrayList<>(person.getBooks());
        }
        return List.of();
    }
}