package com.example.licenta.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.licenta.dto.Appointment;
import com.example.licenta.dto.Client;
import com.example.licenta.dto.LoanRequest;
import com.example.licenta.dto.User;

@Database(entities = {User.class, Client.class, LoanRequest.class, Appointment.class}, exportSchema = false, version = 3)
public abstract class DatabaseManager extends RoomDatabase {

    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, "digital_bank_db")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return databaseManager;
    }

    public abstract UserDao getUserDao();
    public abstract ClientDao getClientDao();
    public abstract LoanRequestDao getLoanRequestDao();
    public abstract AppointmentDao getAppointmentDao();

}
