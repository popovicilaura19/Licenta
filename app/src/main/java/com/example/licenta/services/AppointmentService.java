package com.example.licenta.services;

import android.content.Context;

import com.example.licenta.async.AsyncTaskRunner;
import com.example.licenta.async.Callback;
import com.example.licenta.database.AppointmentDao;
import com.example.licenta.database.DatabaseManager;
import com.example.licenta.dto.Appointment;

import java.util.List;
import java.util.concurrent.Callable;

public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final AsyncTaskRunner asyncTaskRunner;

    public AppointmentService(Context context) {
        this.appointmentDao = DatabaseManager.getInstance(context).getAppointmentDao();
        asyncTaskRunner = new AsyncTaskRunner();
    }

    public void insert(Appointment appointment, Callback<Appointment> activityThread) {
        Callable<Appointment> insertOperation = new Callable<Appointment>() {
            @Override
            public Appointment call() throws Exception {
                if (appointment == null || appointment.getAppointmentId() > 0) {
                    return null;
                }
                long id = appointmentDao.insert(appointment);
                if (id < 0) {
                    return null;
                }
                appointment.setAppointmentId(id);
                return appointment;
            }
        };
        asyncTaskRunner.executeAsync(insertOperation, activityThread);
    }


    public void getAll(Callback<List<Appointment>> activityThread) {
        Callable<List<Appointment>> getAllOperation = new Callable<List<Appointment>>() {
            @Override
            public List<Appointment> call() throws Exception {
                return appointmentDao.getAll();
            }
        };
        asyncTaskRunner.executeAsync(getAllOperation, activityThread);
    }

}
