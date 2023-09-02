package com.app.library.business.person.impl;

import com.app.library.business.book.impl.BookEntity;
import com.app.library.common.layer.IEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;

@Entity
public class PersonEntity implements IEntity {
    public static final String BOOKS_MAPPED_BY_NAME = "books";
    public static final String PERSON_BOOK_ENTITY_NAME = "person_book_entity";
    public static final String PERSON_BOOK_ENTITY_BOOK_ID_COLUMN = "book_id";
    public static final String PERSON_BOOK_ENTITY_PERSON_ID_COLUMN = "person_id";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String phone;
    @Column
    private String name;
    @Column
    private String family;
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinTable(name = PERSON_BOOK_ENTITY_NAME,
            joinColumns = @JoinColumn(name = PERSON_BOOK_ENTITY_BOOK_ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = PERSON_BOOK_ENTITY_PERSON_ID_COLUMN))
    private Set<BookEntity> books;

    public PersonEntity() {

    }

    public PersonEntity(Long id, String username, String phone, String name, String family, Set<BookEntity> books) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.name = name;
        this.family = family;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}
