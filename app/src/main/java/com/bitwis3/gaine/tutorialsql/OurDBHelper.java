package com.bitwis3.gaine.tutorialsql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by gaine on 11/23/2017.
 */

public class OurDBHelper extends SQLiteOpenHelper {
    public OurDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.i("TUTORIAL_INFO", "ONCREATE HAS BEEN CALLED");

        String createString = "CREATE TABLE IF NOT EXISTS _tutorial_table (" +
                " _id   INTEGER PRIMARY KEY AUTOINCREMENT," +
                "_name STRING," +
                " _age  STRING);";

        sqLiteDatabase.execSQL(createString);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("TUTORIAL_INFO", "ONUPDATE HAS BEEN CALLED");
        onCreate(sqLiteDatabase);
    }
}
