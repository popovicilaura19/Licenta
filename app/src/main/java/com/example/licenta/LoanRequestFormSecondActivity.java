package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.dto.LoanRequest;
import com.example.licenta.utils.FamilySituation;
import com.example.licenta.utils.Occupation;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class LoanRequestFormSecondActivity extends AppCompatActivity {

    private TextInputEditText tietIban;
    private TextInputEditText tietNrKids;
    private Spinner spnFamilySituation;
    private Spinner spnOccupation;
    private TextInputEditText tietDateOfEmployment;
    private TextInputEditText tietMonthlyIncome;
    private Button btnSendRequest;
    private Button btnCancel;
    private ActivityResultLauncher<Intent> launcherSendRequest;
    private Intent cancelIntent;
    private Intent homeIntent;
    public static final String LOAN_REQUEST_KEY = "loanRequestKey";
    public static final String LOAN_KEY = "loanKey";
    private LoanRequest loanRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request_form_second);

        initComponents();
        launcherSendRequest = getLauncherSendRequest();
        cancelIntent = getIntent();
        homeIntent = getIntent();
        loanRequest = (LoanRequest) getIntent().getSerializableExtra(LOAN_REQUEST_KEY);
    }

    private ActivityResultLauncher<Intent> getLauncherSendRequest() {
        ActivityResultCallback<ActivityResult> callback = getLoanSendRequestActivityResultCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getLoanSendRequestActivityResultCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        };
    }

    private void initComponents() {
        tietIban = findViewById(R.id.id_tiet_iban);
        tietNrKids = findViewById(R.id.id_tiet_nrKids);
        spnFamilySituation = findViewById(R.id.id_spn_familySituation);
        ArrayAdapter<FamilySituation> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                FamilySituation.values());
        spnFamilySituation.setAdapter(adapter1);
        spnOccupation = findViewById(R.id.id_spn_occupation);
        ArrayAdapter<Occupation> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                Occupation.values());
        spnOccupation.setAdapter(adapter2);
        tietDateOfEmployment = findViewById(R.id.id_tiet_dateEmpl);
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
                setResult(RESULT_CANCELED, cancelIntent);
                finish();
            }
        };
    }

    private View.OnClickListener sendRequestEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    createFromViews();
                    homeIntent.putExtra(LOAN_KEY, (Serializable) loanRequest);
                    setResult(RESULT_OK, homeIntent);
                    finish();
                }
            }
        };
    }

    private void createFromViews() {
        String iban = tietIban.getText().toString();
        int nrKids = Integer.parseInt(tietNrKids.getText().toString());
        String familySituation = spnFamilySituation.getSelectedItem().toString();
        String occupation = spnOccupation.getSelectedItem().toString();
        String dateOfEmployment = tietDateOfEmployment.getText().toString();
//        long monthlyIncome= Long.parseLong(tietMonthlyIncome.getText().toString());
        loanRequest.setIBAN(iban);
        loanRequest.setNrKids(nrKids);
        loanRequest.setFamilySituation(FamilySituation.getSituation(familySituation));
        loanRequest.setOccupation(Occupation.getOccupation(occupation));
        loanRequest.setDateOfEmployment(dateOfEmployment);
//        loanRequest.setMonthlyIncome(monthlyIncome);
    }

    private boolean isValid() {
        if (tietIban.getText().toString().isEmpty() || tietNrKids.getText().toString().isEmpty()
                || tietDateOfEmployment.getText().toString().isEmpty()
                || tietMonthlyIncome.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            R.string.sign_in_form_error,
                            Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }
}