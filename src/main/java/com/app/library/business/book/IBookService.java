package com.app.library.business.book;

import com.app.library.business.book.impl.BookEntity;
import com.app.library.business.book.impl.BookModel;
import com.app.library.common.layer.IService;
import com.app.library.common.search.SearchModel;

import java.util.List;

public interface IBookService extends IService {

    BookModel saveOrUpdate(BookModel book);

    BookModel getById(Long id);

    List<BookModel> getAll(SearchModel<BookEntity> search);

    void delete(Long id);
}
