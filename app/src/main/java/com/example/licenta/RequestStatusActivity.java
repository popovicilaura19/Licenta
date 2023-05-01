package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.async.Callback;
import com.example.licenta.dto.LoanRequest;
import com.example.licenta.services.LoanRequestService;
import com.example.licenta.utils.RequestStatus;

public class RequestStatusActivity extends AppCompatActivity {

    private TextView tvStatus;
    private Button btnGoHome;
    private ActivityResultLauncher<Intent> launcherGoHome;
    private Intent intent;
    public static final String LOAN_REQUEST_KEY = "loanRequestKey";
    public static final String LOAN_KEY = "loanKey";
    private LoanRequest loanRequest;
    private LoanRequestService loanRequestService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status);

        initComponents();
        intent = getIntent();
        loanRequest = (LoanRequest) getIntent().getSerializableExtra(LOAN_KEY);
        loanRequest.setStatus(RequestStatus.APPROVED);
        loanRequestService = new LoanRequestService(getApplicationContext());
        loanRequestService.insert(loanRequest, insertLoanCallback());

    }

    private Callback<LoanRequest> insertLoanCallback() {
        return new Callback<LoanRequest>() {
            @Override
            public void runResultOnUiThread(LoanRequest result) {
                tvStatus = findViewById(R.id.id_tv_status);
                tvStatus.append(" " + result.getStatus().toString() + "!");
            }
        };
    }

    private void initComponents() {
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