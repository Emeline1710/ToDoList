package com.example.todolist.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.todolist.Managers.NoteHandler;
import com.example.todolist.Models.Note;
import com.example.todolist.R;

import java.util.List;

public class ViewNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        readNotes();
    }

    public void readNotes() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutNotes);
        linearLayoutRecords.removeAllViews();
        List<Note> notes = new NoteHandler(this).getAllNotes();
        if (notes.size() > 0) {
            for (Note obj : notes) {
                int id = obj.getId();
                String title = obj.getTitle();
                String text = obj.getText();
                String date = obj.getDate();
                String textViewContents = String.valueOf(id)
                        + "-" + title + " - "
                        + text + " - " + date;
                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerUpdateNote());
                linearLayoutRecords.addView(textViewStudentItem);
            }
        }
        else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Aucun enregistrement pour le moment.");
            linearLayoutRecords.addView(locationItem);
        }
    }
}