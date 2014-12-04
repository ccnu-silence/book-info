package com.pera.service;

import com.pera.dao.BookDao;
import com.pera.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by phnix on 12/4/2014.
 */
@Service("bookService")
@Transactional
public class BookService {

    @Autowired
    BookDao bookDao;

    public Book save(Book book){
        return bookDao.save(book);
    }

    public Book findByIsbn(String isbn){
        return bookDao.findByIsbn(isbn);
    }

    public List<Book> findAll(){
        return bookDao.findBooks();
    }
}
