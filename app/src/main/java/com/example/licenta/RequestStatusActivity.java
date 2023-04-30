package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.dto.LoanRequest;

public class RequestStatusActivity extends AppCompatActivity {

    private TextView tvStatus;
    private Button btnGoHome;
    private ActivityResultLauncher<Intent> launcherGoHome;
    private Intent intent;
    public static final String LOAN_REQUEST_KEY = "loanRequestKey";
    public static final String LOAN_KEY = "loanKey";
    private LoanRequest loanRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status);

        initComponents();
        intent = getIntent();
        loanRequest = (LoanRequest) getIntent().getSerializableExtra(LOAN_KEY);
        System.out.println(loanRequest);
    }

    private void initComponents() {
        tvStatus = findViewById(R.id.id_tv_status);
        btnGoHome = findViewById(R.id.id_btn_goHome);
        btnGoHome.setOnClickListener(goHomeEventListener());
    }

    private View.OnClickListener goHomeEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }

    public void analyzeRequest() {

    }
}