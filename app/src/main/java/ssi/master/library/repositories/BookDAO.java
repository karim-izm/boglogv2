package ssi.master.library.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import ssi.master.library.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private MyDBSQLite myDBSQLite;
    private SQLiteDatabase db;

    public BookDAO(Context context) {
        myDBSQLite = new MyDBSQLite(context);
    }

    public void open() {
        db = myDBSQLite.getWritableDatabase();
    }

    public void close() {
        myDBSQLite.close();
    }

    public long addBook(Book book) {
        ContentValues values = new ContentValues();
        values.put("isbn", book.getIsbn());
        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        return db.insert("books", null, values);
    }

    public boolean updateBook(Book book) {
        ContentValues values = new ContentValues();
        values.put("isbn", book.getIsbn());
        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());

        // Log for debugging
        Log.d("BookDAO", "Updating book with ID: " + book.getId());

        // Updating row
        int rowsAffected = db.update("books", values, "id = ?", new String[]{String.valueOf(book.getId())});
        Log.d("BookDAO", "Rows affected: " + rowsAffected);

        return rowsAffected > 0; // Return true if update was successful
    }


    public void deleteBook(int bookId) {
        db.delete("books", "id = ?", new String[]{String.valueOf(bookId)});
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Cursor cursor = db.query("books", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String isbn = cursor.getString(cursor.getColumnIndex("isbn"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));

                Book book = new Book(id, isbn, title, author);
                books.add(book);
            }
            cursor.close();
        }
        return books;
    }

    public Book getBook(String isbn) {
        Cursor cursor = db.query("books", null, "isbn = ?", new String[]{isbn}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
            cursor.close();
            return new Book(id, isbn, title, author);
        }
        return null;
    }

    public Book getBookById(int id) {
        Cursor cursor = db.query("books", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            @SuppressLint("Range") String isbn = cursor.getString(cursor.getColumnIndex("isbn"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
            cursor.close();
            return new Book(id, isbn, title, author);
        }
        return null;
    }
}
