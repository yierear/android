package com.example.finnal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private List<Appointment> appointments;

    public AppointmentAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);
        holder.textTitle.setText(appointment.getTitle());
        holder.textDate.setText(appointment.getDate());
        holder.textTime.setText(appointment.getTime());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textDate;
        public TextView textTime;

        public ViewHolder(View view) {
            super(view);
            textTitle = view.findViewById(R.id.textTitle);
            textDate = view.findViewById(R.id.textDate);
            textTime = view.findViewById(R.id.textTime);
        }
    }
}
