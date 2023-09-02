package com.app.library.business.person.book;

import com.app.library.business.book.impl.BookModel;
import com.app.library.business.person.impl.PersonModel;
import com.app.library.common.layer.IService;
import com.app.library.common.search.SearchModel;

import java.util.List;

public interface IPersonBookService extends IService {
    PersonModel borrowBook(Long bookId);

    PersonModel releaseBook(Long bookId);

    List<BookModel> myBooks(SearchModel<PersonModel> search);

}
