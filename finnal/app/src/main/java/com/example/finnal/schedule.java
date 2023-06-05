package com.example.finnal;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class schedule extends Activity {
    private Button show;
    private AppointmentContentProvider appointmentProvider = new AppointmentContentProvider();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 插入新的预约
        Appointment appointment = new Appointment(1, "Meeting", "2023-06-05", "10:00 AM");
        appointmentProvider.insert(AppointmentContentProvider.CONTENT_URI, appointment.getContentValues());

        //从数据库中获取预约
        List<Appointment> appointments = appointmentProvider.getAllAppointments();
        AppointmentAdapter adapter = new AppointmentAdapter(appointments);
        recyclerView.setAdapter(adapter);

//        // 更新预约
//        appointment.setTitle("Updated Meeting");
//        appointmentProvider.update(ContentUris.withAppendedId(AppointmentContentProvider.CONTENT_URI, appointment.getId()), appointment.getContentValues(), null, null);
//
//        // 删除预约
//        appointmentProvider.delete(ContentUris.withAppendedId(AppointmentContentProvider.CONTENT_URI, appointment.getId()), null, null);
    }

    private void initView() {
        show = findViewById(R.id.show);
        recyclerView = findViewById(R.id.recyclerView);
    }

}
