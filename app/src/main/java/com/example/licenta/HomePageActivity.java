package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.dto.Client;
import com.example.licenta.dto.User;

public class HomePageActivity extends AppCompatActivity {

    private Button btnNewLoan;
    private User user;
    private Client client;
    private ActivityResultLauncher<Intent> launcher;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initComponents();
        launcher=getLauncher();

    }

    private ActivityResultLauncher<Intent> getLauncher() {
        ActivityResultCallback<ActivityResult> callback = getLoanRequestActivityResultCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getLoanRequestActivityResultCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        };
    }

    private void initComponents() {
        btnNewLoan=findViewById(R.id.id_btn_newLoanRequest);
        btnNewLoan.setOnClickListener(newLoanRequestEventListener());
    }

    private View.OnClickListener newLoanRequestEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoanRequestFormActivity.class);
//                intent.putExtra(USER_KEY, (Serializable) user);
                setResult(RESULT_OK, intent);
                finish();
                launcher.launch(intent);
            }
        };
    }
}