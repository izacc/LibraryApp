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
    public static final String TABLE_IMAGE = "image";


  /*
    Foreign keys
   */

    public static final String BOOK_ID = "id";
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

    /*
     * Locker Table
     */
    public static final String BOOK_ID_FOREIGN = "book_id";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_READ = "is_read";


    public static final String CREATE_BOOK_TABLE = "CREATE TABLE " +
            TABLE_BOOK + " (" + BOOK_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," + COLUMN_AUTHOR + " TEXT," + COLUMN_DESCRIPTION
            + " TEXT," + COLUMN_WEBSITE + " TEXT)";

    public static final String CREATE_LOCKER_TABLE = "CREATE TABLE " +
            TABLE_LOCKER + " ("  + COLUMN_RATING + " INTEGER," +  COLUMN_READ + " INTEGER," +
            " FOREIGN KEY" + "(" + BOOK_ID + ") REFERENCES " + TABLE_BOOK + "(" + "BOOK_ID" + ") )";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_LOCKER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
