package com.example.todolist.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Managers.NoteHandler;
import com.example.todolist.Models.Note;
import com.example.todolist.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// Cette classe est faite pour créer des notes.
public class CreateNote extends AppCompatActivity {
    private TextView dateTextView;
    private EditText noteEditText;
    private EditText titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        dateTextView = findViewById(R.id.dateTextView);
        noteEditText = findViewById(R.id.noteEditText);
        titre = findViewById(R.id.titre);

        // Affiche la date du jour en haut de la note
        SimpleDateFormat dateNow = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        dateNow.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        String currentDate = dateNow.format(new Date());
        dateTextView.setText(currentDate);
    }

    // Affiche le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String title = titre.getText().toString();
        String text = noteEditText.getText().toString();
        String date = dateTextView.getText().toString();

        Note note = new Note();
        note.setTitle(title);
        note.setText(text);
        note.setDate(date);

        NoteHandler noteHandler = new NoteHandler(this);

        if (id == R.id.save) {
            // Ajoute la note à la base de données
            int insertedId = noteHandler.addNote(note);

            if (insertedId != -1) {
                Toast.makeText(this, "Note sauvegardée", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erreur lors de la sauvegarde de la note", Toast.LENGTH_LONG).show();
            }

            // Redirige vers l'activité ViewNotes
            Intent intent1 = new Intent(this, ViewNotes.class);
            this.startActivity(intent1);
            return true;
        }

        if (id == R.id.create_note || id == R.id.view_notes) {
            // Demande confirmation avant de quitter l'activité sans sauvegarder la note
            new AlertDialog.Builder(this)
                    .setMessage("Voulez-vous quitter sans sauvegarder la note ?")
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (id == R.id.create_note) {
                                Intent intent = new Intent(CreateNote.this, CreateNote.class);
                                startActivity(intent);
                            } else if (id == R.id.view_notes) {
                                Intent intent = new Intent(CreateNote.this, ViewNotes.class);
                                startActivity(intent);
                            }
                        }
                    })
                    .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
