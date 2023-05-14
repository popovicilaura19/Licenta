package com.example.licenta.dto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.licenta.R;
import com.example.licenta.dto.Appointment;

import java.util.List;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    private Context context;
    private int resource;
    private List<Appointment> appointmentList;
    private LayoutInflater inflater;

    public AppointmentAdapter(@NonNull Context context, int resource, @NonNull List<Appointment> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.appointmentList = objects;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Appointment appointment = appointmentList.get(position);

        addRequestId(appointment.getRequestId(), view);
        addDay(appointment.getDayOfMeeting(), view);
        addMonth(appointment.getMonthOfMeeting(), view);
        addYear(appointment.getYearOfMeeting(), view);
        addTime(appointment.getTime(), view);
        addLocation(appointment.getLocationName(), view);

        return view;
    }

    private void addRequestId(long requestId, View view) {
        TextView textView = view.findViewById(R.id.id_tv_appReqId);
        textView.append(" " + requestId);
    }

    private void addDay(int day, View view) {
        TextView textView = view.findViewById(R.id.id_tv_dayApp);
        textView.append(" " + day);
    }

    private void addMonth(int month, View view) {
        TextView textView = view.findViewById(R.id.id_tv_monthApp);
        textView.append(" " + month);
    }

    private void addYear(int year, View view) {
        TextView textView = view.findViewById(R.id.id_tv_yearApp);
        textView.append(" " + year);
    }

    private void addTime(String time, View view) {
        TextView textView = view.findViewById(R.id.id_tv_timeApp);
        textView.append(" " + time);
    }

    private void addLocation(String location, View view) {
        TextView textView = view.findViewById(R.id.id_tv_locationApp);
        textView.append(" " + location);
    }

}
