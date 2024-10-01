package ssi.master.library.services;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ssi.master.library.R;
import ssi.master.library.models.Book;
import ssi.master.library.repositories.BookDAO;

public class AddBookActivity extends AppCompatActivity {

    private EditText etIsbn, etTitle;
    private Button btnAdd;
    private BookDAO bookDAO;
    private String author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etIsbn = findViewById(R.id.edittext_isbn);
        etTitle = findViewById(R.id.edittext_title);
        btnAdd = findViewById(R.id.btn_add);

        bookDAO = new BookDAO(this);
        bookDAO.open();

        // Retrieve the author from the intent
        author = getIntent().getStringExtra("username");

        btnAdd.setOnClickListener(v -> {
            String isbn = etIsbn.getText().toString().trim();
            String title = etTitle.getText().toString().trim();

            if (!isbn.isEmpty() && !title.isEmpty()) {
                Book book = new Book(isbn, title, author);
                long id = bookDAO.addBook(book);

                if (id != -1) {
                    Toast.makeText(AddBookActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();
                    etIsbn.setText("");
                    etTitle.setText("");
                } else {
                    Toast.makeText(AddBookActivity.this, "Failed to add book", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(AddBookActivity.this, "Please enter ISBN and title", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bookDAO != null) {
            bookDAO.close();
        }
    }
}
