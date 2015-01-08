package com.example.spectop.spectop15010104.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by spectop on 15-1-2.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static DataBaseHelper instance = null;

    private final static String DATABASE_NAME = "project_db";
    private final static int DATABASE_VERSION = 1;

    public final static String TABLE_NOTE = "note";
    public final static String KEY_NOTE_ID = "_id";
    public final static String KEY_NOTE_TITTLE = "tittle";
    public final static String KEY_NOTE_CONTENT = "content";

    public final static String TYPE_TEXT = " TEXT, ";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteDatabase getInstance(Context context){
        if (instance == null){
            instance = new DataBaseHelper(context);
        }
        return instance.getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createProjectNameSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTE
                + "(" + KEY_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NOTE_TITTLE + TYPE_TEXT
                + KEY_NOTE_CONTENT + " TEXT);";


        db.execSQL(createProjectNameSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
