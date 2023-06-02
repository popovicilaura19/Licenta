package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

public class QuizPageTwoActivity extends AppCompatActivity {

    private CheckBox checkedYes;
    private CheckBox checkedNo;
    private Button btnNext;

    private Intent intent;

    private ActivityResultLauncher<Intent> launcher;

    public static final String QUIZ_KEY = "quizKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page_two);

        intent = getIntent();
        initComponents();
    }

    private void initComponents() {
        btnNext = findViewById(R.id.id_btn_quizTwo_next);
        btnNext.setOnClickListener(goToPageThreeOfQuizEventListener());
    }

    private View.OnClickListener goToPageThreeOfQuizEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizPageThreeActivity.class);
//                intent.putExtra(QUIZ_KEY, client);
                setResult(RESULT_OK, intent);
                finish();
                startActivity(intent);
            }
        };
    }
}