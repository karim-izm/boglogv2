package ssi.master.library.services;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import ssi.master.library.R;
import ssi.master.library.repositories.BookDAO;

public class DeleteBookActivity extends AppCompatActivity {

    private EditText etIsbnDelete;
    private Button btnDelete;
    private BookDAO bookDAO;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_book);

        etIsbnDelete = findViewById(R.id.edittext_isbn_delete);
        btnDelete = findViewById(R.id.btn_delete);

        bookDAO = new BookDAO(this);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isbn = etIsbnDelete.getText().toString().trim();
                if (!isbn.isEmpty()) {
                    try {
                        bookDAO.open();
                       // bookDAO.deleteBook(isbn);
                        Toast.makeText(DeleteBookActivity.this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
                        etIsbnDelete.setText("");
                        bookDAO.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(DeleteBookActivity.this, "Failed to delete book", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DeleteBookActivity.this, "Please enter ISBN", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
