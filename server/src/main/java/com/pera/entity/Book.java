package com.pera.entity;

import javax.persistence.*;

/**
 * Created by phnix on 2014/12/1.
 */
@Entity
@Table(name = "book")
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private String image;
    private String press;
    private String pages;
    private boolean isRecord;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", press='" + press + '\'' +
                ", isbn='" + isbn + '\'' +
                ", pages='" + pages + '\'' +
                '}';
    }


    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(columnDefinition = "boolean default false")
    public boolean isRecord() {
        return isRecord;
    }

    public void setRecord(boolean isRecord) {
        this.isRecord = isRecord;
    }
}
