package com.example.todolist.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.todolist.R;

public class ViewNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);


        ImageView imageViewAddNote = findViewById(R.id.imageViewAddNote);

        imageViewAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewNotes.this, CreateNote.class);
                startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new NoteListFragment())
                .commit();
    }
}
