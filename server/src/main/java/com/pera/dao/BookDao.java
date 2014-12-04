package com.pera.dao;

import com.pera.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by phnix on 12/4/2014.
 */
public interface BookDao extends Repository<Book, Integer> {
    Book save(Book entity);
    Book findByIsbn(String isbn);

    @Query("select book from Book book where book.isRecord <> false")
    List<Book> findBooks();
}
