package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.dto.Client;

public class QuizPageOneActivity extends AppCompatActivity {

    private CheckedTextView checkedYes;
    private CheckedTextView checkedNo;
    private Button btnNext;

    private Intent intent;
    private Client client;

    private ActivityResultLauncher<Intent> launcher;

    public static final String QUIZ_KEY = "quizKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page_one);

        intent = getIntent();
        client = (Client) getIntent().getSerializableExtra(QUIZ_KEY);
//        appointmentService = new AppointmentService(getApplicationContext());
//        appointmentService.getAll(getAllCallback());
        initComponents();
    }

    private void initComponents() {
        btnNext = findViewById(R.id.id_btn_quizOneNext);
        btnNext.setOnClickListener(goToPageTwoOfQuizEventListener());
    }

    private View.OnClickListener goToPageTwoOfQuizEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}