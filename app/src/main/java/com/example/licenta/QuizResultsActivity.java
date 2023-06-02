package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

public class QuizResultsActivity extends AppCompatActivity {

    private Button btnReturnHome;

    private Intent intent;

    private ActivityResultLauncher<Intent> launcher;

    public static final String QUIZ_KEY = "quizKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        intent = getIntent();
        initComponents();
    }

    private void initComponents() {
        btnReturnHome = findViewById(R.id.id_btn_returnHome);
        btnReturnHome.setOnClickListener(returnHomeEventListener());
    }

    private View.OnClickListener returnHomeEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }
}