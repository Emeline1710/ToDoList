package com.example.todolist.Managers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.todolist.Models.Note;

import java.util.ArrayList;
import java.util.List;


// Cette classe sert à gérer les notes dans la base de données SQLite.

public class NoteHandler extends SQLiteHelper {
    private SQLiteHelper dbHelper;

    public NoteHandler(Context context) {
        super(context);
        dbHelper = new SQLiteHelper(context);
    }

    // Cette méthode ajoute une note à la base de données.
    public int addNote(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_TITLE, note.getTitle());
        values.put(dbHelper.KEY_TEXT, note.getText());
        values.put(dbHelper.KEY_DATE, note.getDate());
        long insertId = db.insert(dbHelper.TABLE, null, values);
        db.close();
        return (int) insertId;
    }

    // Cette méthode récupère une note à partir de son ID.
    public Note getNote(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbHelper.TABLE, new String[]{dbHelper.KEY_ID,
                        dbHelper.KEY_TITLE, dbHelper.KEY_TEXT, dbHelper.KEY_DATE}, dbHelper.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Note note = new Note();
        note.setId(Integer.parseInt(cursor.getString(0)));
        note.setTitle(cursor.getString(1));
        note.setText(cursor.getString(2));
        note.setDate(cursor.getString(3));
        return note;
    }

    // Cette méthode récupère toutes les notes de la base de données.
    public List<Note> getAllNotes() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Note> noteList = new ArrayList<Note>();
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                note.setDate(cursor.getString(3));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        return noteList;
    }

    // Cette méthode supprime une note de la base de données.
    public void deleteNote(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE, dbHelper.KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    // Cette méthode met à jour une note dans la base de données.
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_TEXT, note.getText());
        values.put(KEY_DATE, note.getDate());
        int updateId = db.update(TABLE, values,
                KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
        return updateId;
    }

    // Cette méthode récupère toutes les notes triées par date (la plus récente en premier).
    public List<Note> getNotesSortedByDate() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Note> noteList = new ArrayList<Note>();
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE + " ORDER BY " + dbHelper.KEY_DATE + " DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                note.setDate(cursor.getString(3));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        return noteList;
    }

    // Cette méthode récupère toutes les notes triées par titre (ordre alphabetique).
    public List<Note> getNotesSortedByTitle() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Note> noteList = new ArrayList<Note>();
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE + " ORDER BY " + dbHelper.KEY_TITLE + " ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                note.setDate(cursor.getString(3));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        return noteList;
    }

    // Cette méthode récupère toutes les notes triées par ID.
    public List<Note> getNotesSortedById() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Note> noteList = new ArrayList<Note>();
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE + " ORDER BY " + dbHelper.KEY_ID + " ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                note.setDate(cursor.getString(3));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        return noteList;
    }

    // Cette méthode compte le nombre de notes dans la base de données.
    public int count() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + dbHelper.TABLE;
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();
        return recordCount;
    }
}
