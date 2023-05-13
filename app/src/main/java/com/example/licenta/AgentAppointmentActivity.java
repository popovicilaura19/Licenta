package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AgentAppointmentActivity extends AppCompatActivity {

    private Button btnCancel;
    private Button btnSetMeeting;
    private Intent cancelIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_appointment);

        cancelIntent = getIntent();
        initComponents();
    }

    private void initComponents() {
        btnCancel = findViewById(R.id.id_btn_cancelMeeting);
        btnCancel.setOnClickListener(cancelMeetingEventListener());
        btnSetMeeting = findViewById(R.id.id_btn_setMeeting);
        btnSetMeeting.setOnClickListener(setMeetingEventListener());
    }

    private View.OnClickListener setMeetingEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
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