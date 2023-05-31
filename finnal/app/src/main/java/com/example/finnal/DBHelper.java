package com.example.finnal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//创建数据库
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lib.db";

    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    final String CREATE_TABLE_SQL1="create table if not exists user(_id integer primary key autoincrement ,username,password)";
    final String CREATE_TABLE_SQL2="create table if not exists comment(_id integer primary key autoincrement ,userid,comment)";
    final String CREATE_TABLE_SQL3="create table if not exists reserve(_id integer primary key autoincrement ,userid,type,data)";
    public void onCreate(SQLiteDatabase sqLitedatabase){
        sqLitedatabase.execSQL(CREATE_TABLE_SQL1);
        sqLitedatabase.execSQL(CREATE_TABLE_SQL2);
        sqLitedatabase.execSQL(CREATE_TABLE_SQL3);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
    }


}
