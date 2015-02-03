package net.muxistudio.personaldairy_v1.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 15-2-3.
 */
public class MyDairyDao {
    private SQLiteDatabase db;

    public MyDairyDao(Context context) {
        db = DataBaseHelper.getInstance(context);
    }

    public void insert(String title, String content){
        String insertDairySQL = "INSERT INTO " + DataBaseHelper.TABLE_DAIRY
                + " VALUES (NULL, ?, ?)";
        db.execSQL(insertDairySQL, new String[]{title, content});
        //插入数据
    }

    public List<Map<String, String>> loadDairy(){
        String querySQL = "SELECT * FROM " + DataBaseHelper.TABLE_DAIRY;
        Cursor cursor = db.rawQuery(querySQL, null);

        List<Map<String ,String >> list = new ArrayList<>();

        if (cursor.getCount() >= 1){
            while (cursor.moveToNext()){
                Map<String ,String > map = new HashMap<>();
                map.put(DataBaseHelper.KEY_DAIRY_TITLE,
                        cursor.getString(cursor.getColumnIndex(DataBaseHelper.KEY_DAIRY_TITLE)));
                map.put(DataBaseHelper.KEY_DAIRY_CONTENT,
                        cursor.getString(cursor.getColumnIndex(DataBaseHelper.KEY_DAIRY_CONTENT)));
                list.add(map);
            }
        }

        return list;

        //把数据打包，用list带回
    }
}
