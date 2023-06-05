package com.example.finnal;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class AppointmentContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.myapp.appointments";
    private static final String BASE_PATH = "appointments";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final String COLUMN_ID = "S_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";

    private static final int APPOINTMENTS = 1;
    private static final int APPOINTMENT_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, APPOINTMENTS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", APPOINTMENT_ID);
    }

    private AppointmentDbHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        // 初始化 Content Provider
        // 这里可以执行一些必要的初始化操作，例如创建数据库表
        SQLiteOpenHelper dbHelper = new AppointmentDbHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case APPOINTMENTS:
                cursor = database.query(AppointmentDbHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case APPOINTMENT_ID:
                long id = ContentUris.parseId(uri);
                cursor = database.query(AppointmentDbHelper.TABLE_NAME, projection, AppointmentDbHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        database = dbHelper.getWritableDatabase();
        long id = database.insert(AppointmentDbHelper.TABLE_NAME, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rowsAffected = database.update(AppointmentDbHelper.TABLE_NAME, values, selection, selectionArgs);
        if (rowsAffected > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsAffected;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsAffected = database.delete(AppointmentDbHelper.TABLE_NAME, selection, selectionArgs);
        if (rowsAffected > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsAffected;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        Cursor cursor = query(CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME));

                Appointment appointment = new Appointment(id, title, date, time);
                appointments.add(appointment);
            }
            cursor.close();
        }
        return appointments;
    }
}
