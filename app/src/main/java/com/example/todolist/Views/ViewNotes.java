package com.example.todolist.Views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.todolist.Models.Note;
import com.example.todolist.Managers.NoteHandler;
import com.example.todolist.R;
import java.util.List;

// Cette classe est l'activité d'affichage des notes.
public class ViewNotes extends AppCompatActivity {

    private NoteHandler noteHandler;
    private Spinner spinnerSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        noteHandler = new NoteHandler(this);

        // Initialise le spinner de tri
        spinnerSort = findViewById(R.id.spinnerSort);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(adapter);

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Trier par Date")) {
                    List<Note> sortedNotes = noteHandler.getNotesSortedByDate();
                    updateNoteList(sortedNotes);
                } else if (selectedItem.equals("Trier par Titre")) {
                    List<Note> sortedNotes = noteHandler.getNotesSortedByTitle();
                    updateNoteList(sortedNotes);
                } else if (selectedItem.equals("Trier par ID")) {
                    List<Note> sortedNotes = noteHandler.getNotesSortedById();
                    updateNoteList(sortedNotes);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Initialise le bouton d'ajout de note
        ImageView imageViewAddNote = findViewById(R.id.imageViewAddNote);
        imageViewAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewNotes.this, CreateNote.class);
                startActivity(intent);
            }
        });

        // Remplace le fragment container avec le fragment de la liste des notes
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new NoteListFragment())
                .commit();
    }

    // Met à jour la liste des notes dans le fragment
    private void updateNoteList(List<Note> sortedNotes) {
        NoteListFragment fragment = (NoteListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragment.updateNotesList(sortedNotes);
        }
    }
}
