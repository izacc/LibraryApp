package com.example.libraryapplicationproject;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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
    public static final String TABLE_IMAGE = "image";


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
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_WEBSITE= "website";

    /*
        Image table
     */


    public static final String COLUMN_RESOURCE = "resource";


    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_READ = "is_read";


    public static final String CREATE_BOOK_TABLE = "CREATE TABLE " +
            TABLE_BOOK + "( " + BOOK_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," + COLUMN_AUTHOR + " TEXT," + COLUMN_DESCRIPTION
            + " TEXT," + COLUMN_RATING + " INTEGER," + COLUMN_WEBSITE + " TEXT);";

    public static final String CREATE_LOCKER_TABLE = "CREATE TABLE " +
            TABLE_LOCKER + "( "  + BOOK_ID + " INTEGER," + "FOREIGN KEY" + "(" + BOOK_ID + ") REFERENCES " + TABLE_BOOK + "(" + BOOK_ID + ") );";

    //Favorited books can exist without being added to the locker
    public static final String CREATE_FAVORITES_TABLE = "CREATE TABLE " +
            TABLE_FAVORITES + " ( "  + BOOK_ID + " INTEGER," +  "FOREIGN KEY" + "(" + BOOK_ID + ") REFERENCES " + TABLE_BOOK + "(" + BOOK_ID + ") );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_LOCKER_TABLE);
        db.execSQL(CREATE_FAVORITES_TABLE);
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
        values.put(COLUMN_RATING, book.getBookRating());
        values.put(COLUMN_NAME, book.getBookName());
        values.put(COLUMN_AUTHOR, book.getBookAuthor());
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
                        COLUMN_NAME, COLUMN_AUTHOR, COLUMN_DESCRIPTION, COLUMN_WEBSITE}, BOOK_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            book = new BookData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5));
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
                    cursor.getInt(4),
                    cursor.getString(5)));
        }
        db.close();
        return books;
    }

    /*
    LOCKER TABLE READ STATEMENTS
     */

    public ArrayList<BookData> getAllLockerBooks(){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TABLE_BOOK + ".*" + " FROM " + TABLE_BOOK + " INNER JOIN " + TABLE_FAVORITES + " ON " + TABLE_BOOK + "." + BOOK_ID +
                "=" + TABLE_FAVORITES + "." + BOOK_ID,
                null);
        ArrayList<BookData> books = new ArrayList<>();
        while(cursor.moveToNext()) {
            books.add(new BookData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5)));
        }
        db.close();
        return books;
    }

    //This should work, unable to test
    public BookData getLockerBook(int id){
        SQLiteDatabase db  = this.getReadableDatabase();
        BookData book = null;
        Cursor cursor = db.rawQuery("SELECT " + TABLE_BOOK + ".*" + " FROM " + TABLE_BOOK + " INNER JOIN " + TABLE_FAVORITES + " ON " + TABLE_BOOK + "." + BOOK_ID +
                        "=" + TABLE_FAVORITES + "." + BOOK_ID + " WHERE " + TABLE_FAVORITES + "." + BOOK_ID + " = " + id,
                null);

        if (cursor.moveToFirst()) {
            book = new BookData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5));
        }
        db.close();
        return book;
    }

    /*
        FAVOURITES TABLE READ STATEMENTS
     */

    public ArrayList<BookData> getAllFavorites(){
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TABLE_BOOK + ".*" + " FROM " + TABLE_BOOK + " INNER JOIN " + TABLE_LOCKER + " ON " + TABLE_BOOK + "." + BOOK_ID +
                        "=" + TABLE_LOCKER + "." + BOOK_ID,
                null);
        ArrayList<BookData> books = new ArrayList<>();
        while(cursor.moveToNext()) {
            books.add(new BookData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5)));
        }
        db.close();
        return books;
    }

    //This should work, unable to test
    public BookData getFavoriteBook(int id){
        SQLiteDatabase db  = this.getReadableDatabase();
        BookData book = null;
        Cursor cursor = db.rawQuery("SELECT " + TABLE_BOOK + ".*" + " FROM " + TABLE_BOOK + " INNER JOIN " + TABLE_LOCKER + " ON " + TABLE_BOOK + "." + BOOK_ID +
                        "=" + TABLE_LOCKER + "." + BOOK_ID + " WHERE " + TABLE_LOCKER + "." + BOOK_ID + " = " + id,
                null);

        if (cursor.moveToFirst()) {
            book = new BookData(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5));
        }
        db.close();
        return book;
    }

    /*
        Delete Statements
     */
    public void deleteLockerItem(Integer book){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCKER, BOOK_ID + " = ?",
                new String[]{String.valueOf(book)});
        db.close();
    }


}
