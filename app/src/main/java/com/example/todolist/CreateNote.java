package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private TextView dateTextView;
    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        dateTextView = findViewById(R.id.dateTextView);
        noteEditText = findViewById(R.id.noteEditText);

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = date.format(new Date());
        dateTextView.setText(String.format("Date: %s", currentDate));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}