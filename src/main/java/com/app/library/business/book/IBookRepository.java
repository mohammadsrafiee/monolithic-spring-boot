package com.app.library.business.book;

import com.app.library.business.book.impl.BookEntity;
import com.app.library.common.layer.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long>, IRepository {
}
