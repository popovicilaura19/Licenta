package com.example.licenta.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "appointment")
public class Appointment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "appointment_id")
    private long appointmentId;
    @ColumnInfo(name = "client_id")
    private long clientId;
    @ColumnInfo(name = "request_id")
    private long requestId;
    @ColumnInfo(name = "agentName")
    private String agentName;
    @ColumnInfo(name = "dayOfMeeting")
    private int dayOfMeeting;
    @ColumnInfo(name = "monthOfMeeting")
    private int monthOfMeeting;
    @ColumnInfo(name = "yearOfMeeting")
    private int yearOfMeeting;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "locationName")
    private String locationName;

    public Appointment(long appointmentId,long clientId, long requestId, String agentName, int dayOfMeeting, int monthOfMeeting, int yearOfMeeting, String time, String locationName) {
        this.appointmentId = appointmentId;
        this.clientId=clientId;
        this.requestId = requestId;
        this.agentName = agentName;
        this.dayOfMeeting = dayOfMeeting;
        this.monthOfMeeting = monthOfMeeting;
        this.yearOfMeeting = yearOfMeeting;
        this.time = time;
        this.locationName = locationName;
    }

    @Ignore
    public Appointment(long clientId, long requestId) {
        this.clientId=clientId;
        this.requestId = requestId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public int getDayOfMeeting() {
        return dayOfMeeting;
    }

    public void setDayOfMeeting(int dayOfMeeting) {
        this.dayOfMeeting = dayOfMeeting;
    }

    public int getMonthOfMeeting() {
        return monthOfMeeting;
    }

    public void setMonthOfMeeting(int monthOfMeeting) {
        this.monthOfMeeting = monthOfMeeting;
    }

    public int getYearOfMeeting() {
        return yearOfMeeting;
    }

    public void setYearOfMeeting(int yearOfMeeting) {
        this.yearOfMeeting = yearOfMeeting;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", clientId=" + clientId +
                ", requestId=" + requestId +
                ", agentName='" + agentName + '\'' +
                ", dayOfMeeting=" + dayOfMeeting +
                ", monthOfMeeting=" + monthOfMeeting +
                ", yearOfMeeting=" + yearOfMeeting +
                ", time='" + time + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
