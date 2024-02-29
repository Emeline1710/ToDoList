package com.example.todolist.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todolist.R;

// Cette classe est l'activité principale de l'application.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recherche des boutons et ajout de leur écouteur d'événements
        Button buttonCreateNote = findViewById(R.id.buttonCreateNote);
        buttonCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige vers l'activité de création de notes
                Intent intent = new Intent(MainActivity.this, CreateNote.class);
                startActivity(intent);
            }
        });

        Button buttonViewNotes = findViewById(R.id.buttonViewNotes);
        buttonViewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige vers l'activité d'affichage des notes
                Intent intent = new Intent(MainActivity.this, ViewNotes.class);
                startActivity(intent);
            }
        });
    }
}
