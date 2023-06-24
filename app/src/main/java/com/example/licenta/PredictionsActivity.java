package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PredictionsActivity extends AppCompatActivity {

    private Button btnBack;
    private Button btnExchangeRate;
    private Button btnInflationRate;
    private Button btnInterestRate;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictions);

        intent = getIntent();
        initComponents();
    }

    private void initComponents() {
        btnBack = findViewById(R.id.id_btn_predictionBack);
        btnBack.setOnClickListener(goBackHomeEventListener());
        btnExchangeRate = findViewById(R.id.id_btn_exchangeRate);
        btnExchangeRate.setOnClickListener(getExchangeRatePrediction());
        btnInflationRate = findViewById(R.id.id_btn_inflationRate);
        btnInflationRate.setOnClickListener(getInflationRatePrediction());
        btnInterestRate = findViewById(R.id.id_btn_interestRatePrediction);
        btnInterestRate.setOnClickListener(getInterestRatePrediction());
    }

    private View.OnClickListener getInterestRatePrediction() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener getInflationRatePrediction() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InflationRateActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener getExchangeRatePrediction() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExchangeRateActivity.class);
                startActivity(intent);
            }
        };
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