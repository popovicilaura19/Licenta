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

import com.example.licenta.utils.FamilySituation;
import com.google.android.material.textfield.TextInputEditText;

public class LoanRequestFormSecondActivity extends AppCompatActivity {

    private TextInputEditText tietIban;
    private TextInputEditText tietNrKids;
    private Spinner spnFamilySituation;
    private Button btnNext;
    private ActivityResultLauncher<Intent> launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request_form_second);

        initComponents();
        launcher=getLauncher();
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
        tietIban = findViewById(R.id.id_tiet_iban);
        tietNrKids = findViewById(R.id.id_tiet_nrKids);
        spnFamilySituation = findViewById(R.id.id_spn_familySituation);
        ArrayAdapter<FamilySituation> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                FamilySituation.values());
        spnFamilySituation.setAdapter(adapter1);
        btnNext = findViewById(R.id.id_btn_goNext);
        btnNext.setOnClickListener(goNextEventListener());
    }

    private View.OnClickListener goNextEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoanRequestFormThirdActivity.class);
//                intent.putExtra(USER_KEY, (Serializable) user);
                setResult(RESULT_OK, intent);
                finish();
                launcher.launch(intent);
            }
        };
    }
}