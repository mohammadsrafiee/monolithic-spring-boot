package com.app.library.business.person.impl;

import com.app.library.business.book.impl.BookModel;
import com.app.library.common.layer.IModel;

import java.util.Set;

public class PersonModel implements IModel {
    private Long id;
    private String name;
    private String writer;
    private String family;
    private Set<BookModel> books;

    public PersonModel() {

    }

    public PersonModel(Long id, String name, String writer, String family, Set<BookModel> books) {
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.family = family;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Set<BookModel> getBooks() {
        return books;
    }

    public void setBooks(Set<BookModel> books) {
        this.books = books;
    }
}
