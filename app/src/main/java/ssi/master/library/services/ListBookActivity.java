package ssi.master.library.services;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ssi.master.library.R;
import ssi.master.library.adapters.BookAdapter;
import ssi.master.library.models.Book;
import ssi.master.library.repositories.BookDAO;

public class ListBookActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBooks;
    private BookDAO bookDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_book);

        recyclerViewBooks = findViewById(R.id.recycler_view_books);
        recyclerViewBooks.setLayoutManager(new LinearLayoutManager(this));

        bookDAO = new BookDAO(this);
        bookDAO.open();

        List<Book> books = bookDAO.getAllBooks();
        BookAdapter bookAdapter = new BookAdapter(this, books);
        recyclerViewBooks.setAdapter(bookAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bookDAO != null) {
            bookDAO.close();
        }
    }
}
