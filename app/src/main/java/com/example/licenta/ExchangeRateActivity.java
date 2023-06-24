package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ExchangeRateActivity extends AppCompatActivity {

    private Button btnBack;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rate);

        intent = getIntent();
        btnBack = findViewById(R.id.id_btn_exchange_back);
        btnBack.setOnClickListener(goBackHomeEventListener());
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