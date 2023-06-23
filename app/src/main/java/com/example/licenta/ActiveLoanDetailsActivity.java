package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.async.Callback;
import com.example.licenta.dto.ActiveLoanRecord;
import com.example.licenta.dto.LoanRequest;
import com.example.licenta.dto.adapter.ActiveLoanRecordAdapter;
import com.example.licenta.services.ActiveLoanRecordService;

import java.util.ArrayList;
import java.util.List;

public class ActiveLoanDetailsActivity extends AppCompatActivity {

    private ListView lvActiveLoanRecords;
    private TextView tvActiveLoanRecord;
    private TextView tvMonthlyRate;
    private Button btnHome;

    private Intent intent;

    private List<ActiveLoanRecord> activeLoanRecordList = new ArrayList<>();
    private LoanRequest loanRequest;
    private ActiveLoanRecordService activeLoanRecordService;
    public static final String ACTIVE_LOAN_KEY = "activeLoanKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_loan_details);

        intent = getIntent();
        loanRequest = (LoanRequest) getIntent().getSerializableExtra(ACTIVE_LOAN_KEY);
        activeLoanRecordService = new ActiveLoanRecordService(getApplicationContext());
        activeLoanRecordService.getListOfRecordsByLoanId(loanRequest.getRequestId(), getRecordListCallback());
        initComponents();
    }

    private Callback<List<ActiveLoanRecord>> getRecordListCallback() {
        return new Callback<List<ActiveLoanRecord>>() {
            @Override
            public void runResultOnUiThread(List<ActiveLoanRecord> result) {
                activeLoanRecordList.addAll(result);
                lvActiveLoanRecords = findViewById(R.id.id_lv_activeLoanRecord);
                ActiveLoanRecordAdapter adapter = new ActiveLoanRecordAdapter(getApplicationContext(), R.layout.lv_active_loan_record_item, activeLoanRecordList, getLayoutInflater());
                lvActiveLoanRecords.setAdapter(adapter);
                tvMonthlyRate.append(" " + activeLoanRecordList.get(0).getMonthlyRate());
                tvMonthlyRate.append("$. Don't forget to pay by the 15th of the month!");
                tvActiveLoanRecord.append(" " + activeLoanRecordList.get(activeLoanRecordList.size() - 1).getAmountDue() + "$.");
            }
        };
    }

    private void initComponents() {
        tvMonthlyRate = findViewById(R.id.id_tv_record_monthlyRate);
        tvActiveLoanRecord = findViewById(R.id.id_tv_activeAmountDue);
        btnHome = findViewById(R.id.id_btn_activeLoan_back);
        btnHome.setOnClickListener(goHomeActiveListener());
    }

    private View.OnClickListener goHomeActiveListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }
}