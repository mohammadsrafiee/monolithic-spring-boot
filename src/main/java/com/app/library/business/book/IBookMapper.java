package com.app.library.business.book;

import com.app.library.business.book.impl.BookEntity;
import com.app.library.business.book.impl.BookModel;
import com.app.library.common.layer.IMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface IBookMapper extends IMapper<BookModel, BookEntity> {
}
