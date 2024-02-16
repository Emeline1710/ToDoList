package com.example.todolist;

import android.content.Context;
import android.database.sqlite.*;

public class NoteSQLite extends SQLiteOpenHelper {
    public static final String DB_Name = "MiniProjet";
    public static final int DB_Version = 1;
    public static final String table_name = "note";
    public static final String id = "id";
    public static final String title = "title";
    public static final String text = "text";
    public static final String date = "date";

    private static final String CREATE_BDD = "CREATE TABLE " + table_name + " ("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + title + " TEXT NOT NULL, "
            + text + " TEXT NOT NULL,"
            + date + " DATE NOT NULL);";

    public NoteSQLite(Context context)
    {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE " + table_name + ";");
        onCreate(db);
    }
}
