package com.example.libraryapplicationproject.DeliciousBeans;

import android.os.Parcel;
import android.os.Parcelable;

public class BookData implements Parcelable {
    //all properties
    public String bookName;
    public String bookAuthor;
    public String bookDescription;
    public String bookURL;
    public int bookRating;
    public int bookID;
    public int bookImage;
    public String imageBook;
    public String bookCat;
    public String bookPublisher;
    public String publishedDate;


    //this is a changed method to test my functionality
    public BookData(String bookName, String bookAuthor, String bookDescription, int bookImage, int bookRating) {
        this.bookImage = bookImage;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookRating = bookRating;

    }



    public BookData(String bookName, String bookAuthor, String bookCat,String bookPublisher,String publishedDate, String imageBook, String bookDescription, int bookRating){
        this.bookAuthor = bookAuthor;
        this.bookName = bookName;
        this.bookCat = bookCat;
        this.bookPublisher = bookPublisher;
        this.publishedDate = publishedDate;
        this.imageBook = imageBook;
        this.bookDescription = bookDescription;
        this.bookRating = bookRating;
    }

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
    public String getBookPublisher() {
        return bookPublisher;
    }
    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
    public String getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
    public String getImageBook() {
        return imageBook;
    }
    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }


    protected BookData(Parcel in) {
        bookName = in.readString();
        bookAuthor = in.readString();
        bookDescription = in.readString();
        bookURL = in.readString();
        bookRating = in.readInt();
        bookID = in.readInt();
        bookImage = in.readInt();
        imageBook = in.readString();
        bookCat = in.readString();
        bookPublisher = in.readString();
        publishedDate = in.readString();
    }

    public static final Creator<BookData> CREATOR = new Creator<BookData>() {
        @Override
        public BookData createFromParcel(Parcel in) {
            return new BookData(in);
        }

        @Override
        public BookData[] newArray(int size) {
            return new BookData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookName);
        parcel.writeString(bookAuthor);
        parcel.writeString(bookDescription);
        parcel.writeString(bookURL);
        parcel.writeInt(bookRating);
        parcel.writeInt(bookID);
        parcel.writeInt(bookImage);
        parcel.writeString(imageBook);
        parcel.writeString(bookCat);
        parcel.writeString(bookPublisher);
        parcel.writeString(publishedDate);
    }
}
