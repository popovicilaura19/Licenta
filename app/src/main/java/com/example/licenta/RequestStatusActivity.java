package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.async.Callback;
import com.example.licenta.dto.LoanRequest;
import com.example.licenta.services.LoanRequestService;
import com.example.licenta.utils.CreditType;
import com.example.licenta.utils.FamilySituation;
import com.example.licenta.utils.Occupation;
import com.example.licenta.utils.RequestStatus;

public class RequestStatusActivity extends AppCompatActivity {

    private TextView tvStatus;
    private Button btnGoHome;
    private ImageView imgStatus;
    private ActivityResultLauncher<Intent> launcherGoHome;
    private Intent intent;
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
        analyzeRequest();
        loanRequestService = new LoanRequestService(getApplicationContext());
        loanRequestService.insert(loanRequest, insertLoanCallback());

    }

    private Callback<LoanRequest> insertLoanCallback() {
        return new Callback<LoanRequest>() {
            @Override
            public void runResultOnUiThread(LoanRequest result) {
                tvStatus = findViewById(R.id.id_tv_status);
                tvStatus.append(" " + result.getStatus().toString() + "!");
                if (result.getStatus() == RequestStatus.REQUIRES_AGENT_REVIEW)
                    imgStatus.setImageResource(R.drawable.fingers_crossed);
                else {
                    if (result.getStatus() == RequestStatus.REJECTED)
                        imgStatus.setImageResource(R.drawable.sorry);
                }
            }
        };
    }

    private void initComponents() {
        btnGoHome = findViewById(R.id.id_btn_goHome);
        btnGoHome.setOnClickListener(goHomeEventListener());
        imgStatus = findViewById(R.id.id_img_requestStatus);
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
        if (loanRequest.getCreditType() != CreditType.HOUSE_LOAN) {
            if (loanRequest.getTotalAmount() < 100000) {
                if (loanRequest.getTotalAmount() / loanRequest.getPeriod() < loanRequest.getMonthlyIncome() / 2) {
                    if (loanRequest.getOccupation() != Occupation.UNEMPLOYED) {
                        if (loanRequest.getNrKids() == 0 || loanRequest.getFamilySituation() != FamilySituation.DIVORCED) {
                            loanRequest.setStatus(RequestStatus.APPROVED);
                        } else {
                            loanRequest.setStatus(RequestStatus.REQUIRES_AGENT_REVIEW);
                        }
                    } else {
                        loanRequest.setStatus(RequestStatus.REQUIRES_AGENT_REVIEW);
                    }
                } else {
                    loanRequest.setStatus(RequestStatus.REJECTED);
                }
            } else {
                loanRequest.setStatus(RequestStatus.REQUIRES_AGENT_REVIEW);
            }
        } else {
            loanRequest.setStatus(RequestStatus.REQUIRES_AGENT_REVIEW);
        }
        if(loanRequest.getCreditType()==CreditType.STUDENT_LOAN && loanRequest.getOccupation()!=Occupation.STUDENT){
            loanRequest.setStatus(RequestStatus.REJECTED);
        }
    }
}