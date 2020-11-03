package com.example.colordatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ColorDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "colors.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TABLE_ID = "_id";
    public static final String TABLE = "colors";
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";
    public static final String FAVORITES = "favorites";


    public ColorDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public int getCount() {
        SQLiteDatabase sql = getReadableDatabase();
        Cursor c = sql.rawQuery("SELECT COUNT(*) FROM colors",null);
        c.moveToFirst();
        return c.getInt(0);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE colors( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "red INTEGER, "
                + "green INTEGER, "
                + "blue INTEGER, "
                + "favorites NUMERIC);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
