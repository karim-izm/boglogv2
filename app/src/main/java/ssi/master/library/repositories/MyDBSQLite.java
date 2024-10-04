package ssi.master.library.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "library.db";
    private static final int DATABASE_VERSION = 3;

    private static final String CREATE_TABLE_USERS = "CREATE TABLE users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL, " +
            "password TEXT NOT NULL)";

    private static final String CREATE_TABLE_BOOKS = "CREATE TABLE books (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "isbn TEXT NOT NULL, " +
            "title TEXT NOT NULL, " +
            "author TEXT NOT NULL, " +
            "category TEXT NOT NULL)";

    public MyDBSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }
}
