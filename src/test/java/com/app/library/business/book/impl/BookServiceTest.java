package com.app.library.business.book.impl;

import com.app.library.business.book.IBookMapper;
import com.app.library.business.book.IBookRepository;
import com.app.library.common.search.SearchModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private IBookRepository bookRepository;
    @Mock
    private IBookMapper bookMapper;

    @Test
    void testSaveOrUpdate() {
        BookModel book = new BookModel();
        book.setId(1L);
        book.setName("Mohammad");
        when(bookRepository.save(any())).thenReturn(new BookEntity());
        when(bookMapper.toEntity(any())).thenReturn(new BookEntity());
        when(bookMapper.toModel(any())).thenReturn(book);
        BookModel result = bookService.saveOrUpdate(book);
        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
        assertEquals("Mohammad", result.getName());
    }

    @Test
    void testGetById() {
        when(bookRepository.getReferenceById(1L)).thenReturn(new BookEntity());
        when(bookMapper.toModel(any())).thenReturn(new BookModel());
        BookModel result = bookService.getById(1L);
        assertNotNull(result);
    }

    @Test
    void testDelete() {
        bookService.delete(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    void testGetAll() {
        SearchModel<BookEntity> searchModel = new SearchModel<>();
        Page mockPage = mock(Page.class);
        when(bookRepository.findAll(any(), any(PageRequest.class))).thenReturn(mockPage);
        when(bookMapper.toModel(any())).thenReturn(new BookModel());
        List<BookModel> result = bookService.getAll(searchModel);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}