package com.example.finnal;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class reserve extends Activity {
    private Button button1,button2,button3,button4,button5,button6,button7,backbutton;
    TextView text1,text2,text3,text4,text5,text6,text7;
    private int year;
    private int month;
    private int day;
    private int alarmDay;
    private SQLiteDatabase mDbWriter;
    private DBHelper dbhelper;
    private AlarmManager alarmManager;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        dbhelper = new DBHelper(this);
        mDbWriter = dbhelper.getWritableDatabase();
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=getIntent();
        name=intent.getStringExtra("username");
        initView();
        Calendar myCalendar = Calendar.getInstance(Locale.CHINA);
        Date myDate = new Date();
        myCalendar.setTime(myDate);

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);

        text1.setText("未预约");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text1.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="1";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text1.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                        setClock(view);
                    }
                    },year,month,day);
                dpd.show();
                    }
        });
        myCalendar.setTime(myDate);//为Calendar对象设置时间为当前日期

        alarmDay = day;
        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);

        text2.setText("未预约");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text2.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="2";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text2.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                        setClock(view);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        myCalendar.setTime(myDate);//为Calendar对象设置时间为当前日期

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);

        text3.setText("未预约");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text3.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="3";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text3.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                        setClock(view);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        myCalendar.setTime(myDate);

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        day = myCalendar.get(Calendar.DAY_OF_MONTH);

        text4.setText("未预约");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text4.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="4";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text4.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                        setClock(view);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        text5.setText("未预约");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text5.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="5";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text5.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                        setClock(view);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        text6.setText("未预约");
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text6.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="6";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text6.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                        setClock(view);
                    }
                },year,month,day);
                dpd.show();
            }
        });
        text7.setText("未预约");
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd=new DatePickerDialog(reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear, int dayOfMonth) {
                        text7.setText(myyear+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        String s="7";
                        ContentValues mContentValues = new ContentValues();
                        mContentValues.put("userid", name);
                        mContentValues.put("pinzhong",s);
                        mContentValues.put("data", text7.getText().toString().trim());
                        mDbWriter.insert("yuyue", null, mContentValues);
                        setClock(view);
                    }
                },year,month,day);
                dpd.show();
            }
        });
    backbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent2=new Intent(reserve.this,MainActivity.class);
            intent2.putExtra("username",name);
            startActivity(intent2);
        }
    });
    }

    private void initView() {
        button1 =  findViewById(R.id.yuyuebutton1);
        button2 =  findViewById(R.id.yuyuebutton2);
        button3 =  findViewById(R.id.yuyuebutton3);
        button4 = findViewById(R.id.yuyuebutton4);
        button5 = findViewById(R.id.yuyuebutton5);
        button6 = findViewById(R.id.yuyuebutton6);
        button7 = findViewById(R.id.yuyuebutton7);
        backbutton=findViewById(R.id.fanhui);

        text1 =findViewById(R.id.yuyuetext1);
        text2 =findViewById(R.id.yuyuetext2);
        text3 =findViewById(R.id.yuyuetext3);
        text4 =findViewById(R.id.yuyuetext4);
        text5 =findViewById(R.id.yuyuetext5);
        text6 =findViewById(R.id.yuyuetext6);
        text7 =findViewById(R.id.yuyuetext7);
    }

    public void setClock(View view){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                Intent intent = new Intent(reserve.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(reserve.this,0X102, intent,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                Toast.makeText(reserve.this, "预约成功", Toast.LENGTH_SHORT).show();
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

}

