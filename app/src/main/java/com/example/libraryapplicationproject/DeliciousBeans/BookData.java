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
    public String bookImage;
    public String bookCat;
    public String bookPublisher;
    public String publishedDate;
    public String preview;


//keep this importnt for search gives me everything i need so far
    public BookData(String bookName, String bookAuthor, String bookCat, String bookPublisher, String publishedDate, String bookImage, String bookDescription, int bookRating, String bookURL,String preview){
        this.bookAuthor = bookAuthor;
        this.bookName = bookName;
        this.bookCat = bookCat;
        this.bookPublisher = bookPublisher;
        this.publishedDate = publishedDate;
        this.bookImage = bookImage;
        this.bookDescription = bookDescription;
        this.bookRating = bookRating;
        this.bookURL = bookURL;
        this.preview = preview;
    }

    public BookData(String bookName, String bookAuthor, String bookDescription, int bookRating, String bookImage, String bookURL) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookRating = bookRating;
        this.bookImage = bookImage;
        this.bookURL = bookURL;
    }

    public BookData(int bookID, String bookName, String bookAuthor, String bookDescription, int bookRating, String bookImage, String bookURL) {
        this.bookName = bookName;
        this.bookID = bookID;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookRating = bookRating;
        this.bookURL = bookURL;
        this.bookImage = bookImage;
    }

    public String getPreview() {
        return preview;
    }
    public void setPreview(String preview) {
        this.preview = preview;
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
    public String getBookImage() {
        return bookImage;
    }
    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }


    protected BookData(Parcel in) {
        bookName = in.readString();
        bookAuthor = in.readString();
        bookDescription = in.readString();
        bookURL = in.readString();
        bookRating = in.readInt();
        bookID = in.readInt();
        bookImage = in.readString();
        bookCat = in.readString();
        bookPublisher = in.readString();
        publishedDate = in.readString();
        preview = in.readString();
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
        parcel.writeString(bookImage);
        parcel.writeString(bookCat);
        parcel.writeString(bookPublisher);
        parcel.writeString(publishedDate);
        parcel.writeString(preview);
    }
}
