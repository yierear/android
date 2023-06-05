package com.example.finnal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppointmentDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "appointments.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "appointment";
    public static final String COLUMN_ID = "S_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_TIME + " TEXT)";

    public AppointmentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库升级操作
    }
}
