package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.utils.CreditType;
import com.google.android.material.textfield.TextInputEditText;

public class CalculatorActivity extends AppCompatActivity {

    private Spinner spnCalculatorCreditType;
    private TextInputEditText tietMonths;
    private TextInputEditText tietAmount;
    private Button btnCalculate;
    private Button btnGoHome;
    private TextView tvMonthlyRate;
    private TextView tvInterestRate;

    private double interestRateByType;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        initComponents();
        intent = getIntent();
    }

    private void initComponents() {
        spnCalculatorCreditType = findViewById(R.id.id_spn_calculator);
        ArrayAdapter<CreditType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                CreditType.values());
        spnCalculatorCreditType.setAdapter(adapter);
        tietMonths = findViewById(R.id.id_tiet_calculatoMonths);
        tietAmount = findViewById(R.id.id_tiet_calculatorAmount);
        tvMonthlyRate = findViewById(R.id.id_tv_calculator_monthlyRate);
        tvInterestRate = findViewById(R.id.id_tv_calculatorInterestRate);
        btnCalculate = findViewById(R.id.id_btn_calculate);
        btnCalculate.setOnClickListener(calculateOnClickListener());
        btnGoHome = findViewById(R.id.id_btn_calculator_goHome);
        btnGoHome.setOnClickListener(goHomeOnClickListener());
    }

    private View.OnClickListener goHomeOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }

    private View.OnClickListener calculateOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalCreditAmount = Double.parseDouble(tietAmount.getText().toString());
                double nrMonths = Double.parseDouble(tietMonths.getText().toString());
                double interestRatePercent = getInterestRateByCreditType();
                double monthlyRate = getMonthlyInterestEffectively(totalCreditAmount, nrMonths, interestRatePercent / 10);
                int monthlyRateInteger = (int) monthlyRate;
                tvMonthlyRate.setText(R.string.your_monthly_rate_will_be);
                tvMonthlyRate.append(" " + monthlyRateInteger);
                tvInterestRate.setText(R.string.this_value_was_calculated_for_an_interest_rate_of_value);
                tvInterestRate.append(" " + interestRatePercent + "%");
            }
        };
    }

    public double getMonthlyInterestEffectively(double totalCreditAmount, double nrMonths, double interestRatePercent) {
        double sum = 0;
        for (int i = 1; i < nrMonths / 12; i++) {
            sum += 1 / Math.pow(1 + interestRatePercent * 0.01, i);
        }
        double annuity = (totalCreditAmount / sum) / 12;
        System.out.println("annuity: " + annuity);
        double installment = 0.0;
        double outstandingCreditBalance = totalCreditAmount - installment;
        interestRatePercent = Math.pow((interestRatePercent + 1), (1.0 / 12.0)) * 12 - 12;
        interestRatePercent = interestRatePercent / 12;
        double interest = interestRatePercent * 0.1 * outstandingCreditBalance;
        installment = annuity - interest;
        double sumOfInterest = interest;
        for (int i = 1; i < nrMonths && outstandingCreditBalance > 0; i++) {
            outstandingCreditBalance = outstandingCreditBalance - installment;
            interest = interestRatePercent * 0.1 * outstandingCreditBalance;
            installment = annuity - interest;
            sumOfInterest += interest;
            System.out.println("outstanding credit balance: " + outstandingCreditBalance);
            System.out.println("interest: " + interest);
            System.out.println("installment: " + installment);
        }
        return sumOfInterest / 2;
    }

    public double getInterestRateByCreditType() {
        if (spnCalculatorCreditType.getSelectedItem().toString().equalsIgnoreCase("Student Loan")) {
            interestRateByType = 2.5;
        } else {
            if (spnCalculatorCreditType.getSelectedItem().toString().equalsIgnoreCase("House Loan")) {
                interestRateByType = 4.5;
            } else {
                if (spnCalculatorCreditType.getSelectedItem().toString().equalsIgnoreCase("Travel Loan")) {
                      interestRateByType = 3;}
                else{
                    interestRateByType=3.5;
                }
            }
        }
        return interestRateByType;
    }
}