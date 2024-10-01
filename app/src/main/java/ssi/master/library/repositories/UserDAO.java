package ssi.master.library.repositories;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ssi.master.library.models.User;

public class UserDAO {
    private MyDBSQLite myDBSQLite;
    private SQLiteDatabase db;

    public UserDAO(Context context) {
        myDBSQLite = new MyDBSQLite(context);
    }

    public void open() {
        db = myDBSQLite.getWritableDatabase();
    }

    public void close() {
        myDBSQLite.close();
    }

    public long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        return db.insert("users", null, values);
    }

    public User getUser(String username, String password) {
        Cursor cursor = db.query("users", null, "username = ? AND password = ?", new String[]{username, password}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            cursor.close();
            return new User(id, username, password);
        }
        return null;
    }
}
