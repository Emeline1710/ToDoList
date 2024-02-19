package com.example.todolist.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todolist.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CreateNote extends AppCompatActivity {
    private TextView dateTextView;
    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        //Affiche la date du jour en haut de la note

        dateTextView = findViewById(R.id.dateTextView);
        noteEditText = findViewById(R.id.noteEditText);

        SimpleDateFormat dateNow = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        dateNow.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        String currentDate = dateNow.format(new Date());
        dateTextView.setText(currentDate);
    }

    //Affiche le menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}