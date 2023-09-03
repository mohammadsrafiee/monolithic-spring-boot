package com.app.library.business.book.impl;

import com.app.library.business.book.IBookMapper;
import com.app.library.business.book.IBookRepository;
import com.app.library.business.book.IBookService;
import com.app.library.common.log.impl.Log;
import com.app.library.common.search.FilterQuery;
import com.app.library.common.search.SearchModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {

    private final IBookRepository repository;
    private final IBookMapper mapper;

    public BookService(IBookRepository repository, IBookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Log
    @Override
    public BookModel saveOrUpdate(BookModel book) {
        return this.mapper.toModel(
                repository.save(
                        mapper.toEntity(book)));
    }

    @Log
    @Override
    public BookModel getById(Long id) {
        return mapper.toModel(repository.getReferenceById(id));
    }

    @Log
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Log
    @Override
    public List<BookModel> getAll(SearchModel<BookEntity> search) {
        List<BookModel> result = new ArrayList<>();
        Page<BookEntity> booksPage;
        Example<BookEntity> example = FilterQuery.example(search);
        if (example == null) {
            booksPage = repository.findAll(FilterQuery.pageRequest(search));
        } else {
            booksPage = repository.findAll(example, FilterQuery.pageRequest(search));
        }
        List<BookEntity> books = booksPage.getContent();
        books.forEach(e -> result.add(mapper.toModel(e)));
        return result;
    }

}