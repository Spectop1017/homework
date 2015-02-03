package net.muxistudio.personaldairy_v1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 15-2-3.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "dairy_db";
    private final static int DATABASE_VERSION = 1;

    private static DataBaseHelper instance = null;
    public final static String TABLE_DAIRY = "dairy";

    public final static String KEY_DAIRY_ID = "_id";
    public final static String KEY_DAIRY_TITLE = "title";
    public final static String KEY_DAIRY_CONTENT = "content";

    public final static String TYPE_TEXT = " TEXT, ";
    //数据库命令有关的变量

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createProjectNameSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_DAIRY
                + "(" + KEY_DAIRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_DAIRY_TITLE + TYPE_TEXT
                + KEY_DAIRY_CONTENT + " TEXT);";
        db.execSQL(createProjectNameSQL);
        //创建数据库
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
