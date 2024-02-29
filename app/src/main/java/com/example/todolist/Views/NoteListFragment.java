package com.example.todolist.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todolist.Managers.NoteHandler;
import com.example.todolist.Models.Note;
import com.example.todolist.R;

import java.util.List;

// Ce fragment affiche la liste de notes.
public class NoteListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        displayNotes(view);
        return view;
    }

    // Affiche les notes dans la vue.
    private void displayNotes(View view) {
        LinearLayout linearLayoutRecords = view.findViewById(R.id.linearLayoutNotes);
        linearLayoutRecords.removeAllViews();
        List<Note> notes = new NoteHandler(getActivity()).getAllNotes();
        if (notes.size() > 0) {
            for (Note obj : notes) {
                int id = obj.getId();
                String title = obj.getTitle();
                String text = obj.getText();
                String date = obj.getDate();
                String textViewContents = String.valueOf(id)
                        + "-" + title + " - "
                        + text + " - " + date;
                TextView textViewNoteItem = new TextView(getActivity());
                textViewNoteItem.setPadding(0, 10, 0, 10);
                textViewNoteItem.setText(textViewContents);
                textViewNoteItem.setTag(Integer.toString(id));
                textViewNoteItem.setOnLongClickListener(new OnLongClickListenerUpdateNote());
                linearLayoutRecords.addView(textViewNoteItem);
            }
        } else {
            TextView locationItem = new TextView(getActivity());
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Aucune note pour le moment.");
            linearLayoutRecords.addView(locationItem);
        }
    }

    // Met à jour la liste des notes avec les nouvelles notes fournies.
    public void updateNotesList(List<Note> updatedNotes) {
        LinearLayout linearLayoutRecords = getView().findViewById(R.id.linearLayoutNotes);
        linearLayoutRecords.removeAllViews();

        if (updatedNotes.size() > 0) {
            for (Note obj : updatedNotes) {
                int id = obj.getId();
                String title = obj.getTitle();
                String text = obj.getText();
                String date = obj.getDate();
                String textViewContents = String.valueOf(id)
                        + "-" + title + " - "
                        + text + " - " + date;
                TextView textViewNoteItem = new TextView(getActivity());
                textViewNoteItem.setPadding(0, 10, 0, 10);
                textViewNoteItem.setText(textViewContents);
                textViewNoteItem.setTag(Integer.toString(id));
                textViewNoteItem.setOnLongClickListener(new OnLongClickListenerUpdateNote());
                linearLayoutRecords.addView(textViewNoteItem);
            }
        } else {
            TextView locationItem = new TextView(getActivity());
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Aucune note pour le moment.");
            linearLayoutRecords.addView(locationItem);
        }
    }
}
