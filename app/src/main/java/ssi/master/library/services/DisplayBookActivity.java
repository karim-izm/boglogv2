package ssi.master.library.services;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import ssi.master.library.MainActivity;
import ssi.master.library.R;
import ssi.master.library.models.Book;
import ssi.master.library.repositories.BookDAO;

public class DisplayBookActivity extends AppCompatActivity {

    private TextView tvIsbnDisplay, tvTitleDisplay, tvAuthorDisplay;
    private Button btnDelete, btnEdit;
    private BookDAO bookDAO;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_book);

        tvIsbnDisplay = findViewById(R.id.tv_isbn_display);
        tvTitleDisplay = findViewById(R.id.tv_title_display);
        tvAuthorDisplay = findViewById(R.id.tv_author_display);
        btnDelete = findViewById(R.id.btn_delete);
        btnEdit = findViewById(R.id.btn_edit);

        bookDAO = new BookDAO(this);
        bookDAO.open();

        bookId = getIntent().getIntExtra("bookId", -1);
        System.out.println(bookId);
        if (bookId != -1) {
            displayBookDetails(bookId);
        }

        btnDelete.setOnClickListener(v -> deleteBook());
        btnEdit.setOnClickListener(v -> editBook());
    }

    private void displayBookDetails(int bookId) {
        Book book = bookDAO.getBookById(bookId);
        if (book != null) {
            tvIsbnDisplay.setText("ISBN: " + book.getIsbn());
            tvTitleDisplay.setText("Title: " + book.getTitle());
            tvAuthorDisplay.setText("Author: " + book.getAuthor());
        }
    }

    private void deleteBook() {
        try {
            bookDAO.deleteBook(bookId);
            Toast.makeText(this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DisplayBookActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to delete book", Toast.LENGTH_SHORT).show();
        }
    }

    private void editBook() {
        Intent intent = new Intent(DisplayBookActivity.this, EditBookActivity.class);
        intent.putExtra("bookId", bookId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookDAO.close();
    }
}
