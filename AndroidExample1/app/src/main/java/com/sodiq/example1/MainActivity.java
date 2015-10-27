package com.sodiq.example1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.orm.SugarRecord;
import com.sodiq.example1.models.Book;

import java.util.List;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {
    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        deleteAllRecord();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book("My Book", "1nd edition");
                book.save();

                book.setTitle("My Book Update");
                book.save();

                Book book2 = new Book("My Book 2", "2nd edition");
                book2.save();

                showBooks();
            }
        });
    }

    @DebugLog
    private void showBooks() {
        List<Book> books = getAllRecord();
        for (int i = 0; i < books.size(); i++) {
            getRecordById(books.get(i).getId());

            //just test the log
            Log.d("data" + i, getRecordById(books.get(i).getId()) + "\n" + books.get(i).toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Book> getAllRecord() {
        return SugarRecord.listAll(Book.class);
    }

    private void deleteAllRecord() {
        SugarRecord.deleteAll(Book.class);
    }

    private Book getRecordById(long id) {
        return SugarRecord.findById(Book.class, id);
    }
}
