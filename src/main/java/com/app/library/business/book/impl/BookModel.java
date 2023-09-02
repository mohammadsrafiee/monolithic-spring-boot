package com.app.library.business.book.impl;

import com.app.library.common.layer.IModel;
import jakarta.validation.constraints.NotBlank;

public class BookModel implements IModel {
    private Long id;
    @NotBlank(message = "isbn is required")
    private String isbn;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "writer is required")
    private String writer;
    private Integer count;

    public BookModel() {

    }

    public BookModel(Long id, String isbn, String name, String writer, Integer count) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.writer = writer;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
