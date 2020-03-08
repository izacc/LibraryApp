package com.example.libraryapplicationproject.DeliciousBeans;

public class BookData {
    public String bookName;
    public String bookAuthor;
    public String bookDescription;
    public String bookURL;
    public int bookID;
    public int bookImage;

    /**
     * @author yonis sheekh
     * @since 2020-03-2
     * @param bookName the name of the book
     * @param bookAuthor the author of the book
     * @param bookID the IDof the book
     */

    public BookData( int bookID, String bookName, String bookAuthor, String bookDescription, String bookURL) {
        this.bookName = bookName;
        this.bookID = bookID;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookURL = bookURL;
    }

    //constructor for testing purposes
    public BookData(String bookName, String bookAuthor, int bookImage) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
    }


    public int getBookID() {return bookID;}

    public void setBookID(int bookID) {this.bookID = bookID;}

    public String getBookDescription() {return bookDescription;}

    public void setBookDescription(String bookDescription) {this.bookDescription = bookDescription;}

    public String getBookURL() {return bookURL;}

    public void setBookURL(String bookURL) {this.bookURL = bookURL;}

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


    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
    }

}
