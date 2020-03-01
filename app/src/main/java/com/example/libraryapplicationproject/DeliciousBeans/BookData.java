package com.example.libraryapplicationproject.DeliciousBeans;

public class BookData {
    public String bookName;
    public String bookAuthor;
    public String bookPublisher;
    /**
     * @author yonis sheekh
     * @since 2020-03-1
     * @param bookName the name of the book
     * @param bookAuthor the author of the book
     * @param bookPublisher the publisher of the book
     */
    public BookData(String bookName, String bookAuthor, String bookPublisher) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

}
