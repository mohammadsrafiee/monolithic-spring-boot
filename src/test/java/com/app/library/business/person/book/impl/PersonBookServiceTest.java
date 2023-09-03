package com.app.library.business.person.book.impl;

import com.app.library.business.book.IBookRepository;
import com.app.library.business.book.impl.BookEntity;
import com.app.library.business.book.impl.BookModel;
import com.app.library.business.person.IPersonMapper;
import com.app.library.business.person.IPersonRepository;
import com.app.library.business.person.impl.PersonEntity;
import com.app.library.business.person.impl.PersonModel;
import com.app.library.common.search.SearchModel;
import com.app.library.business.book.IBookMapper;
import com.app.library.business.book.IBookService;
import com.app.library.business.person.IPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PersonBookServiceTest {

    @InjectMocks
    private PersonBookService personBookService;
    @Mock
    private IPersonRepository personRepository;
    @Mock
    private IPersonService personService;
    @Mock
    private IPersonMapper personMapper;
    @Mock
    private IBookRepository bookRepository;
    @Mock
    private IBookService bookService;
    @Mock
    private IBookMapper bookMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBorrowBook() {
        Long bookId = 1L;
        Long userId = 0L;
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        bookEntity.setCount(1);
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(userId);
        when(bookRepository.getReferenceById(bookId)).thenReturn(bookEntity);
        when(personRepository.getReferenceById(userId)).thenReturn(personEntity);
        when(personMapper.toModel(any())).thenReturn(new PersonModel());
        PersonModel result = personBookService.borrowBook(bookId);
        assertEquals(0, bookEntity.getCount());
        assertEquals(1, personEntity.getBooks().size());
        verify(bookRepository, times(1)).save(bookEntity);
        verify(personRepository, times(1)).save(personEntity);
    }

    @Test
    void testReleaseBook() {
        Long bookId = 1L;
        Long userId = 0L;
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        bookEntity.setCount(0);
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(userId);
        personEntity.getBooks().add(bookEntity);
        when(bookRepository.getReferenceById(bookId)).thenReturn(bookEntity);
        when(personRepository.getReferenceById(userId)).thenReturn(personEntity);
        when(personMapper.toModel(any())).thenReturn(new PersonModel());
        PersonModel result = personBookService.releaseBook(bookId);
        assertEquals(1, bookEntity.getCount());
        assertEquals(0, personEntity.getBooks().size());
        verify(bookRepository, times(1)).save(bookEntity);
        verify(personRepository, times(1)).save(personEntity);
    }

    @Test
    void testMyBooks() {
        Long userId = 0L;
        PersonModel personModel = new PersonModel();
        personModel.setId(userId);
        when(personService.getById(userId)).thenReturn(personModel);
        List<BookModel> books = personBookService.myBooks(new SearchModel<>());
        assertEquals(personModel.getBooks(), books);
    }
}