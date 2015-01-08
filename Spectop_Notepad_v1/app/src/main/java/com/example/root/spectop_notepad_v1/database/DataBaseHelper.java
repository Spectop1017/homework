package com.example.root.spectop_notepad_v1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 15-1-8.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "project_db";
    private final static int DATABASE_VERSION = 1;

    private static DataBaseHelper instance = null;

    public final static String TABLE_NOTE = "note";

    public final static String KEY_NOTE_ID = "_id";
    public final static String KEY_NOTE_TITLE = "title";
    public final static String KEY_NOTE_CONTENT = "content";

    public final static String TYPE_TEXT = " TEXT, ";

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//static????
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createProjectNameSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTE
                + "(" + KEY_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NOTE_TITLE + TYPE_TEXT
                + KEY_NOTE_CONTENT + " TEXT);";

        db.execSQL(createProjectNameSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static SQLiteDatabase getInstance(Context context){
        if (instance == null){
            instance = new DataBaseHelper(context);
        }
        return instance.getReadableDatabase();
    }
}
