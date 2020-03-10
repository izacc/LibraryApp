package com.example.libraryapplicationproject.DeliciousBeans;

public class BookData {
    public String bookName;
    public String bookAuthor;
    public String bookDescription;
    public String bookURL;
    public int bookRating;
    public int bookID;
    public int bookImage;
    public String bookCat;

    //this is a changed method to test my functionality
    public BookData(String bookName, String bookAuthor, String bookDescription, int bookImage, int bookRating) {
        this.bookImage = bookImage;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookRating = bookRating;

    }

    public BookData(String bookName, String bookAuthor, String bookCat){
        this.bookAuthor = bookAuthor;
        this.bookName = bookName;
        this.bookCat = bookCat;
    }
    /**
     * @author yonis sheekh
     * @since 2020-03-2
     * @param bookName the name of the book
     * @param bookAuthor the author of the book
     * @param bookID the IDof the book
     */


    public BookData(String bookName, String bookAuthor, String bookDescription, int bookRating, String bookURL) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookRating = bookRating;
        this.bookURL = bookURL;
    }

    public BookData( int bookID, String bookName, String bookAuthor, String bookDescription, int bookRating, String bookURL) {
        this.bookName = bookName;
        this.bookID = bookID;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookRating = bookRating;
        this.bookURL = bookURL;
    }

    public BookData(String bookName, String bookAuthor, String publisher2, int bookImage) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDescription = publisher2;
        this.bookImage = bookImage;
    }


    public int getBookRating() {return bookRating;}

    public void setBookRating(int bookRating) {this.bookRating = bookRating;}

    public int getBookID() {return bookID;}

    public void setBookID(int bookID) {this.bookID = bookID;}

    public String getBookDescription() {return bookDescription;}

    public void setBookDescription(String bookDescription) {this.bookDescription = bookDescription;}

    public String getBookURL() {return bookURL;}

    public void setBookURL(String bookURL) {this.bookURL = bookURL;}

    public String getBookName() {
        return bookName;
    }

    public String getBookCat() {
        return bookCat;
    }

    public void setBookCat(String bookCat) {
        this.bookCat = bookCat;
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
