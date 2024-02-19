package com.example.todolist.Views;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.todolist.Managers.NoteHandler;
import com.example.todolist.Models.Note;
import com.example.todolist.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class OnLongClickListenerUpdateNote implements View.OnLongClickListener{
    private Context context;
    private String id;
    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();
        final CharSequence[] items = {"Modifier","Supprimer"};
        new AlertDialog.Builder(context)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editNote(Integer.parseInt(id),view);
                        }
                        else if (item == 1) {
                            Note note = new Note();
                            note.setId(Integer.parseInt(id));
                            new NoteHandler(context).deleteNote(note);
                            Toast.makeText(context, "Note supprimée avec succès", Toast.LENGTH_SHORT).show();
                            readNotes(context,view);
                        }

                        dialog.dismiss();
                    }
                }).show();
        return false;
    }

    public void editNote(final int id, final View view) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.note_input_form, null, false);
        final EditText editTextNoteTitle = formElementsView.findViewById(R.id.editTextNoteTitle);
        final EditText editTextNoteText = formElementsView.findViewById(R.id.editTextNoteText);

        final NoteHandler noteHandler = new NoteHandler(context);
        final Note note = noteHandler.getNote(id);

        editTextNoteTitle.setText(note.getTitle());
        editTextNoteText.setText(note.getText());

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setPositiveButton("Sauvegarder", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        note.setTitle(editTextNoteTitle.getText().toString());
                        note.setText(editTextNoteText.getText().toString());

                        SimpleDateFormat dateNow = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                        dateNow.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                        String currentDate = dateNow.format(new Date());
                        note.setDate(currentDate);

                        int updatedRows = noteHandler.updateNote(note);
                        if (updatedRows > 0) {
                            Toast.makeText(context, "La note a été mise à jour.", Toast.LENGTH_SHORT).show();
                            readNotes(context, view);
                        } else {
                            Toast.makeText(context, "La mise à jour de la note a échoué.", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                }).show();
    }


    public void readNotes(Context context,View view) {
        LinearLayout linearLayoutRecords = (LinearLayout)  
                view.getRootView().findViewById(R.id.linearLayoutNotes);
        linearLayoutRecords.removeAllViews();
        List<Note> notes = new NoteHandler(context).getAllNotes();
        if (notes.size() > 0) {
            for (Note obj : notes) {
                int id = obj.getId();
                String title = obj.getTitle();
                String text = obj.getText();
                String date = obj.getDate();
                String textViewContents = String.valueOf(id)
                        + "-" + title + " - "
                        + text + " - " + date;
                TextView textViewNoteItem= new TextView(context);
                textViewNoteItem.setPadding(0, 10, 0, 10);
                textViewNoteItem.setText(textViewContents);
                textViewNoteItem.setTag(Integer.toString(id));
                textViewNoteItem.setOnLongClickListener(new OnLongClickListenerUpdateNote());
                linearLayoutRecords.addView(textViewNoteItem);
            }
        }
        else {
            TextView locationItem = new TextView(context);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Aucun enregistrement pour le moment.");
            linearLayoutRecords.addView(locationItem);
        }
    }

}