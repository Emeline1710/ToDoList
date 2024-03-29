package com.example.todolist.Managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// Cette classe gère la création de la base de données SQLite pour stocker les notes.
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MiniProjet";
    protected static final String TABLE = "Note";
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TEXT = "text";
    public static final String KEY_DATE = "date";

    // Constructeur
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Requête SQL pour créer la table Note
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE +
                " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_TITLE + " TEXT," +
                KEY_TEXT + " TEXT," +
                KEY_DATE + " TEXT" + ")";
        // Exécute la requête SQL pour créer la table
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprime la table existante si elle existe
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Recrée la table
        onCreate(db);
    }
}
