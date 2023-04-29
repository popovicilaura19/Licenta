package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.dto.Client;
import com.example.licenta.utils.CreditType;
import com.google.android.material.textfield.TextInputEditText;

public class LoanRequestFormActivity extends AppCompatActivity {

    private Spinner spnCreditType;
    private TextInputEditText tietTotalAmount;
    private Spinner spnPeriod;
    private TextInputEditText tietInterestRate;
    private Button btnNext;

    private ActivityResultLauncher<Intent> launcher;

    public static final String LOAN_REQUEST_KEY = "loanRequestKey";

    private Intent homeIntent;
    private Client client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request_form);

        initComponents();
        launcher = getLauncher();
        homeIntent = getIntent();
    }

    private ActivityResultLauncher<Intent> getLauncher() {
        ActivityResultCallback<ActivityResult> callback = getLoanRequestNextActivityResultCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getLoanRequestNextActivityResultCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        };
    }

    private void initComponents() {
        spnCreditType = findViewById(R.id.id_spn_creditType);
        ArrayAdapter<CreditType> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                CreditType.values());
        spnCreditType.setAdapter(adapter1);
        tietTotalAmount = findViewById(R.id.id_tiet_amount);
        spnPeriod = findViewById(R.id.id_spn_period);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.creditPeriod,
                android.R.layout.simple_spinner_dropdown_item);
        spnPeriod.setAdapter(adapter2);
        tietInterestRate = findViewById(R.id.id_tiet_interest_rate);
        btnNext = findViewById(R.id.id_btn_next);
        btnNext.setOnClickListener(nextEventListener());
    }

    private View.OnClickListener nextEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoanRequestFormSecondActivity.class);
//                intent.putExtra(USER_KEY, (Serializable) user);
                intent.putExtra("home_intent", homeIntent);
                setResult(RESULT_OK, intent);
                finish();
                launcher.launch(intent);
            }
        };
    }
}