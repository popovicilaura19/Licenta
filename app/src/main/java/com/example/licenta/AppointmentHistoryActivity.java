package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.async.Callback;
import com.example.licenta.dto.Appointment;
import com.example.licenta.dto.Client;
import com.example.licenta.dto.adapter.AppointmentAdapter;
import com.example.licenta.services.AppointmentService;

import java.util.ArrayList;
import java.util.List;

public class AppointmentHistoryActivity extends AppCompatActivity {

    private ListView lvAppoitments;
    private Button btnGoBackHome;
    private List<Appointment> appointmentList = new ArrayList<>();
    private Intent intent;
    private Client client;
    private AppointmentService appointmentService;
    public static final String CLIENT_KEY = "clientKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);

        intent = getIntent();
        client = (Client) getIntent().getSerializableExtra(CLIENT_KEY);
        appointmentService = new AppointmentService(getApplicationContext());
        appointmentService.getAll(getAllCallback());
        initComponents();
    }

    private Callback<List<Appointment>> getAllCallback() {
        return new Callback<List<Appointment>>() {
            @Override
            public void runResultOnUiThread(List<Appointment> result) {
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getClientId() == client.getId())
                        appointmentList.add(result.get(i));
                }
                lvAppoitments = findViewById(R.id.id_lv_appointments);
                AppointmentAdapter adapter = new AppointmentAdapter(getApplicationContext(), R.layout.lv_appointment_item, appointmentList, getLayoutInflater());
                lvAppoitments.setAdapter(adapter);
            }
        };
    }

    private void initComponents() {
        btnGoBackHome = findViewById(R.id.id_btn_appointment_goBack);
        btnGoBackHome.setOnClickListener(goBackHomeEventListener());
    }

    private View.OnClickListener goBackHomeEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }
}