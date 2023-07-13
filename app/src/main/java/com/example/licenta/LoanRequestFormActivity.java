package com.example.licenta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.dto.Client;
import com.example.licenta.dto.LoanRequest;
import com.example.licenta.utils.CreditType;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class LoanRequestFormActivity extends AppCompatActivity {

    private Spinner spnCreditType;
    private TextInputEditText tietTotalAmount;
    private Spinner spnPeriod;
    private TextInputEditText tietInterestRate;
    private Button btnNext;
    private Button btnNeedHelp;

    private ActivityResultLauncher<Intent> launcher;

    public static final String LOAN_REQUEST_KEY = "loanRequestKey";

    private Intent homeIntent;
    private Client client;
    public static final String CLIENT_KEY = "clientKey";
    private LoanRequest loanRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request_form);

        initComponents();
        launcher = getLauncher();
        homeIntent = getIntent();
        client = (Client) getIntent().getSerializableExtra(CLIENT_KEY);
        loanRequest = new LoanRequest(client.getId());
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
        tietInterestRate = findViewById(R.id.id_tiet_interest_rate);
        spnCreditType = findViewById(R.id.id_spn_creditType);
        ArrayAdapter<CreditType> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                CreditType.values());
        spnCreditType.setAdapter(adapter1);
        spnCreditType.setOnItemSelectedListener(getInterestRateValue());
        tietTotalAmount = findViewById(R.id.id_tiet_amount);
        spnPeriod = findViewById(R.id.id_spn_period);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.creditPeriod,
                android.R.layout.simple_spinner_dropdown_item);
        spnPeriod.setAdapter(adapter2);
        btnNext = findViewById(R.id.id_btn_next);
        btnNext.setOnClickListener(nextEventListener());
        btnNeedHelp = findViewById(R.id.id_btn_needHelp);
        btnNeedHelp.setOnClickListener(onNeedHelpEventListener());
    }

    private AdapterView.OnItemSelectedListener getInterestRateValue() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tietInterestRate.setText(" ");
                tietInterestRate.append("" + setValueForInterestRateBasedOnCreditType());
                tietInterestRate.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tietInterestRate.setText(" ");
                tietInterestRate.append("" + setValueForInterestRateBasedOnCreditType());
                tietInterestRate.setEnabled(false);
            }
        };
    }

    public double setValueForInterestRateBasedOnCreditType() {
        double interestRateByType;
        if (spnCreditType.getSelectedItem().toString().equalsIgnoreCase("Student Loan")) {
            interestRateByType = 2.5;
        } else {
            if (spnCreditType.getSelectedItem().toString().equalsIgnoreCase("House Loan")) {
                interestRateByType = 4.5;
            } else {
                if (spnCreditType.getSelectedItem().toString().equalsIgnoreCase("Travel Loan")) {
                    interestRateByType = 3;
                } else {
                    interestRateByType = 3.5;
                }
            }
        }
        return interestRateByType;
    }

    private View.OnClickListener onNeedHelpEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog quizDialog = new AlertDialog.Builder(LoanRequestFormActivity.this)
                        .setTitle(R.string.dialog_quiz_title)
                        .setMessage(R.string.dialog_quiz_message)
                        .setPositiveButton(R.string.dialog_quiz_yes, getPositiveQuizDialogEvent())
                        .setNegativeButton(R.string.dialog_quiz_no, getNegativeQuizDialogEvent())
                        .create();
                quizDialog.show();
            }
        };
    }

    private DialogInterface.OnClickListener getNegativeQuizDialogEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
    }

    private DialogInterface.OnClickListener getPositiveQuizDialogEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), QuizPageOneActivity.class);
                intent.putExtra(CLIENT_KEY, client);
                setResult(RESULT_OK, intent);
                finish();
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener nextEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    createFromViews();
                    Intent intent = new Intent(getApplicationContext(), LoanRequestFormSecondActivity.class);
                    intent.putExtra(LOAN_REQUEST_KEY, (Serializable) loanRequest);
                    intent.putExtra("home_intent", homeIntent);
                    setResult(RESULT_OK, intent);
                    finish();
                    launcher.launch(intent);
                }
            }
        };
    }

    private void createFromViews() {
        String creditType = spnCreditType.getSelectedItem().toString();
        long totalAmount = Long.parseLong(tietTotalAmount.getText().toString());
        int period = Integer.parseInt(spnPeriod.getSelectedItem().toString());
        float interestRate = Float.parseFloat(tietInterestRate.getText().toString());
        loanRequest.setCreditType(CreditType.getType(creditType));
        loanRequest.setTotalAmount(totalAmount);
        loanRequest.setPeriod(period);
        loanRequest.setInterestRate(interestRate);
    }

    private boolean isValid() {
        if (tietTotalAmount.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                            R.string.sign_in_form_error,
                            Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (spnCreditType.getSelectedItem().toString().equalsIgnoreCase(CreditType.STUDENT_LOAN.toString())) {
            if ((Integer.parseInt(tietTotalAmount.getText().toString())) > 50000) {
                Toast.makeText(getApplicationContext(),
                                R.string.student_loan_max_amount,
                                Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (spnCreditType.getSelectedItem().toString().equalsIgnoreCase(CreditType.TRAVEL_LOAN.toString())) {
            if ((Integer.parseInt(tietTotalAmount.getText().toString())) > 35000) {
                Toast.makeText(getApplicationContext(),
                                R.string.travel_loan_max_amount,
                                Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (spnCreditType.getSelectedItem().toString().equalsIgnoreCase(CreditType.PERSONAL_LOAN.toString())) {
            if ((Integer.parseInt(tietTotalAmount.getText().toString())) > 65000) {
                Toast.makeText(getApplicationContext(),
                                R.string.personal_loan_max_amount,
                                Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (spnCreditType.getSelectedItem().toString().equalsIgnoreCase(CreditType.HOUSE_LOAN.toString())) {
            if ((Integer.parseInt(tietTotalAmount.getText().toString())) > 300000) {
                Toast.makeText(getApplicationContext(),
                                R.string.house_loan_max_amount,
                                Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        return true;
    }
}