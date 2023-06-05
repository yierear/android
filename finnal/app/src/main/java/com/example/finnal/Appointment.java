package com.example.finnal;

import android.content.ContentValues;

import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;

public class Appointment {
    private int id;
    private String title;
    private String date;
    private String time;

    public Appointment(int id, String title, String date, String time) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(AppointmentDbHelper.COLUMN_TITLE, title);
        values.put(AppointmentDbHelper.COLUMN_DATE, date);
        values.put(AppointmentDbHelper.COLUMN_TIME, time);
        return values;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    // Getter 和 Setter 方法
    // ...
}
