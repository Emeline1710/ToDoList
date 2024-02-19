package com.example.todolist.Managers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.todolist.Models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteHandler extends SQLiteHelper{
    private SQLiteHelper dbHelper;

    public NoteHandler(Context context){
        super(context);
        dbHelper = new SQLiteHelper(context);
    }

    public int addNote(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_TITLE, note.getTitle());
        values.put(dbHelper.KEY_TEXT, note.getText());
        values.put(dbHelper.KEY_DATE, note.getDate());
        long insertId = db.insert(dbHelper.TABLE, null, values);
        db.close();
        return (int)insertId;
    }

    public Note getNote(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbHelper.TABLE, new String[] { dbHelper.KEY_ID,
                        dbHelper.KEY_TITLE, dbHelper.KEY_TEXT, dbHelper.KEY_DATE }, dbHelper.KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Note note = new Note();
        note.setId(Integer.parseInt(cursor.getString(0)));
        note.setTitle(cursor.getString(1));
        note.setText(cursor.getString(2));
        note.setDate(cursor.getString(3));
        return note;
    }

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

    public void deleteNote(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE, dbHelper.KEY_ID + " = ?",
                new String[] { String.valueOf(note.getId()) });
        db.close();
    }

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

    public int count() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + dbHelper.TABLE;
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();
        return recordCount;
    }
}