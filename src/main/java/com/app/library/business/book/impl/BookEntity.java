package com.app.library.business.book.impl;

import com.app.library.business.person.impl.PersonEntity;
import com.app.library.common.layer.IEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class BookEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String isbn;
    @Column
    private String name;
    @Column
    private String writer;
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer count;
    @ManyToMany(mappedBy = PersonEntity.BOOKS_MAPPED_BY_NAME, fetch = FetchType.LAZY)
    private Set<PersonEntity> readers;

    public BookEntity() {

    }

    public BookEntity(Long id, String isbn, String name, String writer, Integer count, Set<PersonEntity> readers) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.writer = writer;
        this.count = count;
        this.readers = readers;
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

    public Set<PersonEntity> getReaders() {
        return readers;
    }

    public void setReaders(Set<PersonEntity> readers) {
        this.readers = readers;
    }
}
