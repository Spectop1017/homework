package com.example.spectop.spectop15010104.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spectop on 15-1-2.
 */
public class ProjectNameDao {
    private SQLiteDatabase db;

    public ProjectNameDao(Context context){
        db = DataBaseHelper.getInstance(context);
    }

    public void insertNote(String title, String content) {
        String insertDiarySQL = "INSERT INTO " + DataBaseHelper.TABLE_NOTE
                + " VALUES (NULL, ?, ?)";
        db.execSQL(insertDiarySQL, new String[]{title, content});
    }

    public List<Map<String ,String >> loadNote(){
        String querySQL = "SELECT * from " + DataBaseHelper.TABLE_NOTE;
        Cursor cursor = db.rawQuery(querySQL, null);

        List<Map<String, String>> list = new ArrayList<>();

        if (cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Map<String, String> map = new HashMap<>();
                map.put(DataBaseHelper.KEY_NOTE_TITTLE,
                        cursor.getString(cursor.getColumnIndex(DataBaseHelper.KEY_NOTE_TITTLE)));
                map.put(DataBaseHelper.KEY_NOTE_ID,
                        cursor.getString(cursor.getColumnIndex(DataBaseHelper.KEY_NOTE_ID)));
                map.put(DataBaseHelper.KEY_NOTE_CONTENT,
                        cursor.getString(cursor.getColumnIndex(DataBaseHelper.KEY_NOTE_CONTENT)));
                list.add(map);
            }
        }

        return list;
    }
}
