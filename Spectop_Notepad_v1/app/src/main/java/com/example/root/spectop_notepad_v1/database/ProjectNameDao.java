package com.example.root.spectop_notepad_v1.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 15-1-8.
 */
public class ProjectNameDao {
    private SQLiteDatabase db;

    public ProjectNameDao(Context context){
        db = DataBaseHelper.getInstance(context);
    }

    public void insert(String title, String note){
        String insertDiarySQL = "INSERT INTO " + DataBaseHelper.TABLE_NOTE
                + " VALUES (NULL, ?, ?)";
        db.execSQL(insertDiarySQL, new String[]{title, note});

    }

    public List<Map<String, String>> loadNote(){
        String querySQL = "SELECT * from " + DataBaseHelper.TABLE_NOTE;
        Cursor cursor = db.rawQuery(querySQL, null);

        List<Map<String, String>> list = new ArrayList<>();

        if (cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Map<String, String> map = new HashMap<>();
                map.put(DataBaseHelper.KEY_NOTE_TITLE,
                        cursor.getString(cursor.getColumnIndex(DataBaseHelper.KEY_NOTE_TITLE)));
                map.put(DataBaseHelper.KEY_NOTE_CONTENT,
                        cursor.getString(cursor.getColumnIndex(DataBaseHelper.KEY_NOTE_CONTENT)));
                list.add(map);
            }
        }

        return list;
    }

}
