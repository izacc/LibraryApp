package com.example.libraryapplicationproject;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.libraryapplicationproject.DeliciousBeans.BookData;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    /*
     * Keep track of the database version
     */

    public static final int DATABASE_VERSION = 1;

    /*
     * Name the database
     */

    public static final String DATABASE_NAME = "booklocker";


    /*
     * Names of tables
     */

    public static final String TABLE_BOOK = "book";
    public static final String TABLE_LOCKER = "locker";
    public static final String TABLE_FAVORITES = "favorites";



  /*
    Foreign keys
   */

    public static final String BOOK_ID = "book_id";
    public static final String IMAGE_ID = "image_id";

    /*
     * Book table
     */

    public static final String COLUMN_NAME = "bookname";
    public static final String COLUMN_AUTHOR = "bookauthor";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_PUBLISHER = "publisher";
    public static final String COLUMN_PUBLISHER_DATE = "publisher_date";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_WEBSITE= "website";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_RATING = "rating";
    /*
        Image table
     */





    public static final String CREATE_BOOK_TABLE = "CREATE TABLE " +
            TABLE_BOOK + "( " + BOOK_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," + COLUMN_AUTHOR + " TEXT," + COLUMN_CATEGORY + " TEXT," + COLUMN_PUBLISHER
            + " TEXT," + COLUMN_PUBLISHER_DATE + " TEXT," + COLUMN_IMAGE + " TEXT," + COLUMN_DESCRIPTION
            + " TEXT," + COLUMN_RATING  + " TEXT," + COLUMN_WEBSITE + " TEXT);";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
           CREATE STATEMENTS
     */
    public void addBook(BookData book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, book.getBookName());
        values.put(COLUMN_AUTHOR, book.getBookAuthor());
        values.put(COLUMN_CATEGORY, book.getBookCat());
        values.put(COLUMN_PUBLISHER, book.getBookPublisher());
        values.put(COLUMN_PUBLISHER_DATE, book.getPublishedDate());
        values.put(COLUMN_IMAGE, book.getBookImage());
        values.put(COLUMN_DESCRIPTION, book.getBookDescription());
        values.put(COLUMN_RATING, book.getBookRating());
        values.put(COLUMN_WEBSITE, book.getBookURL());
        db.insert(TABLE_BOOK, null, values);
        db.close();
    }

    /*
         BOOK TABLE READ STATEMENTS
     */

    public BookData getBook(int id){
        SQLiteDatabase db  = this.getReadableDatabase();
        BookData book = null;
        Cursor cursor = db.query(TABLE_BOOK, new String[]{BOOK_ID,
                        COLUMN_NAME, COLUMN_AUTHOR, COLUMN_CATEGORY, COLUMN_PUBLISHER, COLUMN_PUBLISHER_DATE, COLUMN_IMAGE,
                        COLUMN_DESCRIPTION, COLUMN_RATING, COLUMN_WEBSITE}, BOOK_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            book = new BookData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(8),
                    cursor.getInt(7),
                    cursor.getString(9));
        }
        db.close();
        return book;
    }

    public ArrayList<BookData> getAllBooks(){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOK ,
                null);
        ArrayList<BookData> books = new ArrayList<>();
        while(cursor.moveToNext()) {
            books.add(new BookData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getInt(8),
                    cursor.getString(9)));


        }
        db.close();

        return books;
    }



    public void deleteBook(Integer book){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, BOOK_ID + " = ?",
                new String[]{String.valueOf(book)});
        db.close();
    }
    public void updateRating(BookData book){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RATING, book.getBookRating());
        db.update(TABLE_BOOK, values, BOOK_ID + "=?",
                new String[]{String.valueOf(book.getBookID())});
    }


}
