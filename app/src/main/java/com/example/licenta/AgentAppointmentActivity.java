package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.async.Callback;
import com.example.licenta.dto.Appointment;
import com.example.licenta.dto.Client;
import com.example.licenta.services.AppointmentService;
import com.example.licenta.utils.AgentName;
import com.example.licenta.utils.LocationName;

public class AgentAppointmentActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText time;
    private Spinner spnLocation;
    private Spinner spnAgentName;
    private Button btnCancel;
    private Button btnSetMeeting;
    private Intent cancelIntent;
    private Intent appointmentIntent;
    private int daySelected;
    private int monthSelected;
    private int yearSelected;
    private Appointment appointment;
    private Client client;
    private AppointmentService appointmentService;
    public static final String CLIENT_KEY = "clientKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_appointment);

        cancelIntent = getIntent();
        appointmentIntent = getIntent();
        initComponents();
        client = (Client) getIntent().getSerializableExtra(CLIENT_KEY);
        appointment = new Appointment(client.getId());
        appointmentService = new AppointmentService(getApplicationContext());
    }

    private void initComponents() {
        calendarView = findViewById(R.id.id_calendar_meeting);
        time = findViewById(R.id.id_edit_time);
        spnLocation = findViewById(R.id.id_spn_locationName);
        ArrayAdapter<LocationName> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                LocationName.values());
        spnLocation.setAdapter(adapter1);
        spnAgentName = findViewById(R.id.id_spn_agentName);
        ArrayAdapter<AgentName> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                AgentName.values());
        spnAgentName.setAdapter(adapter2);
        btnCancel = findViewById(R.id.id_btn_cancelMeeting);
        btnCancel.setOnClickListener(cancelMeetingEventListener());
        btnSetMeeting = findViewById(R.id.id_btn_setMeeting);
        btnSetMeeting.setOnClickListener(setMeetingEventListener());
    }

    private View.OnClickListener setMeetingEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    createFromViews();
                    appointmentService.insert(appointment, insertAppointmentCallback());
                    setResult(RESULT_OK, appointmentIntent);
                    finish();
                    Toast.makeText(getApplicationContext(),
                                    R.string.appointment_scheduled,
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        };
    }

    private Callback<Appointment> insertAppointmentCallback() {
        return new Callback<Appointment>() {
            @Override
            public void runResultOnUiThread(Appointment result) {
            }
        };
    }

    private void createFromViews() {
        String timeSelected = time.getText().toString();
        String locationName = spnLocation.getSelectedItem().toString();
        String agentName = spnAgentName.getSelectedItem().toString();
        appointment.setDayOfMeeting(daySelected);
        appointment.setMonthOfMeeting(monthSelected);
        appointment.setYearOfMeeting(yearSelected);
        appointment.setTime(timeSelected);
        appointment.setLocationName(locationName);
        appointment.setAgentName(agentName);
    }

    private boolean isValid() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                daySelected = dayOfMonth;
                monthSelected = month;
                yearSelected = year;
            }
        });
        if (time.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            R.string.sign_in_form_error,
                            Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    private View.OnClickListener cancelMeetingEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, cancelIntent);
                finish();
            }
        };
    }
}