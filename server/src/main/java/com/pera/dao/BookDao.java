package com.pera.dao;

import com.pera.entity.Book;
import org.springframework.data.repository.Repository;

/**
 * Created by phnix on 12/4/2014.
 */
public interface BookDao extends Repository<Book, Integer> {
    Book save(Book entity);
    Book findByIsbn(String isbn);
}
