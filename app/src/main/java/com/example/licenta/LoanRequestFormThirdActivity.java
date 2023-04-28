package com.example.licenta;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.utils.Occupation;
import com.google.android.material.textfield.TextInputEditText;

public class LoanRequestFormThirdActivity extends AppCompatActivity {

    private Spinner spnOccupation;
    private CalendarView dateOfEmployment;
    private TextInputEditText tietMonthlyIncome;
    private Button btnSendRequest;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request_form_third);

        initComponents();

    }

    private void initComponents() {
        spnOccupation = findViewById(R.id.id_spn_occupation);
        ArrayAdapter<Occupation> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                Occupation.values());
        spnOccupation.setAdapter(adapter1);
        dateOfEmployment = findViewById(R.id.id_calendar_dateEmpl);
        tietMonthlyIncome = findViewById(R.id.id_tiet_monthlyIncome);
        btnSendRequest = findViewById(R.id.id_btn_send);
        btnSendRequest.setOnClickListener(sendRequestEventListener());
        btnCancel = findViewById(R.id.id_btn_cancel);
        btnCancel.setOnClickListener(sendCancelEventListener());
    }

    private View.OnClickListener sendCancelEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener sendRequestEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}