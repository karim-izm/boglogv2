package ssi.master.library.services;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ssi.master.library.R;
import ssi.master.library.models.Book;
import ssi.master.library.repositories.BookDAO;

public class EditBookActivity extends AppCompatActivity {

    private EditText etIsbn, etTitle;
    private Button btnSave;
    private BookDAO bookDAO;
    private int bookId; // ID of the book to edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        etIsbn = findViewById(R.id.edittext_isbn);
        etTitle = findViewById(R.id.edittext_title);
        btnSave = findViewById(R.id.btn_save);

        bookDAO = new BookDAO(this);
        bookDAO.open();

        // Retrieve the book details from the intent
        bookId = getIntent().getIntExtra("bookId", -1);
        if (bookId == -1) {
            Toast.makeText(this, "Invalid book ID", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if ID is invalid
            return;
        }

        // Load book details
        loadBookDetails(bookId);

        btnSave.setOnClickListener(v -> {
            String isbn = etIsbn.getText().toString().trim();
            String title = etTitle.getText().toString().trim();

            if (!isbn.isEmpty() && !title.isEmpty()) {
                Book book = new Book(isbn, title, getIntent().getStringExtra("username"));
                book.setId(bookId); // Set the ID for the book being edited
                boolean success = bookDAO.updateBook(book);

                if (success) {
                    Toast.makeText(EditBookActivity.this, "Book updated successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                } else {
                    Toast.makeText(EditBookActivity.this, "Failed to update book", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(EditBookActivity.this, "Please enter ISBN and title", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadBookDetails(int bookId) {
        Book book = bookDAO.getBookById(bookId); // Method to retrieve book details
        if (book != null) {
            etIsbn.setText(book.getIsbn());
            etTitle.setText(book.getTitle());
        } else {
            Toast.makeText(this, "Book not found", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if book not found
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bookDAO != null) {
            bookDAO.close();
        }
    }
}
