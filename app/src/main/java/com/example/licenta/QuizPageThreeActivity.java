package com.example.licenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.licenta.utils.QuizResponse;

public class QuizPageThreeActivity extends AppCompatActivity {

    private CheckBox checkedYes;
    private CheckBox checkedNo;
    private Button btnNext;

    private Intent intent;
    private QuizResponse quizResponse;

    private ActivityResultLauncher<Intent> launcher;

    public static final String QUIZ_KEY = "quizKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page_three);

        intent = getIntent();
        initComponents();
        quizResponse = (QuizResponse) getIntent().getSerializableExtra(QUIZ_KEY);
    }

    private void initComponents() {
        checkedYes = findViewById(R.id.id_checkbox_yesStudent);
        checkedNo = findViewById(R.id.id_checkbox_noStudent);
        btnNext = findViewById(R.id.id_btn_quizThreeNext);
        btnNext.setOnClickListener(goToPageFourOfQuizEventListener());
    }

    private View.OnClickListener goToPageFourOfQuizEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedYes.isChecked()) {
                    quizResponse.setStudent(true);
                } else {
                    if (checkedNo.isChecked()) {
                        quizResponse.setStudent(false);
                    }
                }
                Intent intent = new Intent(getApplicationContext(), QuizPageFourActivity.class);
                intent.putExtra(QUIZ_KEY, quizResponse);
                setResult(RESULT_OK, intent);
                finish();
                startActivity(intent);
            }
        };
    }
}