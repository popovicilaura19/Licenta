package com.example.licenta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.licenta.dto.Appointment;

import java.util.List;

@Dao
public interface AppointmentDao {

    @Insert
    long insert(Appointment appointment);

    @Query("select * from appointment")
    List<Appointment> getAll();

}
